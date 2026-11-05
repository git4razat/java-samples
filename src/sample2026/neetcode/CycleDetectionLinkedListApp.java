package sample2026.neetcode;

public class CycleDetectionLinkedListApp {

    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public boolean hasCycle(Node head) {
        if (head == null) return false;

        Node current = head;
        Node current2 = head;

        while (current2 != null && current2.next != null) {
            current = current.next;
            current2 = current2.next.next;

            if (current == current2) return true;
        }

        return false;
    }

    public void printList(MergeLinkedListApp.SinglyLinkedListNode head) {
        MergeLinkedListApp.SinglyLinkedListNode current = head;
        while(current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        CycleDetectionLinkedListApp app = new CycleDetectionLinkedListApp();

        Node head = new Node(3);
        Node cycleNode = new Node(2);
        head.next = cycleNode;
        head.next.next = new Node(0);
        head.next.next.next = new Node(-4);
        head.next.next.next.next = cycleNode;

        System.out.println(app.hasCycle(head));

        Node head1 = new Node(3);
        head1.next = new Node(2);
        head1.next.next = new Node(0);
        head1.next.next.next = new Node(-4);
        head1.next.next.next.next = new Node(5);

        System.out.println(app.hasCycle(head1));
    }
}
