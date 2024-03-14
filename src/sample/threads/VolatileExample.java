package sample.threads;

class WorkerRunnable implements Runnable {
	
	// with volatile variable is always read and written to main memory by all threads instead of thread memory/cache...
	// its slightly slow but used for use cases where we want to maintain consistency of variable value across threads...
	
	/*
	 * The volatile keyword can be used to force the computer to store the variable in RAM as opposed to the CPU cache.
	 * Why is this important to concurrency? Using the volatile keyword forces the computer to perform any write operation on that variable
	 * BEFORE any subsequent read operation. This means if one thread is to read a volatile variable, it must first see if there is any write
	 * operations waiting to be performed on it in another thread.
	 * What are the downsides to 'volatile'? Storing the variable in the CPU cache makes operations that involve the variable 
	 * significantly faster thus it should be used only when necessary.
	 */
	
	private volatile boolean terminated;
	
	public boolean isTerminated() {
		return terminated;
	}

	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}

	@Override
	public void run() {
		while(!terminated) {
			System.out.println(Thread.currentThread().getName()+ ":: Worker class is running...");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class VolatileExample {
	
	
	public static void main(String[] args) throws Exception {
		WorkerRunnable runnable = new WorkerRunnable();
		Thread t1 = new Thread(runnable, "t1");
		Thread t2 = new Thread(runnable, "t2");
		
		t1.start();
		t2.start();
		
		Thread.sleep(10000);
		runnable.setTerminated(true);
		System.out.println("main thread ends...");
	}

}
