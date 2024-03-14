package sample.threads;

class ProcessWorker {
	
	//final Object object = new Object();

	public void produce() throws InterruptedException {
	
		synchronized (this) {
			System.out.println("inside producer - start - going to wait");
			wait();
			System.out.println("inside producer - wait over");
		}
	}
	
	public void consume() throws InterruptedException {
		Thread.sleep(1000);
		synchronized (this) {
			System.out.println("inside consume - start - calling notify");
			notify();
			Thread.sleep(1000);
			System.out.println("after notify statement");
		}
	}
	
}

public class WaitNotifyExample {
	
	public static void main(String[] args) {
		ProcessWorker worker = new ProcessWorker();
		Thread t1 = new Thread(() -> {
			try {
				worker.produce();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Thread t2 = new Thread(() -> {
			try {
				worker.consume();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		t1.start();
		t2.start();
	}

}
