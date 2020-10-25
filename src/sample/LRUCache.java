package sample;

import java.util.HashMap;

class LRUNode {
	int key;
	int value;
	LRUNode prev;
	LRUNode next;

	public LRUNode(int key, int value) {
		this.key = key;
		this.value = value;
	}
}

public class LRUCache {

	LRUNode head;
	LRUNode tail;
	HashMap<Integer, LRUNode> map = null;
	int cap = 0;

	public LRUCache(int capacity) {
		this.cap = capacity;
		this.map = new HashMap<>();
	}

	public int get(int key) {
		if (map.get(key) == null) {
			return -1;
		}

		LRUNode t = map.get(key);
		// remove node and add to tail
		removeLRUNode(t);
		offerLRUNode(t);

		return t.value;
	}

	public void put(int key, int value) {
		if (map.containsKey(key)) {
			LRUNode t = map.get(key);
			t.value = value;

			// move to tail
			removeLRUNode(t);
			offerLRUNode(t);
		} else {
			if (map.size() >= cap) {
				// delete head
				map.remove(head.key);
				removeLRUNode(head);
			}

			// add to tail
			LRUNode node = new LRUNode(key, value);
			offerLRUNode(node);
			map.put(key, node);
		}
	}

	private void removeLRUNode(LRUNode n) {
		if (n.prev != null) {
			n.prev.next = n.next;
		} else {
			head = n.next;
		}

		if (n.next != null) {
			n.next.prev = n.prev;
		} else {
			tail = n.prev;
		}
	}

	private void offerLRUNode(LRUNode n) {
		if (tail != null) {
			tail.next = n;
		}

		n.prev = tail;
		n.next = null;
		tail = n;

		if (head == null) {
			head = tail;
		}
	}

}
