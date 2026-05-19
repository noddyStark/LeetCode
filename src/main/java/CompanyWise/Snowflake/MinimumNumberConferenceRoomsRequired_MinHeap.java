package CompanyWise.Snowflake;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumNumberConferenceRoomsRequired_MinHeap {

    static void main() {

        int[][] intervals = {
                {0, 30},
                {5, 10},
                {15, 20}
        };

        /*
         * Approach:
         * First, sort all meetings by their start time.
         *
         * We use a min heap to keep track of meeting end times.
         * The smallest end time will always be at the top of the heap.
         *
         * For each meeting:
         * - If the current meeting starts after or exactly when the earliest meeting ends,
         *   then that room can be reused, so we remove the earliest end time from the heap.
         * - Then we add the current meeting's end time to the heap.
         *
         * The heap size represents the number of rooms currently being used.
         * The maximum heap size during the process gives the minimum number of rooms required.
         */

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // The heap always keeps the earliest ending meeting at the top.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] meeting : intervals) {
            int start = meeting[0];
            int end = meeting[1];

            if (!minHeap.isEmpty() && start >= minHeap.peek()) {
                minHeap.poll();
            }

            minHeap.offer(end);
        }

        System.out.println("Meeting Rooms Required = " + minHeap.size());

    }
}
