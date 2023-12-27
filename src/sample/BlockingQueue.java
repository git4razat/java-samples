package sample;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue {

	private List queue = new LinkedList();
	private int limit = 10;

	public BlockingQueue(int limit) {
		this.limit = limit;
	}

	public synchronized void enqueue(Object item) throws InterruptedException {
		while (this.queue.size() == this.limit) {
			wait();
		}
		if (this.queue.size() == 0) {
			notifyAll();
		}
		System.out.println("adding item to queue::"+ item);
		this.queue.add(item);
	}

	public synchronized Object dequeue() throws InterruptedException {
		while (this.queue.size() == 0) {
			wait();
		}
		if (this.queue.size() == this.limit) {
			notifyAll();
		}
		System.out.println("removing item from queue::"+ this.queue.get(0));
		return this.queue.remove(0);
	}
	
	public static void main(String[] args) throws Exception{
		BlockingQueue bQueue = new BlockingQueue(5);
		for(int i=0; i < 100; i++) {
			System.out.println("Enqueueing ::"+i);
			bQueue.enqueue(i);
			bQueue.dequeue();
		}
	}
}
