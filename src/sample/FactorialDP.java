package sample;

import java.util.ArrayList;
import java.util.Arrays;

// this is using memoization
public class FactorialDP {
	
	static int[] seq;
	
	public static int factorial(int n) {
		if (n == 0) {
			return 1;
		}
		if (seq[n] != 0) {
			return seq[n];
		}
		int result = n * factorial(n -1);
		seq[n] = result;
		return result;
	}
	
	public static void main(String[] args) {
		seq = new int[5];
		//System.out.println(Arrays.asList(seq));
		System.out.println(factorial(5));
	}

}
