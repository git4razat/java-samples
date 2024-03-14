package sample;

import java.util.Arrays;

public class StringsAnagrams {

	/*
	 * Sorting and Comparing Time Complexity of below solution is o(nlogn) Better to
	 * use alternate solution mentioned below that
	 * 
	 */
	static boolean areAnagrams(char[] str1, char[] str2) {
		// Get lenghts of both strings
		int n1 = str1.length;
		int n2 = str2.length;

		// If length of both strings is not same,
		// then they cannot be anagram
		if (n1 != n2)
			return false;

		// Sort both strings
		Arrays.sort(str1);
		Arrays.sort(str2);

		// Compare sorted strings
		for (int i = 0; i < n1; i++)
			if (str1[i] != str2[i])
				return false;

		return true;
	}

	/*
	 * Taking Sum (Add for first array and minus for second, result should be 0)
	 * Time Complexity of below solution is o(n) Better to use alternate solution
	 * mentioned below that
	 * 
	 */
	static boolean areAnagrams2(char[] str1, char[] str2) {
		// Get length of both strings
		int n1 = str1.length;
		int n2 = str2.length;

		// If length of both strings is not same,
		// then they cannot be anagram
		if (n1 != n2)
			return false;

		int count = 0;
		// Take sum of all characters of first String
		for (int i = 0; i < n1; i++) {
			count += str1[i];
		}
		
		// Subtract the Value of all the characters of second
		// String
		for (int i = 0; i < n2; i++) {
			count -= str2[i];
		}

		return (count == 0);
	}

}
