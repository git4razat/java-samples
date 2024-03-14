package sample.threads;


class DaemonWorker implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Daemon worker had completed execution (thread done)");
	}
}

class NormalWorker implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Normal worker had completed execution (thread done)");
	}
}


public class DaemonThreadExample {
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new DaemonWorker());
		Thread t2 = new Thread(new NormalWorker());
		t1.setDaemon(true);
		t1.start();
		t2.start();
		
		
		// main thread will only wait for daemon thread to finish if there is a user/normal thread running else it terminates
		// means daemon threads are low priority threads amd will not exists without App threads. like main or user threads...
				
		System.out.println("In main method");
	}

}
