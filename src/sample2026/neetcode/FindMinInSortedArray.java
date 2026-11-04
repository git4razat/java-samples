package sample2026.neetcode;

public class FindMinInSortedArray {
    public int findMin(int[] nums) {

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[right] < nums[mid]) { // right part is unsorted
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
