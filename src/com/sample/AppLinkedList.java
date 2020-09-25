package com.sample;

class Node {
	int data;
	Node next;
	
	public Node(int data) {
		this.data = data;
	}
	
	public String toString() {
		return this.data + " ";
	}
}

class LinkedList {
	
	Node head;
	
	int nElements = 0;
	
	public LinkedList(Node head) {
		this.head = head;
	}
	
	public void insertToHead(int data) {
		Node node = new Node(data);
		if(head == null) {
			head = node;
			return;
		}
		
		Node curr = head;
		node.next = curr;
		head = node;
	}
	
	public void insertAt(int position, int data) {
		Node node = new Node(data);
		if(head == null) {
			head = node;
			return;
		}
		
		Node curr = head;
		int linkPosition = 0;
		
		while(curr.next != null) {
			System.out.println("");
			if(linkPosition == (position - 1)) {
				node.next =  curr.next;
				curr.next = node;
				break;
			}
			curr = curr.next;
			linkPosition++;
		}
	}
	
	public boolean deleteFirst() {
		if(head == null) {
			return false;
		}
		Node curr = head;
		head = curr.next;
		return true;
	}
	
	public void find(int data) {
		
	}
	
	public void display() {
		if(head == null) {
			return;
		}
		Node current = head;
		while(current != null) {
			System.out.print(current);
			current = current.next;
		}
	}
	
	//HEAD
	//20 -> 15 -> 10 -> 60 -> 1 

							//HEAD
	//20 <- 15 <- 10 <- 60 <- 1
	//recursion
	public Node reverse(Node head) {
		if(head.next == null) {
	        return head;
	    }
	    Node node = head;
	    Node rootNode = reverse(head.next);
	    node.next.next = node;
	    node.next = null;
	    return rootNode;
	}
	
	// iterative approach
	Node reverseIteratively(Node head) 
    { 
        Node prev = null; 
        Node current = head; 
        Node next = null; 
        while (current != null) { 
            next = current.next; 
            current.next = prev; 
            prev = current; 
            current = next; 
        } 
        head = prev; 
        return head; 
    }	
}

class AppLinkedList {
	
	Node head;
	
	public static void main(String[] args) {
		LinkedList list = new LinkedList(null);
		list.insertToHead(1);
		list.insertToHead(10);
		list.insertToHead(15);
		list.insertToHead(20);
		list.insertToHead(25);
		
		list.display();
		
		System.out.println("\n\nDeleting First Element \n");
		
		list.deleteFirst();
		list.display();
		
		System.out.println("\n\nInserting  Element At 0 \n");
		list.insertAt(0,50);
		list.display();
		
		System.out.println("\n\nInserting  Element At 3 \n");
		list.insertAt(3,60);
		list.display();
	}
}