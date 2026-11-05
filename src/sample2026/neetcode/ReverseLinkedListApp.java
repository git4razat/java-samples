package sample2026.neetcode;

public class ReverseLinkedListApp {

    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public Node reverseListRecursive(Node head) {
        if (head.next == null) return head;

        Node node = head;
        Node rootNode = reverseListRecursive(node.next);
        node.next.next = node;
        node.next = null;
        return rootNode;
    }

    public Node reverseListIteratively(Node head) {
        Node current = head;
        Node prev = null;
        Node next = null;

        while(current != null) {
            next = current.next;
            current.next = prev;

            prev = current;
            current = next;
        }
        return prev;
    }

    public void printList(Node head) {
        Node current = head;
        while(current != null) {
            System.out.println(" " + current.data);
            current = current.next;
        }

    }

    public static void main(String[] args) {

        ReverseLinkedListApp app = new ReverseLinkedListApp();

        // 1. Create a linked list: 1 -> 2 -> 3 -> 4 -> 5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.println("Normal List ::");
        app.printList(head);

        Node reversedHeadNode = app.reverseListRecursive(head);

        System.out.println("Reversed List Recursive::");
        app.printList(reversedHeadNode);

        Node reversedHeadNode2 = app.reverseListIteratively(head);
        System.out.println("Reversed List Iteratively::");
        app.printList(reversedHeadNode);
    }

}
