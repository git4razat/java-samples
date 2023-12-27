package sample.threads;

public class JoinThreadExample {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Done with Thread 1");
			
		});
		
		Thread t2 = new Thread(() -> {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Done with Thread 2");
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done with Main Thread");
	
	}
	
}
