package CompanyWise.Snowflake;

import java.util.Arrays;

/*
* Given an array of meeting time intervals, where each interval is represented as a pair [start, end] (with start < end),
* your task is to determine the minimum number of conference rooms required to hold all the meetings. Each meeting needs to be held in a separate room if there is an overlap in time with another meeting.

The intervals are in the form:

[s1, e1], [s2, e2], ...
* */
public class MinimumNumberConferenceRoomsRequired {

    static void main() {

        //  [start, end] (with start < end),
        // [[0, 30], [5, 10], [15, 20]]
        // intervals = [[1, 4], [2, 5], [3, 6]]

        int[][] intervals = {
                {0, 30},
                {5, 10},
                {15, 20}
        };

        // start =  0   5   15
        // end =    10  20  30

        /*
         * Approach:
         * We separate all meeting start times and end times into two arrays and sort both arrays.
         *
         * Then we use two pointers:
         * - startPointer points to the next meeting that is about to start.
         * - endPointer points to the earliest meeting that is about to end.
         *
         * If start[startPointer] < end[endPointer], it means the next meeting starts
         * before the earliest current meeting ends. So there is an overlap, and we need
         * one more room.
         *
         * Otherwise, if start[startPointer] >= end[endPointer], it means one meeting
         * has already ended, or ends exactly when the next meeting starts. So that room
         * becomes available and can be reused.
         *
         * While doing this, we keep track of the maximum number of rooms used at the same time.
         */

        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];

        for (int i=0; i<intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int startPointer = 0;
        int endPointer = 0;
        int rooms = 0;
        int maxRooms = 0;

        while (startPointer < intervals.length) {
            if (start[startPointer] < end[endPointer]) {
                startPointer++;
                rooms++;
                maxRooms = Math.max(rooms,maxRooms);
            } else {
                rooms--;
                endPointer++;
            }
        }

        System.out.println("Max Rooms = " + maxRooms);

    }
}
