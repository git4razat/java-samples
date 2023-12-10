import java.util.ArrayList;
import java.util.List;

public class JavaEatMemory {
	
	public static void main(String[] args) throws InterruptedException{

		
		
		Runnable runable = () -> {
			injectLeak();
		};
		
		Thread thread1 = new Thread(runable);
		thread1.start();
		
		/*while(true) {
			System.out.print(Thread.currentThread().getName() + ":: inside true");
		}*/
		
		
		
		/*Runnable basic = () ->
		        {
		 
		           injectLeak();
		        };
		 
		        Thread thread1 = new Thread(basic);
		        thread1.start();
		        thread2.start();*/
		
		thread1.join();

    }
	
	private static void injectLeak() {
		System.out.print(Thread.currentThread().getName() + ":: inside true");
        List<byte[]> list = new ArrayList<>();
        System.out.printf(Thread.currentThread().getName() + " :: inside injectLeak:");
        int index = 1;
        
        Runtime rt = Runtime.getRuntime();
        long totalMemory = rt.totalMemory();
        long freeMemory = rt.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        
        System.out.println(Thread.currentThread().getName() + " :: inside injectLeak:");
        System.out.println(Thread.currentThread().getName() + " :: inside injectLeak:totalMemory:" + totalMemory);
        System.out.println(Thread.currentThread().getName() + " :: inside injectLeak:freeMemory:" + freeMemory);
        System.out.println(Thread.currentThread().getName() + " :: inside injectLeak:usedMemory:" +(usedMemory/totalMemory)*100);
        
        while (true) {
            // 1MB each loop, 1 x 1024 x 1024 = 1048576
            byte[] b = new byte[1048576];
            list.add(b);
            freeMemory = rt.freeMemory();
            usedMemory = totalMemory - freeMemory;
            //Runtime rt = Runtime.getRuntime();
            System.out.printf(Thread.currentThread().getName() + " : [%d] used memory: %s%n", index++, (usedMemory/totalMemory));
        }
    }
}
