package Top75.Intervals;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Write a function to consolidate overlapping intervals within a given array intervals,
where each interval intervals[i] consists of a start time starti and an end time endi.

Two intervals are considered overlapping if they share any common time, including if one ends exactly when another
begins (e.g., [1,4] and [4,5] overlap and should be merged into [1,5]).

The function should return an array of the merged intervals so that no two intervals overlap and all the intervals
collectively cover all the time ranges in the original input.

Input:

intervals = [[3,5],[1,4],[7,9],[6,8]]
Output:

[[1,5],[6,9]]
Explanation: The intervals [3,5] and [1,4] overlap and are merged into [1,5]. Similarly, [7,9] and [6,8] overlap and are merged into [6,9].
*/
public class MergeIntervals {

    static void main() {

        int[][] intervals = {
                {3,5},
                {1,4},
                {7,9},
                {6,8}
        };


        int[][] intervals1 = {
                {1,3}, {2,6}, {8,10}, {15,18}
        };

        int[][] result = mergeIntervals(intervals1);

        for (int[] interval : result) {
            System.out.println(interval[0] + ", "  + interval[1]);
        }
    }

    private static int[][] mergeIntervals(int[][] intervals) {

        /*
        * -------3------------5--------------------------
        * ---1----------4--------------------------------
        * ----------------------------7-----------9------
        * ------------------------6----------8-----------
        *
        * [1, 5] [6,9]
        */

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        List<int[]> mergedIntervals = new ArrayList<>();

        int[] current = intervals[0];

        for (int i=1; i<intervals.length; i++) {
            int[] next = intervals[i];

            // Overlapping, currentEndTime > nextStartTime
            if (current[1] >= next[0]) {
                current[1] = Math.max(current[1], next[1]);
            }
            // Not overLapping
            else {
                mergedIntervals.add(current);
                current = next;
            }
        }

        mergedIntervals.add(current);

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}
