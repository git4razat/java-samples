package sample;

import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsInAString {
	
	public static void main(String[] args) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String str = "Hi , this is a world of programming. Hello World. Hello Dear. Hello Everyone123.";
		Pattern p = Pattern.compile("[a-zA-z0-9]+");
		Matcher m = p.matcher(str);
		while(m.find()) {
			System.out.println(m.group());
			String word = m.group();
			if(map.containsKey(word)) {
				int value = map.get(word);
				value++;
				map.put(word,value);
			} else {
				map.put(word, 1);
			}
		}
		System.out.println(map);
		
		String s1 = "GeeksForGeeks";
		String s2 = "ForGeeksGeekz";
		
		char c1[] = s1.toCharArray();
		char c2[] = s2.toCharArray();
		boolean isAnagram = false;
		Arrays.sort(c1);
		Arrays.sort(c2);
		isAnagram = true;
        // Compare sorted strings 
        for (int i = 0; i < c1.length; i++) {
        	if (c1[i] != c2[i])  {
        		isAnagram = false;
        	}
        }
        System.out.println("Two arrays are anagrams ::"+ isAnagram); 
        
        
	}

}
