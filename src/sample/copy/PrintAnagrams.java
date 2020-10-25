package sample.copy;

public class PrintAnagrams {

	public static void main(String[] args) {
		String str = "ABC";
		permute(str, 0, str.length() - 1);
	}

	static void permute(String str, int l, int r) {

		if (l == r) {
			System.out.println(str);
		}

		else {
			for (int i = l; i <= r; i++) {
				str = swap(str, l, i);
				permute(str, l + 1, r);
				str = swap(str, l, i);
			}

		}
	}

	static String swap(String str, int i, int j) {
		char[] chars = str.toCharArray();
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
		return String.copyValueOf(chars);
	}

}
