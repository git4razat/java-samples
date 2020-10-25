package crack.coding.interview;

import java.util.HashMap;

class TrieNode {
	HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
	boolean isLeaf = false;
}

public class Trie {
	
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	public void insert(String word) {
		HashMap<Character, TrieNode> children = root.children;
		
		for(int i = 0 ; i < word.length(); i++) {
			TrieNode trieNode;
			char c = word.charAt(i);
			if(children.containsKey(c)) {
				trieNode = children.get(c);
			} else {
				trieNode = new TrieNode();
				children.put(c, trieNode);
			}
			children = trieNode.children;
			
			if(i == word.length() -1) {
				trieNode.isLeaf = true;
			}
		}
		
	}
	
	public boolean search(String word) {
		TrieNode t = searchWord(word);
		if( t!= null && t.isLeaf) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean searchStartsWith(String word) {
		TrieNode t = searchWord(word);
		if( t!= null) {
			return true;
		} else {
			return false;
		}
	}
	
	public TrieNode searchWord(String word) {
		HashMap<Character, TrieNode> children = root.children;
		TrieNode t = null;
		for(int i = 0; i < word.length(); i++) {
			
			char c = word.charAt(i);
			if(children.containsKey(c)) {
				t = children.get(c);
				children = t.children;
			} else {
				return null;
			}
		}
		return t;
	}

}
