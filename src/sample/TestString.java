package sample;

public class TestString {

	public static void main(String[] args) {
		String str = "hello world";
		boolean chars[] = new boolean[256];
		for(int i = 0; i < str.length(); i++) {
			int j = str.charAt(i);
			chars[j] = true;
		}
		System.out.println("Final::"+ chars.toString());
	}

}
