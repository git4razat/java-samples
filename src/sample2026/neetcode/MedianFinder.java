package sample2026.neetcode;

import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFinder {
    Queue<Integer> leftPartitionMaxHeap;
    Queue<Integer> rightPartitionMinHeap;
    public MedianFinder() {
        leftPartitionMaxHeap = new PriorityQueue<>((a,b) -> b-a);
        rightPartitionMinHeap = new PriorityQueue<>((a, b) -> a-b);
    }

    public void addNum(int num) {
        leftPartitionMaxHeap.add(num);
        rightPartitionMinHeap.add(leftPartitionMaxHeap.poll());

        if (leftPartitionMaxHeap.size() < rightPartitionMinHeap.size()) {
            leftPartitionMaxHeap.add(rightPartitionMinHeap.poll());
        }
    }

    public double findMedian() {
        // odd size
        if (leftPartitionMaxHeap.size() > rightPartitionMinHeap.size()) {
            return leftPartitionMaxHeap.peek();
        }
        return (leftPartitionMaxHeap.peek() + rightPartitionMinHeap.peek())/2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */