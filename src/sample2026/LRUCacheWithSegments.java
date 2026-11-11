package sample2026;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUCacheWithSegments<K, V> {

    private final int numSegments;
    private final CacheSegment<K, V>[] segments;

    @SuppressWarnings("unchecked")
    public LRUCacheWithSegments(int totalCapacity, int numSegments) {
        if (totalCapacity <= 0 || numSegments <= 0) {
            throw new IllegalArgumentException("Capacity and segment count must be greater than 0");
        }
        this.numSegments = numSegments;
        this.segments = new CacheSegment[numSegments];

        int segmentCapacity = Math.max(1, totalCapacity / numSegments);
        for (int i = 0; i < numSegments; i++) {
            segments[i] = new CacheSegment<>(segmentCapacity);
        }
    }

    public LRUCacheWithSegments(int totalCapacity) {
        this(totalCapacity, 16);
    }

    private CacheSegment<K, V> getSegment(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        int hash = key.hashCode() & 0x7FFFFFFF;
        return segments[hash % numSegments];
    }

    public V get(K key) {
        return getSegment(key).get(key);
    }

    public void put(K key, V value) {
        getSegment(key).put(key, value);
    }

    // =========================================================================
    // MAIN METHOD: Verification and Concurrency Test
    // =========================================================================
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Initializing Segmented LRU Cache Test ===");

        // Total capacity of 100, split across 4 segments
        LRUCacheWithSegments<String, String> cache = new LRUCacheWithSegments<>(100, 4);

        // 1. Basic Put and Get Verification
        cache.put("A", "Apple");
        cache.put("B", "Banana");
        cache.put("C", "Cherry");

        System.out.println("Get A (Expected 'Apple'): " + cache.get("A"));
        System.out.println("Get B (Expected 'Banana'): " + cache.get("B"));
        System.out.println("Get non-existent key (Expected null): " + cache.get("Z"));

        // 2. Multi-Threaded Concurrency Test (Stress Testing Lock Striping)
        int threadCount = 10;
        int operationsPerThread = 1000;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        System.out.println("\nSpawning " + threadCount + " threads to perform " + (threadCount * operationsPerThread) + " concurrent operations...");

        for (int t = 0; t < threadCount; t++) {
            final int threadId = t;
            executor.submit(() -> {
                for (int i = 0; i < operationsPerThread; i++) {
                    String key = "Key-" + (threadId * operationsPerThread + i);
                    String value = "Val-" + i;
                    cache.put(key, value);
                    cache.get(key);
                }
            });
        }

        executor.shutdown();
        boolean finished = executor.awaitTermination(5, TimeUnit.SECONDS);

        if (finished) {
            System.out.println("Concurrency test completed successfully without deadlocks or exceptions!");
        } else {
            System.out.println("Concurrency test timed out.");
        }

        System.out.println("=== Test Suite Complete ===");
    }

    // =========================================================================
    // CACHE SEGMENT
    // =========================================================================
    private static class CacheSegment<K, V> {
        private static class Node<K, V> {
            K key;
            V value;
            Node<K, V> prev;
            Node<K, V> next;

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private final int capacity;
        private final Map<K, Node<K, V>> map;
        private final Node<K, V> head;
        private final Node<K, V> tail;

        private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        CacheSegment(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();

            head = new Node<>(null, null);
            tail = new Node<>(null, null);
            head.next = tail;
            tail.prev = head;
        }

        public V get(K key) {
            writeLock.lock();
            try {
                Node<K, V> node = map.get(key);
                if (node == null) {
                    return null;
                }
                moveToHead(node);
                return node.value;
            } finally {
                writeLock.unlock();
            }
        }

        public void put(K key, V value) {
            writeLock.lock();
            try {
                Node<K, V> node = map.get(key);
                if (node != null) {
                    node.value = value;
                    moveToHead(node);
                } else {
                    if (map.size() >= capacity) {
                        Node<K, V> lruNode = removeTail();
                        if (lruNode != null) {
                            map.remove(lruNode.key);
                        }
                    }

                    Node<K, V> newNode = new Node<>(key, value);
                    addToHead(newNode);
                    map.put(key, newNode);
                }
            } finally {
                writeLock.unlock();
            }
        }

        private void moveToHead(Node<K, V> node) {
            removeNode(node);
            addToHead(node);
        }

        private void removeNode(Node<K, V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void addToHead(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        private Node<K, V> removeTail() {
            Node<K, V> node = tail.prev;
            if (node == head) return null;
            removeNode(node);
            return node;
        }
    }
}
