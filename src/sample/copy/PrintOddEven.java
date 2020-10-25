package sample.copy;


public class PrintOddEven {
	
	public static void main(String[] args) throws Exception{
		
		OddEvenRunnable one=new OddEvenRunnable(0);
		OddEvenRunnable two=new OddEvenRunnable(1);
		
		Thread t1=new Thread(one,"One");
		Thread t2=new Thread(two,"Two");
		
		t1.start();
		t2.start();
		
	}

}


class OddEvenRunnable implements Runnable {
	
	int printUpTo = 10;
	int remainder = 0;
	static int number = 0;
	
	static Object lock = new Object();
	
	public OddEvenRunnable(int remainder) {
		this.remainder = remainder;
	}
	
	@Override
	public void run() {
		while (number < printUpTo) {
			synchronized (lock) {
				while (number % 2 != remainder) { // wait for numbers other than remainder
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() + " " + number);
				number++;
				lock.notify();
			}
		}
	}
}
