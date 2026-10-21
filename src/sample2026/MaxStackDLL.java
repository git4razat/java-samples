package sample2026;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MaxStackDLL {

    // doubly linked list node definition
    private class Node {
        private int value;
        private Node next;
        private Node prev;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;

    private TreeMap<Integer, List<Node>> map;

    public MaxStackDLL() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        map = new TreeMap<>();
    }

    public void push(int x) {
        Node node = new Node(x);
        // add as last element of linked list
        Node lastNode = tail.prev;
        lastNode.next = node;
        tail.prev = node;
        node.next = tail;
        node.prev = lastNode;
        // add to map
        map.computeIfAbsent(x, k -> new ArrayList<>()).add(node);
    }

    public int pop() {
        Node lastNode = tail.prev;
        // remove from linked list
        removeNode(lastNode);

        // remove from map
        List<Node> list = map.get(lastNode.value);
        list.remove(list.size() -1);
        if(list.isEmpty()) map.remove(lastNode.value);

        return lastNode.value;
    }

    public int top() {
        return tail.prev.value;
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int maxVal = map.lastKey();

        // remove from map for maxVal
        List<Node> maxNodes = map.get(maxVal);
        Node maxNode = maxNodes.remove(maxNodes.size() - 1);
        if (maxNodes.isEmpty()) map.remove(maxVal);

        // remove from dll
        removeNode(maxNode);

        return maxVal;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public static void main(String[] args) {
        MaxStackDLL maxStackObj = new MaxStackDLL();

        System.out.println("--- Test 1: Sequential Validation ---");
        maxStackObj.push(5);
        maxStackObj.push(1);
        maxStackObj.push(5);
        maxStackObj.push(3);

        System.out.println("Current Top (Expected: 3): " + maxStackObj.top());
        System.out.println("Current Max (Expected: 5): " + maxStackObj.peekMax());

        // Pop the top-most max element (the second 5 we pushed)
        System.out.println("Popping Max (Expected: 5): " + maxStackObj.popMax());

        // After removing that 5, the top element should still be 3,
        // and the running max should fall back to the first 5 we pushed
        System.out.println("New Top after popMax (Expected: 3): " + maxStackObj.top());
        System.out.println("New Max after popMax (Expected: 5): " + maxStackObj.peekMax());

        System.out.println("Standard Pop (Expected: 3): " + maxStackObj.pop());
        System.out.println("New Top after pop (Expected: 1): " + maxStackObj.top());
        System.out.println("New Max after pop (Expected: 5): " + maxStackObj.peekMax());
        System.out.println();
    }
}
