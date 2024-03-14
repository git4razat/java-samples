package sample.threads;

import java.util.ArrayList;
import java.util.List;

class ProducerCosumerWorker {
	
	private List<Integer> list = new ArrayList<Integer>();
	private Object lock = new Object();
	private int capacity = 5;
	private int value = 0;
	
	
	public void produce() throws InterruptedException {
		System.out.println("Inside Produce - start");
		while(true) {
			synchronized (lock) {
				if (list.size() == capacity) {
					System.out.println("List is full, going to wait");
					lock.wait();
					value = 0;
				} else {
					System.out.println("Adding Value to List - " + value);
					list.add(value);
					value++;
					lock.notify();
				}
				Thread.sleep(500);
			}
		}
	}
	
	
	public synchronized void consume() throws InterruptedException {
		System.out.println("Inside Consume - start");
		Thread.sleep(500);
		while(true) {
			synchronized (lock) {
				if (list.size() == 0) {
					System.out.println("List is Empty , going to wait");
					lock.wait();
				} else {
					int x = list.remove(list.size() - 1);
					System.out.println("Got Value From List - " + x);
					lock.notify();
				}
				Thread.sleep(500);
			}
		}
	}
}


public class ProducerConsumerExample {
	
	public static void main(String[] args) {
		ProducerCosumerWorker worker = new ProducerCosumerWorker();
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
