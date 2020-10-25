package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ArrayOperations {

	public static void main(String[] args) {

		/*
		 * HashSet<Integer> h = new HashSet<Integer>(); h.add(1); h.add(1); h.add(2);
		 * System.out.println(h);
		 */

		int[] a = { 1, 2, 3, 5 };
		int n = 4;
		int x1 = a[0];
		int x2 = 1;

		/*
		 * For xor of all the elements in array
		 */
		for (int i = 1; i < n; i++) {
			System.out.println("i:" + i + ":x1:" + x1 + ":a[i]:" + a[i]);
			x1 = x1 ^ a[i];
			System.out.println("after x0r :                x1:" + x1);
		}

		System.out.println("====================");

		/*
		 * For xor of all the elements from 1 to n+1
		 */
		for (int i = 2; i <= n + 1; i++) {
			System.out.println("i:" + i + ":x2:" + x2 + ":i:" + i);
			x2 = x2 ^ i;
			System.out.println("after x0r : x2:" + x2);
		}

		System.out.println(x2 ^ x1);

	}

	// Merge Two Sorted Arrays
	// {1,2,3,4}, {5,6,7,8} => {1,2,3,4,5,6,7,8}
	int[] merge(int[] arr1, int[] arr2) {
		int[] result = new int[arr1.length + arr2.length];
		int i = 0, j = 0, k = 0;
		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] < arr2[j]) {
				result[k] = arr1[i];
				i++;
				k++;
			} else {
				result[k] = arr2[j];
				j++;
				k++;
			}
		}
		while (i < arr1.length) {
			result[k] = arr1[i];
			i++;
			k++;
		}
		while (j < arr2.length) {
			result[k] = arr2[j];
			j++;
			k++;
		}
		return result;
	}

	// union of two sorted arrays
	// {1,2,3,4}, {3,4,5,6} => {1,2,3,4,5,6}
	int[] getUnion(int[] arr1, int[] arr2) {
		int[] result = new int[arr1.length + arr2.length]; // or take a list
		int i = 0, j = 0, k = 0;

		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] < arr2[j]) {
				result[k] = arr1[i];
				i++;
				k++;
			} else if (arr1[i] > arr2[j]) {
				result[k] = arr2[j];
				j++;
				k++;
			} else { // equals
				result[k] = arr1[i];
				k++;
				i++;
				j++;
			}
		}

		while (i < arr1.length) {
			result[k] = arr1[i];
			i++;
			k++;
		}

		while (j < arr2.length) {
			result[k] = arr2[j];
			j++;
			k++;
		}
		return result;
	}

	// intersection of two sorted arrays
	// {1,2,3,4}, {3,4,5,6} => {3,4}
	// should remove duplicates
	int[] getIntersection(int[] arr1, int[] arr2) {
		List<Integer> result = new ArrayList<Integer>();
		int i = 0, j = 0, k = 0;

		while (i < arr1.length && j < arr2.length) {
			if (arr1[i] < arr2[j]) {
				i++;
			} else if (arr1[i] > arr2[j]) {
				j++;
			} else {
				// result[k] = arr1[i];
				// if (!result.contains((arr1[i]))) {
				// result.add(arr1[i]);
				// }
				result.add(arr1[i]);
				i++;
				j++;
			}
		}

		int[] finalRes = result.stream().mapToInt(x -> (int) x).toArray();
		return finalRes;
	}

	// check whether sum of any two numbers from two arrays is a target value or not
	// arr1 - {1,2,3}, arr2 - {10,20,30}, targetVal - 32
	// brute force is o(n2), iterate all elements of arr1 for all elements of arr2
	// optimized solution is iterate over one array and place the content of
	// (targetVal - arr1[i]) in a hashset
	// then iterate over arr2 and just check if any element of arr2 is available in
	// hashset
	// complexity o(m+n)
	// space complexity o(n)

	boolean sumOfTwo(int[] arr1, int[] arr2, int targetVal) {
		HashSet<Integer> complementSet = new HashSet<Integer>();
		for (int i = 0; i < arr1.length; i++) {
			int complementValue = targetVal - arr1[i];
			complementSet.add(complementValue);
		}

		for (int j = 0; j < arr2.length; j++) {
			if (complementSet.contains(arr2[j])) {
				return true;
			}
		}
		return false;
	}

	// check whether tripplte exists which is equal to provided sum in an array
	// if exists return true else false
	// in some questions, we may need to return all possible tripplets as well.
	boolean sumOfThree(int[] arr, int sum) {
		// brute force will be nested 3 for loops
		// time complexity - o(n3)
		// optimized solution
		// first we need to sort array and then take three pointers as shown below
		// time complexity - o(n2)
		Arrays.sort(arr);
		int length = arr.length;

		for (int i = 0; i < length - 2; i++) {
			int start = i + 1;
			int end = length - 1;

			while (start < end) {
				if (arr[i] + arr[start] + arr[end] == sum) {
					// if we have to find all possible tripplets, we jjust need to add this
					// combination of
					// arr[i], arr[start], arr[end] and not return true;
					// but increment start and decrement end;
					return true;
				} else if (arr[i] + arr[start] + arr[end] < sum) {
					start++;
				} else {
					end--;
				}
			}
		}
		return false;
	}

	// Largest Sum Contiguous Subarray
	// Write an efficient program to find the sum of contiguous subarray
	// within a one-dimensional array of numbers which has the largest sum.
	// kadene's algorithm will be used
	// https://www.youtube.com/watch?v=YxuK6A3SvTs
	// time complexity o(n)
	int maxSubArraySum(int[] arr) {
		int size = arr.length;
		int ans = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i < size; i++) {
			sum = sum + arr[i];
			if (sum < arr[i]) {
				sum = arr[i];
			}
			if (ans < sum) {
				ans = sum;
			}
		}
		return ans;
	}

	// how many subArrays have sum equal to k
	// [1,1,1] k : 2
	// output : 2
	// [1,1,1,-1,1] k: 2
	// output : 4
	// https://www.youtube.com/watch?v=AmlVSNBHzJg
	int subArraysSum(int[] arr, int k) {

		int sum = 0;
		int result = 0;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < arr.length; i++) {
			sum = sum + arr[i];
			if (map.containsKey(sum - k)) {
				result = result + map.get(sum - k);
			}
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return result;
	}

	// https://www.youtube.com/watch?v=6KHW7ZQwtCA
	// find missing number in an array of natural numbers of size n
	// alternate approach of hashing , store everything in hash with value flag
	// https://www.youtube.com/watch?v=Dq0jQX3YNKg
	int findMissingNumber(int[] arr, int n) {
		// method 1 - hashing - check video
		// create hashmap witk key as 1,2,3,4,5 and values as false
		// iterate over array and whichevere number is avl , mark correspodning value as
		// true
		// after that we just need to find entry in hashmap with false.
		// time complexity - o(n) and space o(n)

		// method 2 - sum of natural numbers -- there is a chance of overflow if array
		// is huge.
		// time - o(n) and space o(1)
		int sum_natural = (n * (n + 1) / 2);
		int sum_arr = 0;
		for (int i : arr) {
			sum_arr = sum_arr + i;
		}
		return (sum_natural - sum_arr);

		// third method , using XOR, this will not have overflow scenario
		// take all integers till range 0-n
		// Do a XOR with similar elements of array and this will give u value which is
		// not there
		// check below method
	}

	// Function to check if an array is
	// subarray of another array
	static boolean isSubArray(int A[], int B[], int n, int m) {
		// Two pointers to traverse the arrays
		int i = 0, j = 0;

		// Traverse both arrays simultaneously
		while (i < n && j < m) {

			// If element matches
			// increment both pointers
			if (A[i] == B[j]) {

				i++;
				j++;

				// If array B is completely
				// traversed
				if (j == m)
					return true;
			}

			// If not,
			// increment i and reset j
			else {
				i = i - j + 1;
				j = 0;
			}
		}
		return false;
	}

	// Function to find missing number
	// https://www.youtube.com/watch?v=Dq0jQX3YNKg
	static int getMissingNo(int a[], int n) {
		int x1 = a[0];
		int x2 = 1;

		/*
		 * For xor of all the elements in array
		 */
		for (int i = 1; i < n; i++)
			x1 = x1 ^ a[i];

		/*
		 * For xor of all the elements from 1 to n+1
		 */
		for (int i = 2; i <= n + 1; i++)
			x2 = x2 ^ i;

		return (x1 ^ x2);
	}

	void printMissingAndDuplicateNumbers() {
		// To Do Pending
	}

	// Array elements value -> 1 to n
	// no 0 and no duplicates, this is the tricky part
	// https://www.youtube.com/watch?v=aMsSF1Il3IY
	List<Integer> findDuplicatesFromArray(int[] arr) {
		List<Integer> output = new ArrayList<Integer>();
		for (int i = 0; i < arr.length - 1; i++) {
			int index = arr[i] - 1;
			if (arr[index] < 0) {
				output.add(arr[index] + 1);
			}
			arr[index] = -arr[index];
		}
		return output;
	}

}
