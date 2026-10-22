package sample2026;

import java.util.*;

public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int wordcount = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i =0; i < size; i++) {
                String currentWord  = queue.poll();
                if (currentWord.equals(endWord)) return wordcount++;
                char[] word = currentWord.toCharArray();
                for (int j =0; j < word.length; j++) {
                    char ch = word[j];
                    for (char k = 'a'; k <= 'z'; k++) {
                        word[j] = k;
                        String newWord = new String(word);
                        if (set.contains(newWord)) {
                            set.remove(newWord);
                            queue.add(newWord);
                        }
                    }
                    word[j] = ch;
                }
            }
            wordcount++;
        }
        return 0;
    }

    public static void main(String[] args) {
        // beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
        WordLadder ladder = new WordLadder();
        String[] words = {"hot","dot","dog","lot","log","cog"};
        System.out.println(ladder.ladderLength("hit", "cog", new ArrayList<>(Arrays.asList(words))));

        String[] words2 = {"hot","dot","dog","lot","log"};
        System.out.println(ladder.ladderLength("hit", "cog", new ArrayList<>(Arrays.asList(words2))));
    }
}
