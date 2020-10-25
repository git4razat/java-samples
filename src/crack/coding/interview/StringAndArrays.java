package crack.coding.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringAndArrays {

	public static void main(String[] args) {
		// isUnique("Hello World!!");
		// replaceChars("Hello World!!");
		System.out.println("compress::" + compress("wwwwaaadexxxxxxywww"));
	}

	// check whether chars in a string are unique or not.
	public static boolean isUnique(String input) {
		boolean[] charFlags = new boolean[128];
		char[] chArr = input.toCharArray();
		for (int i = 0; i < chArr.length; i++) {
			int val = input.charAt(i);
			System.out.println("Checking val at index: " + i + ":val:" + val);
			if (charFlags[val]) {
				return false;
			}
			charFlags[val] = true;
		}
		return true;
	}

	// method to check strings are permute of each other or not.
	// god and dog are permute of each other. assuming case sensitivity can be
	// ignored.
	static boolean isPermutation(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		if (sortChars(s).equals(sortChars(t))) {
			return true;
		}
		return false;
	}

	static char[] sortChars(String input) {
		char[] charArray = input.toCharArray();
		Arrays.sort(charArray);
		return charArray;
	}

	// urlify means replace space with %20 in the input string, pass char array with
	// length of array
	// and then replace char in reverse order .. else in java strings are immutable
	// and unwanted strings will be
	// created.
	static void replaceChars(String input) {

		char[] str = input.toCharArray();
		int trueLength = str.length;

		int spaceCount = 0;
		int i = 0;
		for (i = 0; i < str.length; i++) {
			if (str[i] == ' ') {
				spaceCount++;
			}
		}

		while (str[i - 1] == ' ') {
			spaceCount--;
			i--;
		}

		int totalLength = trueLength + (spaceCount * 2);

		for (int j = totalLength - 1; j >= 0; j--) {
			if (str[j] == ' ') {
				str[totalLength - 1] = '0';
				str[totalLength - 2] = '2';
				str[totalLength - 3] = '%';
				totalLength = totalLength - 3;
			} else {
				str[totalLength - 1] = str[j];
				totalLength = totalLength - 1;
			}
		}
	}

	// geeksogeeks forms palindrome but geeksforgeeks doesnot form palindrome
	// mirror image from start and end

	boolean canFormPalindrome(String str) {

		List<Character> list = new ArrayList<Character>();

		for (int i = 0; i < str.toCharArray().length; i++) {
			Character ch = str.charAt(i);
			if (!list.contains(ch)) {
				list.add(ch);
			} else {
				list.remove(ch);
			}
		}

		if ((str.length() % 2 == 0 && list.size() == 0) || str.length() % 2 == 1 && list.size() == 1) {
			return true;
		} else {
			return false;
		}
	}

	// https://www.geeksforgeeks.org/check-if-two-given-strings-are-at-edit-distance-one/
	static boolean isEditDistanceOne(String s1, String s2) {
		// Find lengths of given strings
		int m = s1.length(), n = s2.length();

		// If difference between lengths is
		// more than 1, then strings can't
		// be at one distance
		if (Math.abs(m - n) > 1)
			return false;

		int count = 0; // Count of edits

		int i = 0, j = 0;
		while (i < m && j < n) {
			// If current characters don't match
			if (s1.charAt(i) != s2.charAt(j)) {
				if (count == 1)
					return false;

				// If length of one string is
				// more, then only possible edit
				// is to remove a character
				if (m > n)
					i++;
				else if (m < n)
					j++;
				else // If lengths of both strings is same
				{
					i++;
					j++;
				}

				// Increment count of edits
				count++;
			}

			else // If current characters match
			{
				i++;
				j++;
			}
		}

		// If last character is extra
		// in any string
		if (i < m || j < n)
			count++;

		return count == 1;
	}

	// String compression

	public static String compress(String str) {
		int n = str.length();
		String result = "";
		for (int i = 0; i < n; i++) {

			// Count occurrences of current character
			int count = 1;
			while (i < n - 1 && str.charAt(i) == str.charAt(i + 1)) {
				count++;
				i++;
			}

			// Print character and its count
			result = result + str.charAt(i) + count;
			// System.out.print(str.charAt(i));
			// System.out.print(count);
		}
		return result;
	}

	// Function to rotate the matrix 90 degree clockwise
	// https://www.geeksforgeeks.org/rotate-a-matrix-by-90-degree-in-clockwise-direction-without-using-any-extra-space/
	/**
	 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
	 * 
	 * 
	 * 13 9 5 1 14 10 6 2 15 11 7 3 16 12 8 4
	 * 
	 */
	static void rotate90Clockwise(int a[][]) {

		// N*N matrix, 4*4 matrix
		int N = 4;

		// Traverse each cycle
		for (int i = 0; i < N / 2; i++) {
			for (int j = i; j < N - i - 1; j++) {

				// Swap elements of each cycle
				// in clockwise direction
				int temp = a[i][j];
				a[i][j] = a[N - 1 - j][i];
				a[N - 1 - j][i] = a[N - 1 - i][N - 1 - j];
				a[N - 1 - i][N - 1 - j] = a[j][N - 1 - i];
				a[j][N - 1 - i] = temp;
			}
		}
	}

	// https://www.geeksforgeeks.org/a-boolean-matrix-question/

	public static void modifyMatrix(int mat[][], int R, int C) {
		int row[] = new int[R];
		int col[] = new int[C];
		int i, j;

		/* Initialize all values of row[] as 0 */
		for (i = 0; i < R; i++) {
			row[i] = 0;
		}

		/* Initialize all values of col[] as 0 */
		for (i = 0; i < C; i++) {
			col[i] = 0;
		}

		/*
		 * Store the rows and columns to be marked as 1 in row[] and col[] arrays
		 * respectively
		 */
		for (i = 0; i < R; i++) {
			for (j = 0; j < C; j++) {
				if (mat[i][j] == 1) {
					row[i] = 1;
					col[j] = 1;
				}
			}
		}

		/*
		 * Modify the input matrix mat[] using the above constructed row[] and col[]
		 * arrays
		 */
		for (i = 0; i < R; i++) {
			for (j = 0; j < C; j++) {
				if (row[i] == 1 || col[j] == 1) {
					mat[i][j] = 1;
				}
			}
		}
	}

	/*
	 * Function checks if passed strings (str1 and str2) are rotations of each other
	 */
	//https://www.geeksforgeeks.org/a-program-to-check-if-strings-are-rotations-of-each-other/
	static boolean areRotations(String str1, String str2) {
		// There lengths must be same and str2 must be
		// a substring of str1 concatenated with str1.
		return (str1.length() == str2.length()) && ((str1 + str1).indexOf(str2) != -1);
	}
}
