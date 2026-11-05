package sample2026.neetcode;

import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedListApp {

    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public Node mergeKSortedLists(List<Node> lists) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (Node head: lists) {
            while(head != null) {
                minHeap.add(head.data);
                head = head.next;
            }
        }

        Node dummyNode = new Node(-1);
        Node current = dummyNode;

        while(!minHeap.isEmpty()) {
            int data = minHeap.poll();
            Node node = new Node(data);
            current.next = node;
            current = current.next;
        }
        return dummyNode.next;
    }
}
