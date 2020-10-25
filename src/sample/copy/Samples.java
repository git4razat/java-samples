package sample.copy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Samples {
	
	// merge two sorted arrays
	public static int[] merge(int[] arr1, int[] arr2) {
		int i = 0,j = 0 ,k = 0;
		int[] mergedArr = new int[arr1.length + arr2.length];
		while(i < arr1.length && j < arr2.length) {
			if(arr1[i] < arr2[j]) {
				mergedArr[k] = arr1[i];
				i++;
				k++;
			} else {
				mergedArr[k] = arr2[j];
				j++;
				k++;
			}
		}
		
		// remaining arr1 items
		while(i < arr1.length) {
			mergedArr[k] = arr1[i];
			i++;
			k++;
		}
		
		// reamining arr2 items
		while(j < arr2.length) {
			mergedArr[k] = arr2[j];
			k++;
			j++;
		}
		
		return mergedArr;
	}
	
	// union of two sorted arrays
	public static List unionOfArrays(int[] arr1, int[] arr2) {
		int i =0, j = 0, k = 0;
		//int[] unionArr = new int [arr1.length + arr2.length];
		List unionArr = new ArrayList();
		while(i < arr1.length && j < arr2.length) {
			if(arr1[i] < arr2[j]) {
				unionArr.add(arr1[i]);
				i++;
			} else if (arr2[j] < arr1[i]) {
				unionArr.add(arr2[j]);
				j++;
			} else {
				unionArr.add(arr2[j]);
				i++;
				j++;
			}
		}
		
		// remaining arr1 items
		while(i < arr1.length) {
			unionArr.add(arr1[i]);
			i++;
		}
		
		// reamining arr2 items
		while(j < arr2.length) {
			unionArr.add(arr2[j]);
			j++;
		}
		return unionArr;
		
	}
	
	
	// intersection of two sorted arrays
	public static List intersectionOfArrays(int[] arr1, int[] arr2) {
		int i = 0; int j = 0;
		List result = new ArrayList();
		while(i < arr1.length && j < arr2.length) {
			if(arr1[i] < arr2[j]) {
				i++;
			}
			else if (arr2[j] < arr1[i]) {
				j++;
			}
			else {
				result.add(arr1[i]);
				i++;
				j++;
			}
		}
		return result;
	}
	
	public static int binarySearch(int[] arr, int searchKey) {
		int i = 0; int j = arr.length;
		while(i < j) {
			int m = (i + j) / 2;
			if(arr[m] == searchKey) {
				return m;
			} else if(arr[m] > searchKey) {
				j = m - 1;	
			} else if(arr[m] < searchKey) {
				i = m;
			}
		}
		return -1;
	}
	
	// n is number missing from array arr
	public static int findMissingNumber(int[] arr, int n) {
		int total = (n*(n+1))/2;
		int sum = 0;
		for(int i = 0; i < arr.length; i++) {
			sum = sum + arr[i];
		}
		return total - sum;
	}
	
	public static void main(String[] args) {
		int arr1[] = {1, 3, 4, 5, 7};
        int arr2[] = {2, 3, 5, 6};
        
        List result = unionOfArrays(arr1, arr2);
        /*for(int i = 0 ; i < result.length; i++) {
        	System.out.print(result[i] + " ");
        }*/
        System.out.println(result);
        
        result = intersectionOfArrays(arr1, arr2);
        System.out.println(result);
        
        int[] a = {1,2,4,5,6};
        System.out.print(findMissingNumber(a, 6));
	}
	
	

}
