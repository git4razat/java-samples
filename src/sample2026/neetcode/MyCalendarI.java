package sample2026.neetcode;

import java.util.TreeMap;

public class MyCalendarI {

    TreeMap<Integer, Integer> map;

    MyCalendarI() {
        map = new TreeMap<>();
    }

    // time complexity of each book call - o(log n) and for n book calls o(nlogn)
    public boolean book(int startTime, int endTime) {
        // Find the closest existing interval starting at or before startTime
        Integer prevStart = map.floorKey(startTime);
        // Find the closest existing interval starting at or after startTime
        Integer nextStart = map.ceilingKey(startTime);

        // Check for overlap with the previous interval
        if (prevStart != null && map.get(prevStart) > startTime) {
            return false;
        }

        // Check for overlap with the next interval
        if (nextStart != null && nextStart < endTime) {
            return false;
        }

        // No overlap found; insert the new booking
        map.put(startTime, endTime);
        return true;
    }

    public static void main(String[] args) {
        MyCalendarI myCalendar = new MyCalendarI();

        // Standard LeetCode Example 1 test cases
        System.out.println("book(10, 20): " + myCalendar.book(10, 20)); // Expected: true
        System.out.println("book(15, 25): " + myCalendar.book(15, 25)); // Expected: false (overlaps with [10, 20))
        System.out.println("book(20, 30): " + myCalendar.book(20, 30)); // Expected: true (adjacent, no overlap)

        // Additional edge cases
        System.out.println("book(5, 10):  " + myCalendar.book(5, 10));  // Expected: true (ends exactly at 10)
        System.out.println("book(5, 11):  " + myCalendar.book(5, 11));  // Expected: false (overlaps with [5, 10) and [10, 20))
    }
}
