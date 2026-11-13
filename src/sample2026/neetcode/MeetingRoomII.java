package sample2026.neetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomII {

    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        // sort intervals based on start time of each meeting.
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));

        // minheap to store endtime of all active meetings.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < intervals.length; i++) {
            if (!minHeap.isEmpty() && intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.add(intervals[i][1]);
        }
        return minHeap.size();
    }
}
