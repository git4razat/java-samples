package sample2026.neetcode;

import java.util.HashMap;
import java.util.Map;

public class WordDictionary {

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isLeaf = false;
    }

    private final TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    // Time: O(L), Space: O(L)
    public void addWord(String word) {
        TrieNode t = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            t.children.putIfAbsent(c, new TrieNode());
            t = t.children.get(c);
        }
        t.isLeaf = true;
    }

    // Time: O(L) without '.', up to O(N) with '.', Space: O(L)
    public boolean search(String word) {
        return isMatch(root, word, 0);
    }

    private boolean isMatch(TrieNode trieNode, String word, int index) {
        if (index == word.length()) {
            return trieNode.isLeaf;
        }

        char c = word.charAt(index);
        if (c == '.') {
            for (TrieNode node : trieNode.children.values()) {
                if (isMatch(node, word, index + 1)) {
                    return true; // Early exit on first match
                }
            }
            return false;
        } else {
            TrieNode next = trieNode.children.get(c);
            return next != null && isMatch(next, word, index + 1);
        }
    }
}
