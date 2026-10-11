package sample2026;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUCache <K,V>{

    private static class Node<K,V> {
        K key;
        V value;
        Node<K,V> prev;
        Node<K,V> next;

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
    private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();


    public LRUCache(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity Must Be Greater Than 0");
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
            writeLock.lock();
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
            Node<K,V> node = map.get(key);
            if (node != null) {  // update scenario
                node.value = value;
                moveToHead(node);
            } else {
                if (map.size() >= capacity) {
                    // remove from tail and add to head the new node
                    Node<K, V> lruNode = removeTail();
                    if (lruNode!= null) {
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
        // impl pending
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
        if (node == null)  return null;
        removeNode(node);
        return node;
    }






}
