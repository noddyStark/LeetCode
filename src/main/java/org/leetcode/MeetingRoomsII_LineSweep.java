package org.leetcode;

import java.util.Arrays;

public class MeetingRoomsII_LineSweep {

    static void main() {

        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        int result = minMeetingRooms(intervals);

        System.out.println("Result = " + result);
    }

    private static int minMeetingRooms(int[][] intervals) {

        int n = intervals.length;

        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start); // 0 5 15
        Arrays.sort(end); // 10 20 30

        int startPointer = 0;
        int endPointer = 0;

        int rooms = 0;
        int maxRooms = 0;

        while (startPointer < n) {

            if (start[startPointer] < end[endPointer]) {
                rooms++;
                maxRooms = Math.max(maxRooms, rooms);
                startPointer++;
            } else {
                maxRooms--;
                endPointer++;
            }
        }

        return maxRooms;
    }
}

/*
Instead of thinking in terms of “rooms”, think in terms of:

Every meeting has:
a start → +1 room needed
an end → -1 room freed


Walkthrough (mentally visualize timeline)

Think of it like events happening in order:
*   | Time | Event | Rooms |
    | ---- | ----- | ----- |
    | 0    | start | 1     |
    | 5    | start | 2     |
    | 10   | end   | 1     |
    | 15   | start | 2     |
    | 20   | end   | 1     |
    | 30   | end   | 0     |

The peak = 2 rooms
* */
