package sample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TrieNode {
	String value;
	HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
	boolean isLeaf;

	public TrieNode() {}

	public TrieNode(String value) {
		this.value = value;
	}
}

public class Trie {
	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	// method to append character
	public TrieNode add(TrieNode node, char character) {
		String s;
		if (node.value == null) {
			s = Character.toString(character);
		} else {
			s = node.value + character;
		}
		TrieNode t = new TrieNode(s);
		HashMap<Character, TrieNode> children = node.children;
		children.put(character, t);
		return t;
	}

	// Inserts a word into the trie.
	public void insert(String word) {
		HashMap<Character, TrieNode> children = root.children;
		TrieNode t = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (children.containsKey(c)) {
				t = children.get(c);
			} else {
				t = add(t, c);
			}
			children = t.children;
			// set leaf node
			if (i == word.length() - 1)
				t.isLeaf = true;
		}
	}

	// Returns if the word is in the trie.
	public boolean search(String word) {
		TrieNode t = searchNode(word);

		if (t != null && t.isLeaf)
			return true;
		else
			return false;
	}

	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix) {
		if (searchNode(prefix) == null)
			return false;
		else
			return true;
	}

	public TrieNode searchNode(String str) {// ant
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
	
	// auto suggestion , this needs value at trieNode level

	public Collection<String> autoComplete(String prefix) {
		Map<Character, TrieNode> children = root.children;
		TrieNode t = null;
		for (int i = 0; i < prefix.length(); i++) {
			char c = prefix.charAt(i);
			if (children.containsKey(c)) {
				t = children.get(c);
				children = t.children;
			} else {
				return null;
			}
		}

		return allPrefixes(t);
	}

	protected Collection<String> allPrefixes(TrieNode t) {
		List<String> results = new ArrayList<String>();
		if (t.isLeaf) {
			results.add(t.value);
		}
		HashMap<Character, TrieNode> children = t.children;
		
		for (char key : children.keySet()) {
			TrieNode childNode = children.get(key);
			Collection<String> childPrefixes = allPrefixes(childNode);
			results.addAll(childPrefixes);
		}
		return results;
	}
	
	public static void main(String[] args) {
		Trie trie = new Trie();
		List<String> words = List.of("hello", "dog", "hell", "cat", "a", "hel","help","helps","helping");
		for (String word: words) {
			trie.insert(word);
		}
		
		System.out.println("dog::" +trie.search("dog"));
		System.out.println("doggy::" +trie.search("doggy"));
		
		
		System.out.println("hel::" +trie.autoComplete("hel"));
	}
}
