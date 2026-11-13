package sample2026.neetcode;

import java.util.Arrays;

public class MeetingRoomI {

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return true;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int previousEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];

            if (previousEnd > currentStart) {
                return false;
            } else {
                previousEnd = currentEnd;
            }
        }
        return true;
    }
}
