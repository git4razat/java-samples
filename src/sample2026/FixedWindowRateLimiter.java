package sample2026;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class FixedWindowRateLimiter {

    private long windowSizeInMillis;
    private int maxRequestPerWindow;

    private ConcurrentHashMap<Long, Integer> counters = new ConcurrentHashMap<>();
    private ReentrantLock lock = new ReentrantLock();

    public FixedWindowRateLimiter(long windowSizeInMillis, int maxRequestPerWindow) {
        this.windowSizeInMillis = windowSizeInMillis;
        this.maxRequestPerWindow = maxRequestPerWindow;
    }

    public boolean allowRequest() {
        long currentWindowKey = System.currentTimeMillis() / windowSizeInMillis;
        lock.lock();
        try {
            int currentCount = counters.getOrDefault(currentWindowKey, 0);
        if (currentCount < maxRequestPerWindow) {
                counters.put(currentWindowKey, currentCount + 1);
                cleanOldKeys(currentWindowKey);
                return true;
            }
            return false;
        } finally {
          lock.unlock();
        }
    }

    public void cleanOldKeys(long currentWindowKey) {
        counters.keySet().removeIf(windowKey -> windowKey < currentWindowKey);
    }

    public static void main(String[] args) throws InterruptedException {
        // Allow max 3 requests per 1-second window
        FixedWindowRateLimiter limiter = new FixedWindowRateLimiter(1000, 3);

        System.out.println("--- Sending 5 rapid requests in the same window ---");
        for (int i = 1; i <= 5; i++) {
            boolean allowed = limiter.allowRequest();
            System.out.println("Request " + i + " allowed? " + allowed);
        }

        System.out.println("\nWaiting 1 second for next window reset...");
        Thread.sleep(1000);

        System.out.println("--- Sending requests in the new window ---");
        for (int i = 1; i <= 3; i++) {
            boolean allowed = limiter.allowRequest();
            System.out.println("Request " + i + " allowed? " + allowed);
        }
    }

}
