package crack.coding.interview;

import java.util.HashSet;

public class AppLinkedList {
	
	class LinkedListNode {
		int data;
		LinkedListNode next;
	}

	
	void deleteDups(LinkedListNode head) {
		HashSet<Integer> set = new HashSet<Integer>();
		LinkedListNode previous = null ;
		LinkedListNode current = head ;
		while (current != null) {
			if (set.contains(current.data)) {
				previous.next = current.next;// important statement here
			} else {
				set.add(current.data);
				previous = current;
			}
			current = current.next;
		}

	}
	
	LinkedListNode getNthNodeFromTail(LinkedListNode head, int positionFromTail) {

        if(head == null) {
            return null;
        }

        LinkedListNode curr = head;
        LinkedListNode node = head;
        int count = 0;
        while(curr.next != null) {
            if(count >= positionFromTail) {
                node = node.next;
            }
            count++;
            curr = curr.next;
        }
        return node;
    }
	
	boolean deleteNode(LinkedListNode node) {
		
		if(node == null ||  node.next == null) {
			return false;
		}
		
		LinkedListNode next = node.next;
		node.data = next.data;
		node.next = next.next;
		return true;
	}
	
	
}
