package Top75.Intervals;

import java.util.Arrays;
import java.util.Comparator;

/*
Write a function to check if a person can attend all the meetings scheduled without any time conflicts. Given an array intervals,
where each element [s1, e1] represents a meeting starting at time s1 and ending at time e1, determine if there are any overlapping meetings.
If there is no overlap between any meetings, return true; otherwise, return false.

Note that meetings ending and starting at the same time, such as (0,5) and (5,10), do not conflict.

Input:
intervals = [(1,5),(3,9),(6,8)]
Output:
false
Explanation: The meetings (1,5) and (3,9) overlap.

Input:
intervals = [(10,12),(6,9),(13,15)]
Output:
true
Explanation: There are no overlapping meetings, so the person can attend all.
*/
public class CanAttendMeetings {

    static void main() {
        int[][] intervals = {
                {10, 12},
                {6, 9},
                {13, 15}
        };

        System.out.println(canAttendAllMeetings(intervals));
    }


    private static boolean canAttendAllMeetings(int[][] intervals) {

        if (intervals.length == 0) {
            return true;
        }

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        int prevMeetingEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            int startTime = intervals[i][0];
            int endTime = intervals[i][1];

            if (startTime < prevMeetingEnd) {
                return false;
            } else {
                prevMeetingEnd = endTime;
            }
        }

        return true;
    }
}
