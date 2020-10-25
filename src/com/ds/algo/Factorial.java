package com.ds.algo;

public class Factorial {
	
	public static void main(String[] args) {
		System.out.println(getFactorial(3));
	}
	
	public static int getFactorial(int num) {
		if(num == 0) {
			return 1;
		}
		
		return num * getFactorial(num -1);

	}

}
