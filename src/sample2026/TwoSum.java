package sample2026;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    private Map<Integer, Integer> nums = new HashMap<>();

    public void add(int num) {
        nums.put(num, nums.getOrDefault(num, 0) + 1);
    }

    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry: nums.entrySet()) {
            int key = entry.getKey();
            int remain = value - key;
            if (key == remain && entry.getValue() > 1)
                return true;
            if (key != remain && nums.containsKey(remain))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();

        // Adding elements to the data structure
        System.out.println("Adding numbers: 1, 3, 5");
        twoSum.add(1);
        twoSum.add(3);
        twoSum.add(5);

        // Test Cases
        System.out.println("Find 4 (1 + 3): " + twoSum.find(4)); // Expects true
        System.out.println("Find 7 (No pair): " + twoSum.find(7)); // Expects false

        // Testing duplicates
        System.out.println("\nAdding number: 3 (Duplicate)");
        twoSum.add(3);
        System.out.println("Find 6 (3 + 3): " + twoSum.find(6)); // Expects true
    }
}
