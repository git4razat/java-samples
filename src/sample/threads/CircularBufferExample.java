package sample.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class CircularBuffer<E> {

    private static final int DEFAULT_CAPACITY = 8;

    private final int capacity;
    private final E[] data;
    private int writeSequence, readSequence;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    @SuppressWarnings("unchecked")
    public CircularBuffer(int capacity) {
        this.capacity = (capacity < 1) ? DEFAULT_CAPACITY : capacity;
        this.data = (E[]) new Object[this.capacity];
        this.readSequence = 0;
        this.writeSequence = -1;
    }

    public boolean write(E element) throws InterruptedException {
    	try {
        	lock.lock();
        	if (isFull()) {
            	//return false;
        		condition.await();
            }
            int nextWriteSeq = writeSequence + 1;
            System.out.println("Adding Element to Buffer - " + element);
            data[nextWriteSeq % capacity] = element;
            writeSequence++;
            condition.signal();
            Thread.sleep(500);
            return true;
    	} finally {
    		lock.unlock();
    	}
    }

    public E read() throws InterruptedException {
    	try {
        	lock.lock();
        	if (isEmpty()) {
        		//return null;
        		condition.await();
        	}
        	E nextValue = data[readSequence % capacity];
            readSequence++;
            System.out.println("Reading Element From Buffer - " + nextValue);
            condition.signal();
            Thread.sleep(500);
            return nextValue;
    	} finally {
    		lock.unlock();
    	}
    }

    public int size() {
        return (writeSequence - readSequence) + 1;
    }

    public boolean isEmpty() {
        return writeSequence < readSequence;
    }

    public boolean isFull() {
        return size() >= capacity;
    }
}



public class CircularBufferExample {
	
	public static void main(String[] args) throws Exception {
		CircularBuffer<Integer> buffer = new CircularBuffer<Integer>(5);
		Thread t1 = new Thread(() -> {
			int count = 0;
			while(true) {
				try {
					buffer.write(count);
					count++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		Thread t2 = new Thread(() -> {
			while(true) {
				try {
					buffer.read();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		//t1.join();
		t2.start();
		//t2.join();
	}

}
