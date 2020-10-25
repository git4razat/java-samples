package sample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	static boolean compareLists(Node head1, Node head2) {

        if (head1 == null && head2 == null) {
            return true;
        } else if ((head1 == null && head2 != null) ||
            (head1 != null && head2 == null)) {
            return false;
        } else if (head1.data != head2.data) {
            return false;
        } else {
            return compareLists(head1.next, head2.next);
        }
    }

	
	static Node mergeLists(Node head1, Node head2) {

        if (head1 == null && head2 == null) {
            return null;
        } else if (head1 == null && head2 != null) {
            return head2;
        } else if (head1 != null && head2 == null) {
            return head1;
        } else if (head1.data < head2.data) {
            head1.next = mergeLists(head1.next, head2);
            return head1;
        } else {
        	Node temp = head2;
            head2 = head2.next;
            temp.next = head1;
            head1 = temp;
            head1.next = mergeLists(head1.next, head2);
            return head1;
        }
    }
	
	static int findMergeNode(Node head1, Node head2) {

        int m = findLength(head1);
        int n = findLength(head2);
        
        if (m < n) {
            Node temp = head1;
            head1 = head2;
            head2 = temp;
            m = findLength(head1);
            n = findLength(head2);
        }

        int d = m - n;

        for (int i =0 ; i < d ; i++) {
            head1 = head1.next;
        }

        while(head1 != null && head2 != null) {
            if (head1 == head2) {
                return head1.data;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return -1;
    }

    static int findLength(Node head) {
        if (head == null) {
            return 0;
        }
        int count = 0;
        Node node = head;
        while (node.next != null) {
            node = node.next;
            count++;
        }
        return count;
    }
    
    public static List<Integer> getIntersection(List<Integer> l1, List<Integer> l2) {
        List<Integer> list = new ArrayList<Integer>();

        for (Integer i : l1) {
            if(l2.contains(i)) {
                list.add(i);
            }
        }

        return list;
    }
    
    public static List<Integer> getUnion(List<Integer> l1, List<Integer> l2) {
        Set<Integer> result = new HashSet<Integer>();
        result.addAll(l1);
        result.addAll(l2);
        return new ArrayList<Integer>(result);
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