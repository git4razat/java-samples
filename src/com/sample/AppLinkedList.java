package com.sample;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.PriorityQueue;


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
	
	public void find(int data) {}
	
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
	Node reverseIteratively(Node head) { 
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
	
	void deleteDups(Node head) {
		HashSet<Integer> set = new HashSet<Integer>();
		Node previous = null ;
		Node current = head ;
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
	
	// compare two lists
	boolean compareLists(Node head1, Node head2) {

        if (head1 == null && head2 == null) {
            return true;
        } else if ((head1 == null && head2 != null) ||
           (head2 == null && head1 != null)) {
            return false;
        } else if (head1.data != head2.data) {
            return false;
        } else {
            return compareLists(head1.next, head2.next);
        }
	}

	
	// merge two sorted list
	Node mergeLists(Node head1, Node head2) {
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
        	Node node = head2;
            head2 = head2.next;
            node.next = head1;
            head1 = node;
            head1.next = mergeLists(head1.next, head2);
            return head1;
        }
    }
	
	// merge k sorted lists
	//O(N * K * log K)  time - N is avg size of list and K is number of sorted list..
	// O(K) - space
	public Node mergeKLists(Node[] lists) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for (Node head : lists) {
			while (head != null) {
				queue.add(head.data);
				head = head.next;
			}
		}
		Node dummy = new Node(-1);
		Node head = dummy;
		while (!queue.isEmpty()) {
			head.next = new Node(queue.remove());
			head = head.next;
		}
		return dummy.next;
	}
	
	// get node from positionFromTail
	int getNode(Node llist, int positionFromTail) {
		// Write your code here
		if (llist == null) {
			return -1;
		}
		Node node = llist;
		Node pointer = llist;
		int count = 0;
		while (node.next != null) {
			if (count >= positionFromTail) {
				pointer = pointer.next;
			}
			node = node.next;
			count++;
		}
		return pointer.data;
	}
	
	// hasCycle with two pointers
	static boolean hasCycle(Node head) {
        
		Node curr = head;
		Node curr2 = head;
        
        while (curr2 != null && curr2.next != null) {
            curr2 = curr2.next.next;
            curr = curr.next;
            
            if (curr == curr2) {
                return true;
            }
        }
        return false;
    }
	
	// hasCycle with hashset
	static boolean hasCycle2(Node head) {
        if (head == null) {
            return false;
        }
        
        HashSet<Node> set = new LinkedHashSet<Node>();
        Node node = head;
        while (node.next != null) {
            if (set.contains(node)) {
                return true;    
            }
            set.add(node);
            node = node.next;
        }
        return false;
    }
	
	// merge point of two lists
	int findMergeNode(Node head1, Node head2) {
            int x = findLength(head1);
            int y = findLength(head2);
            if (x < y) {
            	Node temp = head1;
                head1 = head2;
                head2 = temp;
                x = findLength(head1);
                y = findLength(head2);
            }
            
            int d = x - y;
            
            for (int i = 0; i < d; i++) {
                head1 = head1.next;
            }
            
            while (head1 != null && head2 != null) {
                if (head1 == head2) {
                    return head1.data;
                }
                
                head1 = head1.next;
                head2 = head2.next;
            }
            return -1;
    }
	
	int findLength(Node head) {
        if (head == null) {
            return 0;
        }
        Node curr = head;
        int count = 0;
        while(curr.next != null) {
            curr = curr.next;
            count++;
        }
        return count;
    }
	
	// remove duplicates from sorted linked list
	static Node removeDuplicates(Node head) {

        if (head == null) {
            return null;
        }

        Node node = head;
        while (node != null) {
            while (node.next != null && node.data == node.next.data) {
                node.next = node.next.next;
            }
            node = node.next;
        }
        return head;
    }
	
	
	
	// findMid, reverse the second half and merge the two list
	void reorderList(Node head) {
		if (head == null || head.next == null)
		      return;
		
		//findMid
		Node mid = findMid(head);
		//reverse - use recursive function above..
		Node reversed = reverse(mid);
		//merge
		merge(head, reversed);
	}
	
	private Node findMid(Node head) {
		Node prev = null;
		Node slow = head;
		Node fast = head;

	    while (fast != null && fast.next != null) {
	      prev = slow;
	      slow = slow.next;
	      fast = fast.next.next;
	    }
	    prev.next = null;

	    return slow;
	  }
	
	private void merge(Node l1, Node l2) {
	    while (l2 != null) {
	      Node next = l1.next;
	      l1.next = l2;
	      l1 = l2;
	      l2 = next;
	    }
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
		
		
		
		/**
		 * // Linkedlist1
		ListNode head1 = new ListNode(1);
        head1.next = new ListNode(10);
        head1.next.next = new ListNode(15);
        head1.next.next.next = new ListNode(20);
        head1.next.next.next.next = new ListNode(25);
		 */
		
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