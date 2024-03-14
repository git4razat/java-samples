package sample.threads;


public class TimeSlicingExample {

	public static void main(String[] args) {
		Thread t1 = new Thread(() -> {
			for (int i =0; i < 100; i++) {
				System.out.println("Thread1::" + i);
			}
		});
		
		Thread t2 = new Thread(() -> {
			for (int i =0; i < 100; i++) {
				System.out.println("Thread2::" + i);
			}
		});
		t1.start();
		t2.start();
	}
}
