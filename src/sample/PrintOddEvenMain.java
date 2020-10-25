package sample;


class OddEvenRunnable implements Runnable{
	 
	public int PRINT_NUMBERS_UPTO=10;
	static int  number=0;
	int remainder;
	static Object lock=new Object();
 
	OddEvenRunnable(int remainder)
	{
		this.remainder=remainder;
	}
 
	@Override
	public void run() {
		while (number < PRINT_NUMBERS_UPTO) {
			synchronized (lock) {
				while (number % 3 != remainder) { // wait for numbers other than remainder
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(Thread.currentThread().getName() + " " + number);
				number++;
				lock.notifyAll();
			}
		}
	}
}


public class PrintOddEvenMain {
	public static void main(String[] args) {
 
		OddEvenRunnable one=new OddEvenRunnable(0);
		OddEvenRunnable two=new OddEvenRunnable(1);
		OddEvenRunnable three=new OddEvenRunnable(2);
		
		Thread t1=new Thread(one,"One");
		Thread t2=new Thread(two,"Two");
		Thread t3=new Thread(three,"Three");
		
		t1.start();
		t2.start();
		t3.start();
 
	}
}
