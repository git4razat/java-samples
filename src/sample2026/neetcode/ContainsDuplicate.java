package sample2026.neetcode;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    public static void main(String[] args) {
        ContainsDuplicate obj = new ContainsDuplicate();
        int[] nums = new int[] {1,2,3,1};
        System.out.println(obj.containsDuplicate(nums));

    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            if (set.contains(num)) return true;
            else set.add(num);
        }
        return false;
    }
}
