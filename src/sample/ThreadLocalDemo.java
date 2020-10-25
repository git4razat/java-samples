package sample;

import java.lang.ThreadLocal;

public class ThreadLocalDemo {
	
	public static void main(String[] args) {
		ThreadLocal<Integer> threadLocalValue = new ThreadLocal<>();
		threadLocalValue.set(1);
		System.out.println(Thread.currentThread().getName());
		Thread.currentThread().setName("test");
		System.out.println(Thread.currentThread().getName());
	}

}
