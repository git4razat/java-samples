package sample2026.neetcode;

public class ContainerWithMaxArea {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while(left < right) {
            int length = Math.min(height[left], height[right]);
            int width = right - left;
            int area = length * width;
            maxArea = Math.max(area, maxArea);
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return maxArea;
    }
}
