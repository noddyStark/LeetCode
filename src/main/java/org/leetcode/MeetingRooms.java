package org.leetcode;


import java.util.Arrays;
import java.util.Collections;

/*
* 252. Meeting Rooms
Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.



Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: true


Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti < endi <= 106
* */
public class MeetingRooms {

    static void main() {
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        int[][] intervals1 = {{7, 10}, {2, 4}};

        boolean result = canAttendAllMeetings(intervals1);

        System.out.println("canAttendAllMeetings = " + result);

    }

    private static boolean canAttendAllMeetings(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        for (int i = 1; i < intervals.length; i++) {

            int previousMeetingEndTime = intervals[i - 1][1];
            int currentMeetingStAartTime = intervals[i][0];

            if (currentMeetingStAartTime < previousMeetingEndTime) {
                return false;
            }

        }

        return true;
    }
}
