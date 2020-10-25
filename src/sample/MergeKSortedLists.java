package sample;

import java.util.PriorityQueue;
import java.util.Comparator;

public class MergeKSortedLists {

	public static void main(String[] args) {

	}

	public ListNode mergeKLists(ListNode[] lists) {
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		for (ListNode head : lists) {
			while (head != null) {
				queue.add(head.val);
				head = head.next;
			}
		}
		ListNode dummy = new ListNode(-1);
		ListNode head = dummy;
		while (!queue.isEmpty()) {
			head.next = new ListNode(queue.remove());
			head = head.next;
		}
		return dummy.next;
	}

	/*
	 * public ListNode mergeKLists(ListNode[] lists) {
	 * if(lists==null||lists.length==0) return null;
	 * 
	 * PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(new
	 * Comparator<ListNode>(){ public int compare(ListNode l1, ListNode l2){ return
	 * l1.val - l2.val; } });
	 * 
	 * ListNode head = new ListNode(0); ListNode p = head;
	 * 
	 * for(ListNode list: lists){ if(list!=null) queue.offer(list); }
	 * 
	 * while(!queue.isEmpty()){ ListNode n = queue.poll(); p.next = n; p=p.next;
	 * 
	 * if(n.next!=null) queue.offer(n.next); }
	 * 
	 * return head.next;
	 * 
	 * }
	 */

}

class ListNode {
	int val;
	ListNode next;

	public ListNode(int val) {
		this.val = val;
	}

}