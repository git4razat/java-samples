package sample;

public class JavaLambdas {

	public static void main(String[] args) {
		// Equivalent to interface and impl classes , but functional ingterface with one
		// method only.
		MyLambda lambdaFn = () -> System.out.println("Hello World!");
		lambdaFn.foo();

		MyAdd addIntf = (a, b) -> a + b;
		System.out.println(addIntf.add(5, 10));

		// Three ways to create thread , Runnbale Impl class, anonymous inner class and
		// lambds.

		Runnable innerRunnable = new Runnable() {

			@Override
			public void run() {
				System.out.println("inside run of innerRunnable");
			}
		};

		// using lambdas - remember lambdas function type is only for functinal
		// interfaces like Runnable which has only one method
		Runnable lambdaRunnable = () -> System.out.println("inside lambda runnable");

		Thread t1 = new Thread(new Worker());
		Thread t2 = new Thread(innerRunnable);
		Thread t3 = new Thread(lambdaRunnable);
		Thread t4 = new Thread(() -> System.out.println("inside inline lambda function"));
		// ingternally thread class will call the run method of runnable instance which
		// is being passed once the thread is started..

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}

}

class Worker implements Runnable {
	public void run() {
		System.out.println("inside run of Worker");
	}
}

@FunctionalInterface
interface MyLambda {
	void foo();
}

interface MyAdd {
	int add(int a, int b);
}
