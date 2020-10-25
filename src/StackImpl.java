import java.util.Arrays;

public class StackImpl {
	
	// stack impl
	// push , pop and min 
	
	//1 2 3 4 5 6 7 8
	// 1->2->3->4->5
	// Head = 1
	// 2 -> 1
	
	Node head;
	Node minNode;
	
	public static void main(String args) {
		 StackImpl st = new StackImpl();
		 
	}
	
	public void initStack() {
		head = new Node(1);
	}
	
	public void push(int data, Node head) {
		if (head == null) {
			Node node = new Node(data);
			minNode = node;
			head = node;
			return;
		}
		
		Node node = new Node(data);
		Node temp = head;
		head.next = temp;
		head = node;
		if (node.data < minNode.data) {
			minNode = node;
		}
	}

	

}

 class Node {
	
	int data;
	Node next;
	
	Node(int data) {
		this.data = data;
		this.next = null;
	}
}
