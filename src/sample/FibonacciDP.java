package sample;

// Using Dynamic Programming

//In fibonacci series, next number is the sum of previous two numbers 
//for example 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55 etc. 
//The first two numbers of fibonacci series are 0 and 1.

public class FibonacciDP {

	static int[] seq;
	// assuming 100 is a sufficient size.

	static int fib(int n) {
		seq = new int[n];
		seq[0] = 0;
		seq[1] = 1;
		for (int i = 2; i <= n - 1; i++) {
			seq[i] = seq[i - 1] + seq[i - 2];
		}
		return seq[n - 1];
	}

	public static void main(String args[]) {
		int n = 7;
		System.out.println(fib(n));
	}

}
