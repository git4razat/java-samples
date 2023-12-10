package sample;

import java.util.Deque;

public class NStableLengthArray {
	
	// 10 1 2 4 6
	
	public int longestSubarray(int[] nums, int limit) {
        if (nums == null || nums.length == 0) return 0;
        Deque<Integer> maxDq = new java.util.LinkedList();  
        Deque<Integer> minDq = new java.util.LinkedList();  
        int i = 0;
        int res = 0;
        for (int j = 0; j < nums.length; ++j) {
            while (!maxDq.isEmpty() && nums[j] > maxDq.peekLast()) { maxDq.pollLast(); }
            while (!minDq.isEmpty() && nums[j] < minDq.peekLast()) { minDq.pollLast(); }
            maxDq.add(nums[j]);
            minDq.add(nums[j]);
            if (maxDq.peekFirst() - minDq.peekFirst() > limit) {
                if (maxDq.peekFirst() == nums[i]) { maxDq.pollFirst(); }
                if (minDq.peekFirst() == nums[i]) { minDq.pollFirst(); }
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }

}
