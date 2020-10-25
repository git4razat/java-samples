package sample;

//Java program to print all permutations of a 
//given string. 
public class PrintAnagrams {
	public static void main(String[] args) {
		String str = "ABC";
		int n = str.length();
		PrintAnagrams permutation = new PrintAnagrams();
		permutation.permute(str, 0, n - 1);
	}

	/**
	 * permutation function
	 * 
	 * @param str string to calculate permutation for
	 * @param l   starting index
	 * @param r   end index
	 */
	private void permute(String str, int l, int r) {
		if (l == r)
			System.out.println(str);
		else {
			for (int i = l; i <= r; i++) {
				str = swap(str, l, i); // swapping
				permute(str, l + 1, r);
				str = swap(str, l, i); //backtracking
			}
		}
	}

	/**
	 * Swap Characters at position
	 * 
	 * @param a string value
	 * @param i position 1
	 * @param j position 2
	 * @return swapped string
	 */
	public String swap(String a, int i, int j) {
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}

}

//This code is contributed by Mihir Joshi 

/*
 * public class PrintAnagrams {
 * 
 * // Utility function to swap two characters in a character array private
 * static void swap(char[] ch, int i, int j) { char temp = ch[i]; ch[i] = ch[j];
 * ch[j] = temp; }
 * 
 * // Recursive function to generate all permutations of a String private static
 * void permutations(char[] ch, int currentIndex) { if (currentIndex ==
 * ch.length - 1) { System.out.println(String.valueOf(ch)); }
 * 
 * for (int i = currentIndex; i < ch.length; i++) { swap(ch, currentIndex, i);
 * permutations(ch, currentIndex + 1); swap(ch, currentIndex, i); } }
 * 
 * // generate all permutations of a String in Java public static void
 * main(String[] args) { String s = "ABC"; permutations(s.toCharArray(), 0); }
 * 
 * }
 */
