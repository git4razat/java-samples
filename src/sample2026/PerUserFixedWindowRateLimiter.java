package sample2026;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class PerUserFixedWindowRateLimiter {

    private final long windowSizeInMillis;
    private final int maxLimit;

    private static class UserState {
        long windowKey = 0;
        int count = 0;
        final ReentrantLock lock = new ReentrantLock();
    }

    private final ConcurrentHashMap<String, UserState> storage = new ConcurrentHashMap<>();

    public PerUserFixedWindowRateLimiter(long windowSizeInMillis, int maxLimit) {
        this.windowSizeInMillis = windowSizeInMillis;
        this.maxLimit = maxLimit;
    }

    public boolean allowRequest(String userId) {
        long currentWindow = System.currentTimeMillis() / windowSizeInMillis;
        UserState state = storage.computeIfAbsent(userId, k -> new UserState());

        state.lock.lock();
        try {
            // Check if we rolled into a new window
            if (state.windowKey != currentWindow) {
                state.windowKey = currentWindow;
                state.count = 0;
            }

            if (state.count < maxLimit) {
                state.count++;
                return true;
            }
            return false;
        } finally {
            state.lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        // Allow max 2 requests per user per 1-second window
        PerUserFixedWindowRateLimiter limiter = new PerUserFixedWindowRateLimiter(1000, 2);

        String userA = "user_alice";
        String userB = "user_bob";

        System.out.println("--- Testing Alice's requests ---");
        System.out.println("Alice Req 1: " + limiter.allowRequest(userA)); // true
        System.out.println("Alice Req 2: " + limiter.allowRequest(userA)); // true
        System.out.println("Alice Req 3 (Exceeds limit): " + limiter.allowRequest(userA)); // false

        System.out.println("\n--- Testing Bob's requests (Should be independent of Alice) ---");
        System.out.println("Bob Req 1: " + limiter.allowRequest(userB)); // true
        System.out.println("Bob Req 2: " + limiter.allowRequest(userB)); // true

        System.out.println("\nWaiting 1 second for window reset...");
        Thread.sleep(1000);

        System.out.println("\n--- Testing Alice again after window reset ---");
        System.out.println("Alice Req 1 (New window): " + limiter.allowRequest(userA)); // true
    }
}