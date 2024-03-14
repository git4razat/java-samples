package sample.copy;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;


public class ProducerConsumer {
	
	public static void main(String[] args) {
	
		final Queue q = new LinkedList();
		
		Producer prod = new Producer(q, 10);
		Consumer cons = new Consumer(q, 10);
		
		Thread t1 = new Thread(prod, "Producer");
		Thread t2 = new Thread(cons, "Consumer");
		
		t1.start();
		t2.start();
	}
	

}

class Producer implements Runnable {
	
	private Queue<Integer> queue;
	
	private int maxSize;
	
	public Producer(Queue queue, int maxSize) {
		this.queue = queue;
		this.maxSize = maxSize;
	}

	@Override
	public void run() {
		while(true) {
			synchronized (queue) {
				while(queue.size() == maxSize) {
					try {
						queue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				int element = new Random().nextInt();
				System.out.println("Producer : Element:"+ element);
				queue.add(element);
				queue.notify();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
}

class Consumer implements Runnable {
	
	private Queue<Integer> queue;
	
	private int maxSize;
	
	public Consumer(Queue queue, int maxSize) {
		this.queue = queue;
		this.maxSize = maxSize;
	}
	
	@Override
	public void run() {
		while(true) {
			synchronized (queue) {
				while(queue.isEmpty()) {
					try {
						queue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				int element = queue.poll();
				System.out.println("Consumer :Got Element:"+ element);
				queue.notify();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
