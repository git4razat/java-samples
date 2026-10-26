package sample2026.neetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum obj = new ThreeSum();
        System.out.println(obj.threeSum(new int[] {-1,0,1,2,-1,-4}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for(int i = 0; i < nums.length ; i++) {
           if (nums[i] > 0) break;
           if (i > 0 && nums[i] == nums[i-1]) continue;

           int l = i+1; int r = nums.length - 1;
           while (l < r) {
               int sum = nums[i] + nums[l] + nums[r];
               if (sum > 0) {
                   r--;
               } else if (sum < 0) {
                   l++;
               } else {
                   result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                   l++;
                   r--;
                   while(l < r && nums[l] == nums[l -1]) {
                       l++;
                   }
               }
           }
        }
        return result;
    }
}
