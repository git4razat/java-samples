package com.sample;

public class BinarySearchArray {
	
	public static void main(String[] args) {
		int[] intArr = {10,20,30,40,50,60,70,80,90,100};
		findIndex(intArr, 30);
	}
	
	public static int findIndex(int[] arr, int searchKey) {
		
		int i = 0;
		int j = arr.length -1;
		int m;
		
		while(i < j) {
			m  = (i + j) / 2;
			if (arr[m] == searchKey) {
				System.out.println("Found key at index::" + m);
				return m;
			} else if(arr[m] > searchKey) {
				System.out.println("Need to search In the lower half");
				j = m -1;
			} else if(arr[m] < searchKey) {
				System.out.println("Need to search In the upper half");
				i = m;
			}
		}
		
		System.out.println("Not Found");
		
		return -1;
	}

}
