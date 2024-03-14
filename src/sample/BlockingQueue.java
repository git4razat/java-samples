package sample;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue {

	private List queue = new LinkedList();
	private int limit = 10;
	private Object lock = new Object();

	public BlockingQueue(int limit) {
		this.limit = limit;
	}

	public void enqueue(Object item) throws InterruptedException {
		synchronized (lock) {
			while (this.queue.size() == this.limit) {
				System.out.println("Queue Full- Going to Wait");
				lock.wait();
			}
			if (this.queue.size() == 0) {
				lock.notifyAll();
			}
			System.out.println("adding item to queue::"+ item);
			this.queue.add(item);
			//Thread.sleep(1000);
		}
	}

	public Object dequeue() throws InterruptedException {
		int result = -1;
		synchronized (lock) {
			while (this.queue.size() == 0) {
				System.out.println("Queue Empty- Going to Wait");
				lock.wait();
			}
			if (this.queue.size() == this.limit) {
				lock.notifyAll();
			}
			
			result = (int) this.queue.get(0);
			this.queue.remove(0);
			System.out.println("removing item from queue::"+ result);
			Thread.sleep(1000);
			return result;
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BlockingQueue bQueue = new BlockingQueue(5);
		
		Thread t1 = new Thread(() -> {
			for(int i = 0; i < 100; i++) {
				try {
					bQueue.enqueue(i);
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(() -> {
			for(int i = 0; i < 100; i++) {
				try {
					bQueue.dequeue();
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t1.start();
		Thread.sleep(5000);
		t2.start();
	}
}
