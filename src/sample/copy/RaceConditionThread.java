package sample.copy;

public class RaceConditionThread {
	
	public static void main(String[] args) {
		MRunnable runnable = new MRunnable();
		Thread t1 = new Thread(runnable);
		Thread t2 = new Thread(runnable);
		
		t1.start();
		t2.start();
	}

}

// try below count++ without synchronized , it will generate random upper limit 
// but with synchronized, it will always have upper limit of count as 200000000
// because at a time only one thread can update the count.
// this race condition is happening bcoz we have shared variable myrunnable.count between threads...

class MRunnable implements Runnable {

	int count = 0;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for (int i =0; i < 100000000; i++) {
			synchronized (this) {
				count++;
			}
		}
		System.out.println(Thread.currentThread().getName() +"<<>>"+ count);
		
	}
	
	
}

