package sample2026.neetcode;

import java.util.Arrays;

public class NonOverlappingIntervals {

    public int eraseNonOverlappingIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        Arrays.sort(intervals, (a,b) -> Integer.compare(a[1], b[1]));

        int removeCount = 0;
        int previousEnd = intervals[0][1];

        for (int i = 0 ; i < intervals.length; i++) {
            int currentStart = intervals[i][0];
            int currentEnd = intervals[i][1];

            if (previousEnd > currentStart) {
                removeCount++;
            } else {
                previousEnd = currentEnd;
            }
        }
        return removeCount;
    }
}
