package sample2026.neetcode;

public class MergeLinkedListApp {
//    static class SinglyLinkedListNode {
//        int data;
//        SinglyLinkedListNode next;
//
//        public SinglyLinkedListNode(int data)  {
//            this.data = data;
//        }
//    }

    static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    public SinglyLinkedListNode mergeTwoList(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        if (head1 == null && head2 != null) {
            return head2;
        } else if (head2 == null && head1 != null) {
            return head1;
        } else if (head1 == null && head2 == null) {
            return null;
        } else if (head1.data < head2.data) {
            head1.next = mergeTwoList(head1.next, head2);
            return head1;
        } else {
            SinglyLinkedListNode temp = head2;
            head2 = head2.next;
            temp.next = head1;
            head1 = temp;
            head1.next = mergeTwoList(head1.next, head2);
            return head1;
        }
    }

    public void printList(SinglyLinkedListNode head) {
        SinglyLinkedListNode current = head;
        while(current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        MergeLinkedListApp app = new MergeLinkedListApp();
        SinglyLinkedListNode head1 = new SinglyLinkedListNode(1);
        head1.next = new SinglyLinkedListNode(2);
        head1.next.next = new SinglyLinkedListNode(4);
        System.out.println("<<List 1>>");
        app.printList(head1);

        SinglyLinkedListNode head2 = new SinglyLinkedListNode(1);
        head2.next = new SinglyLinkedListNode(3);
        head2.next.next = new SinglyLinkedListNode(4);
        System.out.println("<<List 2>>");
        app.printList(head2);

        SinglyLinkedListNode result = app.mergeTwoList(head1, head2);
        System.out.println("<<Merged List>>");
        app.printList(result);
    }
}
