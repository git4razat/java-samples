package sample2026.neetcode;

import java.util.*;

public class Trie {

    private static class TrieNode {
        String value;
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isLeaf = false;

        TrieNode() {}

        TrieNode(String value) {
            this.value = value;
        }
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public TrieNode add(TrieNode node, char character) {
        String s;

        if (node.value == null) {
            s = Character.toString(character);
        } else {
            s = node.value + character;
        }

        TrieNode t = new TrieNode(s);
        Map<Character, TrieNode> children = node.children;
        children.put(character, t);
        return t;
    }

    // time complexity - o(m) - m ins the length of string and space - o(m) as every char needs a new node...
    public void insert(String word) {
        Map<Character, TrieNode> children = root.children;
        TrieNode t = root;
        for (int i  =0 ; i < word.length(); i++) {
            char c = word.charAt(i);
            if (children.containsKey(c)) {
                t = children.get(c);
            } else {
                t = add(t, c);
            }
            children = t.children;
            if (i == word.length() -1) t.isLeaf = true;
        }
    }

    // time complexity - o(m) - m is the length of string and space - o(1)
    public boolean search(String word) {
        TrieNode t = searchNode(word);
        if (t != null && t.isLeaf == true) return true;
        else return false;
    }

    // time complexity - o(p) - m is the length of prefix and space - o(1)
    public boolean startsWith(String prefix) {
        TrieNode t = searchNode(prefix);
        if (t != null) return true;
        else return false;
    }

    public TrieNode searchNode(String str) {
        Map<Character, TrieNode> children = root.children;
        TrieNode t = null;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (children.containsKey(c)) {
                t = children.get(c);
                children = t.children;
            } else {
                return null;
            }
        }
        return t;
    }

    // time complexity o(p + k*m) , space - o(k*m + h)
    public List<String> autocomplete(String prefix) {
        TrieNode t = root;
        Map<Character, TrieNode> children = t.children;
        for (char ch: prefix.toCharArray()) {
            if(children.containsKey(ch)) {
                t = children.get(ch);
                children = t.children;
            } else {
                return null;
            }
        }
        return allPrefixes(t);
    }

    private List<String> allPrefixes(TrieNode node) {
        List<String> results = new ArrayList<>();
        if (node.isLeaf) {
            results.add(node.value);
        }

        for (Map.Entry<Character, TrieNode> childNode: node.children.entrySet()) {
            Collection<String> prefixResults = allPrefixes(childNode.getValue());
            results.addAll(prefixResults);
        }
        return results;
    }

    public static void main(String[] args) {
        // Populate the Trie with sample words
        Trie trie = new Trie();
        String[] dictionary = {"apple", "app", "application", "apt", "bat", "ball", "batman"};
        for (String word : dictionary) {
            trie.insert(word);
        }

        // Test autocomplete queries
        System.out.println("Suggestions for 'app': " + trie.autocomplete("app"));
        System.out.println("Suggestions for 'ba': " + trie.autocomplete("ba"));
        System.out.println("Suggestions for 'cat': " + trie.autocomplete("cat"));
    }
}
