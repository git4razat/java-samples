package sample2026.neetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrieOptimised {
    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isLeaf = false;
    }

    TrieNode root;

    public TrieOptimised() {
        root = new TrieNode();
    }

    // Time Complexity: O(L), Space Complexity: O(L)
    public void insert(String word) {
        TrieNode t = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            t.children.putIfAbsent(c, new TrieNode());
            t = t.children.get(c);
        }
        t.isLeaf = true;
    }

    // Time Complexity: O(L), Space Complexity: O(1)
    public boolean search(String word) {
        TrieNode t = searchNode(word);
        return t != null && t.isLeaf;
    }

    // Time Complexity: O(P), Space Complexity: O(1)
    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }

    // Time Complexity: O(L) or O(P), Space Complexity: O(1)
    public TrieNode searchNode(String str) {
        TrieNode t = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            t = t.children.get(c);
            if (t == null) {
                return null;
            }
        }
        return t;
    }

    // Time Complexity: O(P + K * M), Space Complexity: O(K * M + H)
    // p - length of prefix, k - total complete words matching prefix,
    // m - avg length of those matching words, h - height of subtree below prefix...
    public List<String> autocomplete(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode t = searchNode(prefix); // Reuses searchNode cleanly

        if (t == null) {
            return results; // Returning an empty list is safer than null to avoid NullPointerExceptions
        }

        allPrefixes(t, new StringBuilder(prefix), results);
        return results;
    }

    // Helper DFS using a single StringBuilder and a single results list
    private void allPrefixes(TrieNode node, StringBuilder currentPath, List<String> results) {
        if (node.isLeaf) {
            results.add(currentPath.toString());
        }

        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            currentPath.append(entry.getKey());
            allPrefixes(entry.getValue(), currentPath, results);
            currentPath.deleteCharAt(currentPath.length() - 1); // Backtrack
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] dictionary = {"apple", "app", "application", "apt", "bat", "ball", "batman"};
        for (String word : dictionary) {
            trie.insert(word);
        }

        System.out.println("Suggestions for 'app': " + trie.autocomplete("app"));
        System.out.println("Suggestions for 'ba': " + trie.autocomplete("ba"));
        System.out.println("Suggestions for 'cat': " + trie.autocomplete("cat"));
        System.out.println("Search 'apple': " + trie.search("apple"));
        System.out.println("StartsWith 'ap': " + trie.startsWith("ap"));
    }
}
