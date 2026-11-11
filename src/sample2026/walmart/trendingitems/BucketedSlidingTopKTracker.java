package sample2026.walmart.trendingitems;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class BucketedSlidingTopKTracker {
    private int k;
    private int numberOfBuckets;
    private long bucketDurationMillis;

    private ConcurrentHashMap<String, AtomicLong>[] buckets;
    private volatile int currentIndex;
    private volatile long lastBucketStartTime;

    private final ReentrantLock lock = new ReentrantLock();

    public BucketedSlidingTopKTracker(int k, int numberOfBuckets, long bucketDurationMillis) {
        if (k <= 0 || numberOfBuckets <= 0 || bucketDurationMillis <= 0) {
            throw new IllegalArgumentException("Input details are invalid, please check..");
        }
        this.k = k;
        this.numberOfBuckets = numberOfBuckets;
        this.bucketDurationMillis = bucketDurationMillis;

        this.currentIndex = 0;
        this.lastBucketStartTime = System.currentTimeMillis();

        this.buckets = new ConcurrentHashMap[numberOfBuckets];
        for (int i = 0; i < numberOfBuckets; i++) {
            this.buckets[i] = new ConcurrentHashMap<>();
        }
    }

    public void recordEvent(String itemId) {
        if (itemId == null || itemId.isEmpty()) return;
        checkAndRotateBuckets();
        buckets[currentIndex].computeIfAbsent(itemId, k -> new AtomicLong(0)).incrementAndGet();
    }

    private void checkAndRotateBuckets() {
        long now = System.currentTimeMillis();
        if (now - lastBucketStartTime >= bucketDurationMillis) {
            lock.lock();
            try {
                // Double-check inside lock to handle race conditions safely
                while (now - lastBucketStartTime >= bucketDurationMillis) {
                    currentIndex = (currentIndex + 1) % numberOfBuckets;
                    buckets[currentIndex].clear(); // Wipes out stale data from the previous cycle
                    lastBucketStartTime += bucketDurationMillis;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * Aggregates counts across all active buckets and computes Top-K using a Min-Heap.
     */
    public List<TrendingItem> getTopK() {
        checkAndRotateBuckets();

        // Step 1: Merge all active buckets into a single temporary frequency map
        Map<String, Long> aggregatedMap = new HashMap<>();
        for (int i = 0; i < numberOfBuckets; i++) {
            ConcurrentHashMap<String, AtomicLong> bucket = buckets[i];
            for (Map.Entry<String, AtomicLong> entry : bucket.entrySet()) {
                aggregatedMap.merge(entry.getKey(), entry.getValue().get(), Long::sum);
            }
        }

        // Step 2: Compute Top-K using a Min-Heap of size K
        PriorityQueue<TrendingItem> minHeap = new PriorityQueue<>(
                Comparator.comparingLong(item -> item.getFrequency())
        );

        for (Map.Entry<String, Long> entry : aggregatedMap.entrySet()) {
            minHeap.offer(new TrendingItem(entry.getKey(), entry.getValue()));
            if (minHeap.size() > k) {
                minHeap.poll(); // Evict the lowest frequency item when capacity exceeds K
            }
        }

        // Step 3: Extract and reverse elements so highest frequency appears first
        List<TrendingItem> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }
        Collections.reverse(result);
        return result;
    }

    // =========================================================================
    // Verification Main Method
    // =========================================================================
    public static void main(String[] args) throws InterruptedException {
        // Track Top 2 items using 3 buckets of 1 second each for demonstration
        BucketedSlidingTopKTracker tracker = new BucketedSlidingTopKTracker(2, 3, 1000);

        tracker.recordEvent("SKU-PROD-A");
        tracker.recordEvent("SKU-PROD-B");
        tracker.recordEvent("SKU-PROD-A");

        System.out.println("Top 2 Trending Items (Initial):");
        for (TrendingItem item : tracker.getTopK()) {
            System.out.println(item);
        }

        // Simulate time passing to observe bucket rotation
        System.out.println("\nWaiting for bucket rotation...");
        Thread.sleep(2500);

        tracker.recordEvent("SKU-PROD-C");
        tracker.recordEvent("SKU-PROD-C");
        tracker.recordEvent("SKU-PROD-B");

        System.out.println("Top 2 Trending Items (After Window Shift):");
        for (TrendingItem item : tracker.getTopK()) {
            System.out.println(item);
        }
    }

}
