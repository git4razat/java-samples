package sample2026;

import java.util.HashMap;
import java.util.Map;

public class MinWindowSubString {

    // Time Complexity: O(N + M)
    // Space Complexity: O(1)
    public String minWindow(String s, String t) {
        int windowStart = 0;
        int start = 0;
        int minLength = Integer.MAX_VALUE;
        int matched = 0;

        Map<Character, Integer> tmap = new HashMap<>();
        for (char ch: t.toCharArray()) {
            tmap.put(ch, tmap.getOrDefault(ch, 0) + 1);
        }

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char currChar = s.charAt(windowEnd);
            if (tmap.containsKey(currChar)) {
                tmap.put(currChar, tmap.get(currChar) -1);
                if (tmap.get(currChar) >= 0) matched++;
            }

            //shrink or expand the window
            while(matched == t.length()) {
                if (minLength > (windowStart - windowEnd + 1)) {
                    minLength = (windowStart - windowEnd + 1);
                    start = windowStart;
                }

                char leftChar = s.charAt(windowStart);
                windowStart++;

                if (tmap.containsKey(leftChar)) {
                    if(tmap.get(leftChar) == 0) matched--;
                    tmap.put(leftChar, tmap.get(leftChar) +1);
                }
            }
        }
        if (minLength > s.length()) return "";
        return s.substring(start, start+minLength);
    }
}
