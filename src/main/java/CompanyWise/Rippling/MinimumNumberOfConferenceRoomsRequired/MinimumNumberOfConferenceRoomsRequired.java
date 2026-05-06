package CompanyWise.Rippling.MinimumNumberOfConferenceRoomsRequired;

import java.util.*;

/*
 * Given an array of meeting time intervals, where each interval is represented as a pair [start, end] (with start < end),
 * your task is to determine the minimum number of conference rooms required to hold all the meetings. Each meeting needs to
 *  be held in a separate room if there is an overlap in time with another meeting.
 * */
public class MinimumNumberOfConferenceRoomsRequired {

    static void main() {

        int[][] array = {
                {7, 10},
                {2, 4}
        };

            int result = addMeeting(array);

        System.out.println("Result = " + result);
    }


    private static int addMeeting(int[][] meetings) {


        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        //         Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

        System.out.println(Arrays.deepToString(meetings));

        int maxRooms = 0;

        for (int[] currentMeeting : meetings) {

            int start = currentMeeting[0];
            int end = currentMeeting[1];

            while(!minHeap.isEmpty() && start >= minHeap.peek()) {
                minHeap.poll();
            }

            minHeap.offer(end);

            maxRooms = Math.max(maxRooms, minHeap.size());
        }

        return maxRooms;
    }
}
