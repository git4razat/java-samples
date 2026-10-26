package sample2026.neetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        TwoSum object = new TwoSum();
        int[] result = object.twoSum(new int[] {2,7,11,15}, 9);
        System.out.println(Arrays.toString(result));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int remaining = target - nums[i];
            if (map.containsKey(remaining)) {
                return new int[] {i, map.get(remaining)};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }
}
