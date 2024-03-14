package sample;

import java.util.PriorityQueue;
import java.util.Comparator;

public class MergeKSortedLists {

	// K Linked List of size N
	//Time Complexity: O(N * log K) - optimized
	//Auxiliary Space: O(N)
	public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (ListNode head: lists) {
            while(head != null) {
                minHeap.add(head.val);
                head = head.next;
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while(!minHeap.isEmpty()) {
            int val = minHeap.poll();
            ListNode node = new ListNode(val);
            current.next = node;
            current = current.next;
        }
        return dummy.next;
    }
	
	public static void printList(ListNode node)
    {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }
	
	 
	
	public static void main(String[] args) {
		int N = 3;
	       
        // array to store head of linkedlist
		ListNode[] a = new ListNode[N];
       
        // Linkedlist1
		ListNode head1 = new ListNode(1);
        head1.next = new ListNode(3);
        head1.next.next = new ListNode(5);
        head1.next.next.next = new ListNode(7);
        a[0] = head1;
       
        // Limkedlist2
        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(4);
        head2.next.next = new ListNode(6);
        head2.next.next.next = new ListNode(8);
        a[1] = head2;
       
        // Linkedlist3
        ListNode head3 = new ListNode(0);
        head3.next = new ListNode(9);
        head3.next.next = new ListNode(10);
        head3.next.next.next = new ListNode(11);
        a[2] = head3;
          
        ListNode res = mergeKLists(a);
 
        if (res != null)
        	printList(res);
        System.out.println();
	}
	
	

}

class ListNode {
	int val;
	ListNode next;

	public ListNode(int val) {
		this.val = val;
	}

}