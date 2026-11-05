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

    public Node detectCycleAndReturnCycleNode(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;
        boolean hasCycle = false;

        // Phase 1: Determine if a cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        // If no cycle was found, return null
        if (!hasCycle) {
            return null;
        }

        // Phase 2: Find the entry point of the cycle
        fast = head; // Reset one pointer to head
        while (slow != fast) {
            slow = slow.next; // Move both at the same speed (1 step)
            fast = fast.next;
        }

        return slow; // Both pointers now point to the start of the cycle
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
        Node cycleNd = app.detectCycleAndReturnCycleNode(head);
        System.out.println(cycleNode);
        if (cycleNd != null) System.out.println(cycleNd.data);

        Node head1 = new Node(3);
        head1.next = new Node(2);
        head1.next.next = new Node(0);
        head1.next.next.next = new Node(-4);
        head1.next.next.next.next = new Node(5);

        System.out.println(app.hasCycle(head1));
    }
}
