package sample.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class WorkerWithLocks {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private List<Integer> list = new ArrayList();
	private int capacity = 10;
	private int value = 0;
	
	
	public void produce() throws InterruptedException {
		System.out.println("Inside Produce - Start");
		while(true) {
			try {
				lock.lock();
				if (list.size() == capacity) {
					System.out.println("List is full, going to wait.");
					// similar to wait
					condition.await();
					value = 0;
				} else {
					System.out.println("Adding to list - " + value);
					list.add(value);
					value++;
					// similar to notify
					condition.signal();
					Thread.sleep(500);
				}
			} finally {
				lock.unlock();
			}
		}
	}
	
	public void consume() throws InterruptedException {
		System.out.println("Inside Consume - Start");
		Thread.sleep(500);
		while(true) {
			try {
				lock.lock();
				if (list.size() == 0) {
					System.out.println("List is empty, going to wait");
					condition.await();
				} else {
					int x = list.remove(list.size() - 1);
					System.out.println("Got Element from list - " + x);
					condition.signal();
					Thread.sleep(500);
				}
			} finally {
				lock.unlock();
			}
		}
	}
}


public class ProducerConsumerExampleWithLocks {
	
	public static void main(String[] args) {
		WorkerWithLocks worker = new WorkerWithLocks();
		Thread t1 = new Thread(() -> {
			try {
				worker.produce();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(() -> {
			try {
				worker.consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		t1.start();
		t2.start();
	}

}
