package sample2026;

import java.util.Stack;
import java.util.concurrent.locks.ReentrantLock;

public class MaxStack {

    private Stack<Integer> stack;
    private Stack<Integer> maxStack;
    private ReentrantLock lock;

    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
        lock = new ReentrantLock();
    }

    public void push(int x) {
        lock.lock();
        try {
            int max = (maxStack.isEmpty()) ? x : Math.max(x, maxStack.peek());
            stack.push(x);
            maxStack.push(max);
        } finally {
            lock.unlock();
        }
    }

    public int pop() {
        lock.unlock();
        try {
            maxStack.pop();
            return stack.pop();
        } finally {
            lock.unlock();
        }
    }

    public int top() {
        lock.lock();
        try {
            return stack.peek();
        } finally {
            lock.unlock();
        }
    }

    public int peekMax() {
        lock.lock();
        try {
            return maxStack.peek();
        } finally {
            lock.unlock();
        }
    }

    public int popMax() {
        // here we will consider a new buffer till the max element;
        lock.lock();
        try {
            Stack<Integer> buffer = new Stack<>();
            int max = peekMax();
            while (top() != max) {
                buffer.push(pop());
            }
            pop();
            while(!buffer.isEmpty()) {
                push(buffer.pop());
            }
            return max;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MaxStack maxStackObj = new MaxStack();

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
