package sample.threads;

public class SynchronizationExample {
	
	private static int counter = 0;
	
	
	private static void increment() {
		synchronized (SynchronizationExample.class) {
			counter++;
		}
	}
	
	public static void main(String[] args) throws InterruptedException{
		
		Thread t1 = new Thread(() -> {
			for(int i=0; i < 100000; i++) {
				increment();
			}
		});
		
		
		Thread t2 = new Thread(() -> {
			for(int i=0; i < 100000; i++) {
				increment();
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		
		System.out.println("counter::" + counter);
		
	}

}
