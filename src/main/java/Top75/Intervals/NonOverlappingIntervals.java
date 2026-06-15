package Top75.Intervals;

import java.util.Arrays;

/*
Write a function to return the minimum number of intervals that must be removed from a given array intervals,
where intervals[i] consists of a starting point starti and an ending point endi, to ensure that the remaining intervals do not overlap.

Input:

intervals = [[1,3],[5,8],[4,10],[11,13]]
Output:

1
Explanation: Removing the interval [4,10] leaves all other intervals non-overlapping.
*/
public class NonOverlappingIntervals {

    static void main() {

        int[][] intervals = {
                {1, 3},
                {5, 8},
                {4, 10},
                {11, 13}
        };

        System.out.println("Number of Intervals to be removed = " + nonOverlappingIntervals(intervals));
    }


    private static Integer nonOverlappingIntervals(int[][] intervals) {

        // Step 1 : Sort in the ascending order of starting Time
        Arrays.sort(intervals, (a ,b) -> (a[0] - b[0]));

        int n = intervals.length;
        int current = 1;
        int prev = 0;

        int numberOfIntervalsToBeRemoved = 0;

        while (current < n) {

            int prevEnd = intervals[prev][1];

            int currentStart = intervals[current][0];
            int currentEnd = intervals[current][1];

            // Case 1: Non-Overlapping
            if (prevEnd <= currentStart) {
                prev = current;
            }

            // Case 2: OverLapping
            else if (prevEnd <= currentEnd) {
                numberOfIntervalsToBeRemoved++;
            }

            // Case3: OverLapping
            else {
                numberOfIntervalsToBeRemoved++;
                prev = current;
            }

            current++;
        }

        return numberOfIntervalsToBeRemoved;

    }
}
