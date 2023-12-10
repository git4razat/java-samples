package rider;

import java.math.BigInteger;
import java.util.Arrays;

public class StreamsApp {
	public static void main(String[] args) {
		//List<Integer> list = Arrays.asList(8,4,5);
		//Map<String, TreeMap<Integer, String>> map = new HashMap();
		// streams once created can be only used once and will throw an exception if we try to use it again
		// stream is already used or consumed...
		
		/*list.stream().
			filter(n -> n%2 == 1).
				map(n-> n * 2).
				sorted().
					forEach(n -> System.out.println(n));
		
		
		int result = list.parallelStream().
			map(n -> n * 2).
			reduce(0, (c,e) -> c + e);
		
		System.out.println("result::" + result);*/
		
		/*if((int)Math.log10(number+1)!=(int)Math.log10(number)) {
            System.out.println("All 9s");
            System.out.println("Next Palidrome for the number "+ number +" is: "+(number+2));
            return;
       }*/
		
		
		System.out.println("99999::" + ( (int) Math.log10(999) + 1));
		System.out.println("100000:" + ( (int) Math.log10(1000) + 1));
		
		System.out.println("10::"+ (int) (Math.random() * 11));
		
		
		char a = 1;
		char b = 2;
		
		int a1 = (int) a;
		int b1 = (int) b;
		
		System.out.println("result::" + Integer.valueOf(a).equals(Integer.valueOf(b)));
		
		String s = "123";
		
		String[] str = s.split("");
		System.out.println("1::" + Arrays.toString(str));
		int[] numberArr = new int[s.length()];
		for (int i =0; i <= s.length() -1 ; i++) {
			System.out.println("i::" + i + "::" + str[i]);
			numberArr[i] = Integer.valueOf(str[i]);
		}
		
		System.out.println("2::" + Arrays.toString(numberArr));
	}

}
