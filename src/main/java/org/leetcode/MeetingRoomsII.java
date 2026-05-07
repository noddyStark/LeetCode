package org.leetcode;


import java.util.Arrays;
import java.util.PriorityQueue;

/*
* 253. Meeting Rooms II
Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
*
Example 1:
Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2

Example 2:
Input: intervals = [[7,10],[2,4]]
Output: 1


Constraints:
1 <= intervals.length <= 104
0 <= starti < endi <= 106
* */
public class MeetingRoomsII {

    static void main() {

        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        int result = minMeetingRooms(intervals);

        System.out.println("Result = " + result);
    }

    private static int minMeetingRooms(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        PriorityQueue<Integer> minheap = new PriorityQueue<>();

        int maxRooms = 0;

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            while (!minheap.isEmpty() && start >= minheap.peek()) {
                minheap.poll();
            }

            // occupy room
            minheap.offer(end);

            // track peak rooms used
            maxRooms = Math.max(maxRooms, minheap.size());
        }

        return maxRooms;
    }
}
