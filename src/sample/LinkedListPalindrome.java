package sample;

import java.util.concurrent.atomic.AtomicBoolean;

import sample.ReArrangeLinkedList.Node;

public class LinkedListPalindrome {

	// We can use a fast and slow pointer to get the center of the list
	// then reverse the second list and compare two sublists
	// The time is O(n) and space is O(1).

	// Palindrome : RADAR, MADAM which looks same after rotating

	public static void main(String[] args) {

		LinkedListPalindrome list = new LinkedListPalindrome();
		list.head = new Node("R");
		list.head.next = new Node("A");
		list.head.next.next = new Node("D");
		list.head.next.next.next = new Node("A");
		list.head.next.next.next.next = new Node("R");
		list.printlist(head); // print original list
		boolean result = list.checkPalindrome(head);
		System.out.println("result:: " + result);
	}

	static Node head; // head of the list

	/* Node Class */
	static class Node {

		String data;
		Node next;

		// Constructor to create a new node
		Node(String d) {
			data = d;
			next = null;
		}
	}

	void printlist(Node node) {
		if (node == null) {
			return;
		}
		while (node != null) {
			System.out.print(node.data + " -> ");
			node = node.next;
		}
	}

	static Node reverselist(Node node) {
		Node prev = null, curr = node, next;
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		node = prev;
		return node;
	}

	// Recursive function to check if two linked lists are equal or not
	public static boolean compare(Node a, Node b) {
		// see if both list is empty
		if (a == null && b == null) {
			return true;
		}

		return a != null && b != null && (a.data == b.data) && compare(a.next, b.next);
	}

	// Function to split the linked list into two equal parts and return
	// pointer to the second half
	public static Node findMiddle(Node head, AtomicBoolean odd) {
		Node prev = null;
		Node slow = head, fast = head;

		// find middle pointer
		while (fast != null && fast.next != null) {
			prev = slow;
			slow = slow.next;
			fast = fast.next.next;
		}

		// for odd nodes, fast still points to last node
		if (fast != null) {
			odd.set(true);
		}

		// make next of prev node null
		prev.next = null;

		// return mid node
		return slow;
	}

	// Function to check if linked list is palindrome or not
	public static boolean checkPalindrome(Node head) {
		// base case
		if (head == null) {
			return true;
		}

		// flag to indicate if number of nodes in the linked list is
		// odd or not. It will be passed by reference to findMiddle()
		AtomicBoolean odd = new AtomicBoolean(false);

		// find second half of linked list
		Node mid = findMiddle(head, odd);

		// if number of nodes is odd, advance mid
		if (odd.get()) {
			mid = mid.next;
		}

		// reverse the second half
		mid = reverselist(mid);

		// compare first and second half
		return compare(head, mid);
	}
}
