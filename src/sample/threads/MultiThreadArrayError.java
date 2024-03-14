package sample.threads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ReadTask implements Runnable {

	private List<Integer> list;
	
	public ReadTask (List<Integer> list) {
		this.list = list;
	}
	
	public void run() {
		try {
			for (int i = 0; i < 1000; i++) {
				list.add(i);
			}
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

class WriteTask implements Runnable {
private List<Integer> list;
	
	public WriteTask (List<Integer> list) {
		this.list = list;
	}
	
	public void run() {
		try {
			for (int i = 0; i < 1000; i++) {
				list.remove(i);
			}
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

public class MultiThreadArrayError {
	public static void main(String[] args) throws Exception {
		List<Integer> list = Collections.synchronizedList(new ArrayList<>());
		Thread t1 = new Thread(new ReadTask(list));
		Thread t2 = new Thread(new WriteTask(list));
		t1.start();
		Thread.sleep(1000);
		t2.start();
		t1.join();
		t2.join();
		System.out.println("list::" + list);
	}
}
