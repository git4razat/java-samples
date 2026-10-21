package sample2026;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class SlidingWindowCounterRateLimiter {

    private final long windowSizeInMillis;
    private final int maxLimit;

    // Encapsulated state for each individual user
    private static class WindowState {
        long currentWindowKey = 0;
        int currentCount = 0;
        long previousWindowKey = 0;
        int previousCount = 0;

        // Fine-grained lock specific ONLY to this user
        final ReentrantLock userLock = new ReentrantLock();
    }

    // Outer map stores isolated state per userId
    private final ConcurrentHashMap<String, WindowState> userStates = new ConcurrentHashMap<>();

    public SlidingWindowCounterRateLimiter(long windowSizeInMillis, int maxLimit) {
        if (windowSizeInMillis <= 0 || maxLimit <= 0) {
            throw new IllegalArgumentException("Window size and max limit must be greater than zero.");
        }
        this.windowSizeInMillis = windowSizeInMillis;
        this.maxLimit = maxLimit;
    }

    public boolean allowRequest(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("UserId cannot be null or empty.");
        }

        long now = System.currentTimeMillis();
        long currentWindowKey = now / windowSizeInMillis;

        // Get or create state safely for this specific user
        WindowState state = userStates.computeIfAbsent(userId, k -> new WindowState());

        // Acquire lock ONLY for this user (lock striping / fine-grained locking)
        state.userLock.lock();
        try {
            // Check if we have transitioned into a new time window
            if (state.currentWindowKey != currentWindowKey) {
                if (state.currentWindowKey == currentWindowKey - 1) {
                    // Immediate neighbor window: shift current to previous
                    state.previousCount = state.currentCount;
                    state.previousWindowKey = state.currentWindowKey;
                } else {
                    // Too much time has elapsed; previous window data is completely stale
                    state.previousCount = 0;
                    state.previousWindowKey = currentWindowKey - 1;
                }
                state.currentWindowKey = currentWindowKey;
                state.currentCount = 0; // Reset current window count
            }

            // Calculate sliding window overlap weight
            long timeIntoCurrentWindow = now % windowSizeInMillis;
            double weight = (double) (windowSizeInMillis - timeIntoCurrentWindow) / windowSizeInMillis;

            double estimatedRequests = (state.previousCount * weight) + state.currentCount;

            if (estimatedRequests < maxLimit) {
                state.currentCount++;
                return true;
            }

            return false; // Rate limited for this user
        } finally {
            state.userLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Allow max 3 requests per user per 2-second window
        SlidingWindowCounterRateLimiter limiter = new SlidingWindowCounterRateLimiter(2000, 3);

        String alice = "user_alice";
        String bob = "user_bob";

        System.out.println("--- Testing Alice ---");
        System.out.println("Alice Req 1: " + limiter.allowRequest(alice));
        System.out.println("Alice Req 2: " + limiter.allowRequest(alice));
        System.out.println("Alice Req 3: " + limiter.allowRequest(alice));
        System.out.println("Alice Req 4 (Should be rate limited): " + limiter.allowRequest(alice));

        System.out.println("\n--- Testing Bob (Independent quota) ---");
        System.out.println("Bob Req 1: " + limiter.allowRequest(bob));
        System.out.println("Bob Req 2: " + limiter.allowRequest(bob));
    }
}