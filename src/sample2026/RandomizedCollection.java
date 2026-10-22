package sample2026;

import java.util.*;

public class RandomizedCollection {

    private List<Integer> nums;
    private Map<Integer, Set<Integer>> locs;
    private Random random;

    private RandomizedCollection() {
        nums = new ArrayList<>();
        locs = new HashMap<>();
        random = new Random();
    }

    /** Inserts an item val into the multiset. Returns true if not present, false otherwise. */
    private boolean insert(int x) {
        boolean exists = locs.containsKey(x);
        if (!exists) locs.put(x, new HashSet<>());

        locs.get(x).add(nums.size());
        nums.add(x);
        return !exists;
    }

    /** Removes an item val from the multiset if present. Returns true if present, false otherwise. */
    private boolean remove(int x) {
        if (!locs.containsKey(x) || locs.get(x).isEmpty()) {
            return false;
        }

        // 1. Get an arbitrary index of 'x' from our map
        int removeIdx = locs.get(x).iterator().next();
        int lastVal = nums.get(nums.size() - 1);

        // remove element from map and swap with last element of list
        locs.get(x).remove(removeIdx);
        nums.set(removeIdx, lastVal);

        locs.get(lastVal).add(removeIdx);
        locs.get(lastVal).remove(nums.size() - 1);
        nums.remove(nums.size() - 1);
        return true;
    }

    private int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }

    public static void main(String[] args) {
        // Instantiate the LeetCode 381 collection
        RandomizedCollection collection = new RandomizedCollection();

        System.out.println("--- TESTING INSERTION ---");
        // Inserts 10. Returns true as it's the first time.
        System.out.println("Insert 10: " + collection.insert(10));

        // Inserts 10 again. Returns false as 10 is already present.
        System.out.println("Insert 10 (duplicate): " + collection.insert(10));

        // Inserts 20. Returns true.
        System.out.println("Insert 20: " + collection.insert(20));

        System.out.println("\n--- TESTING RANDOM SAMPLING ---");
        // Print 5 random selections to see the duplicate probability distribution
        System.out.print("5 Random selections: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(collection.getRandom() + " ");
        }
        System.out.println();

        System.out.println("\n--- TESTING DELETION ---");
        // Removes one instance of 10. Returns true.
        System.out.println("Remove 10: " + collection.remove(10));

        // Only one 10 and one 20 should be left now. Let's sample again.
        System.out.print("5 Random selections after removing one 10: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(collection.getRandom() + " ");
        }
        System.out.println();

        // Removes another 10. Returns true.
        System.out.println("Remove 10 again: " + collection.remove(10));

        // Tries to remove 10 a third time. Returns false (no 10s left).
        System.out.println("Remove 10 third time: " + collection.remove(10));
    }
}
