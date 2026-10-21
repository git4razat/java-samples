package sample2026;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CircularBufferThreadSafe<E> {
    private static final int DEFAULT_CAPACITY = 8;
    private final int capacity;
    private final E[] data;

    private long writeSeq = 0;
    private long readSeq = 0;

    private final ReentrantLock lock;
    private final Condition writeCondition;
    private final Condition readCondition;

    @SuppressWarnings("unchecked")
    public CircularBufferThreadSafe(int capacity) {
        this.capacity = (capacity <= 0) ? DEFAULT_CAPACITY : capacity;
        this.data = (E[]) new Object[this.capacity];
        this.lock = new ReentrantLock();
        this.writeCondition = lock.newCondition();
        this.readCondition = lock.newCondition();
    }

    public boolean write(E element) throws InterruptedException {
        if (element == null) throw new IllegalArgumentException("Element cannot be null");
        lock.lock();
        try {
            // Utilizing isFull() while holding the lock
            while (isFull()) {
                writeCondition.await();
            }
            int index = (int) (writeSeq % capacity);
            data[index] = element;
            writeSeq++;
            System.out.println("Added: " + element + " at index " + index);
            readCondition.signal();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public E read() throws InterruptedException {
        lock.lock();
        try {
            // Utilizing isEmpty() while holding the lock
            while (isEmpty()) {
                readCondition.await();
            }
            int index = (int) (readSeq % capacity);
            E element = data[index];
            data[index] = null; // Prevent memory leak for GC
            readSeq++;
            System.out.println("Read: " + element + " from index " + index);
            writeCondition.signal();
            return element;
        } finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        return writeSeq == readSeq;
    }

    public boolean isFull() {
        return (writeSeq - readSeq) >= capacity;
    }

    public int size() {
        return (int) (writeSeq - readSeq);
    }

    public static void main(String[] args) {
        CircularBufferThreadSafe<Integer> buffer = new CircularBufferThreadSafe<>(5);

        Thread t1 = new Thread(() -> {
            int count = 1;
            while (true) {
                try {
                    buffer.write(count);
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                count++;
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    buffer.read();
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        t1.start();
        t2.start();
    }
}