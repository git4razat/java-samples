package sample2026.walmart.trendingitems;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class StreamingTopKTracker {
    private int k;

    private ConcurrentHashMap<String, AtomicLong> frequencyMap;

    public StreamingTopKTracker(int k) {
        if (k <= 0) throw new IllegalArgumentException("K should be greater than 0");
        this.k = k;
        frequencyMap = new ConcurrentHashMap<>();
    }

    public void recordEvent(String itemId) {
        if (itemId == null || itemId.isEmpty()) return;
        frequencyMap.computeIfAbsent(itemId, id -> new AtomicLong()).incrementAndGet();
    }

    public List<TrendingItem> getTopK() {
        PriorityQueue<TrendingItem> minHeap = new PriorityQueue<>((obj1,obj2) ->
            {
                return Long.compare(obj1.getFrequency(),obj2.getFrequency());
            }
        );

        for(Map.Entry<String, AtomicLong> entry: frequencyMap.entrySet()) {
           String itemId = entry.getKey();
           long frequency = entry.getValue().get();

           minHeap.offer(new TrendingItem(itemId, frequency));

           if (minHeap.size() > k) {
               minHeap.poll();
           }
        }

        List<TrendingItem> result = new ArrayList<>();
        while(!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }
        Collections.reverse(result);
        return result;
    }

    // =========================================================================
    // Verification Main Method
    // =========================================================================
    public static void main(String[] args) throws InterruptedException {
        StreamingTopKTracker tracker = new StreamingTopKTracker(3); // Track Top 3

        // Simulate incoming event stream
        tracker.recordEvent("SKU-100"); // Laptop
        tracker.recordEvent("SKU-200"); // Phone
        tracker.recordEvent("SKU-100"); // Laptop
        tracker.recordEvent("SKU-300"); // Tablet
        tracker.recordEvent("SKU-100"); // Laptop (SKU-100 frequency = 3)
        tracker.recordEvent("SKU-200"); // Phone (SKU-200 frequency = 2)
        tracker.recordEvent("SKU-400"); // Headphones
        tracker.recordEvent("SKU-400"); // Headphones
        tracker.recordEvent("SKU-400"); // Headphones (SKU-400 frequency = 3)

        System.out.println("Current Top 3 Trending Items:");
        for (TrendingItem item : tracker.getTopK()) {
            System.out.println(item);
        }
        // Expected: SKU-100 (3), SKU-400 (3), SKU-200 (2) [Order between ties may vary]
    }
}
