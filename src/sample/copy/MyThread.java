package sample.copy;

import java.util.ArrayList;
import java.util.List;

public class MyThread {

	public static void main(String[] args) throws Exception {
		Thread t1 = new Thread(new MyRunnable());
		t1.start();
		t1.join();
		
		
		
		Thread t2 = new Thread(() -> {
			try {
				System.out.print("Executing thread 2");
				Thread.sleep(500);
				System.out.print("Ending thread 2");
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
		});
		t2.start();
		t2.join();
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		System.out.println(" ");
		list.forEach(x -> System.out.println(x));
		
		//int[] filteredList = list.stream().filter(x -> x == 1).toArray();
	}

}

class MyRunnable implements Runnable {
	public void run() {
		System.out.println("Thread Start::" + Thread.currentThread().getName());
		for (int i = 0; i < 10; i++) {
			System.out.println("Happening inside thread runnable::" + i);
		}
		System.out.println("Thread Finish ::" + Thread.currentThread().getName());
	}
}
