package sample;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {
	
	public static void main(String[] args) throws Exception {
		ExecutorService service = Executors.newFixedThreadPool(10);
		
		for (int i = 0; i < 100; i++) {
			service.execute(new MyTask());
		}
		service.shutdown();
		
		Future<Integer> future = service.submit(new MyCallable());
		int x = future.get();
		System.out.println("Output from callable::" + x);
	}
}

class MyTask implements Runnable {
	public void run() {
		//Thread.sleep(10000);
		System.out.println("Task is over::" + Thread.currentThread().getName());
	}
}

class MyCallable implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		Thread.sleep(30000);
		return new Random().nextInt();
	}
	
}
