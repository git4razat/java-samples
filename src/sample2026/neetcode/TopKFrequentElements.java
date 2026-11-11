package sample2026.neetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {

        // Create Frequency Map
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num: nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }


        // Create and Populate Buckets
        // The maximum frequency can be the length of the array, so we need n+1 buckets
        List<Integer> buckets[] = new ArrayList[nums.length + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int num : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(num);
            buckets[frequency].add(num);
        }

        // Retrieve Top K Elements
        List<Integer> result = new ArrayList<>();
        for(int i = (buckets.length -1) ; i >= 0; i--) {
            if (buckets[i].size() > 0) {
                for (int num: buckets[i]) {
                    result.add(num);
                    if (result.size() == k)
                        return result.stream().mapToInt(j -> j).toArray();
                }
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}
