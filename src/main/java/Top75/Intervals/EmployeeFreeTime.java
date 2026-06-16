package Top75.Intervals;

import java.util.ArrayList;
import java.util.List;

/*
Write a function to find the common free time for all employees from a list called schedule.
Each employee's schedule is represented by a list of non-overlapping intervals sorted by start times.
The function should return a list of finite, non-zero length intervals where all employees are free, also sorted in order.

Input:
schedule = [[[2,4],[7,10]],[[1,5]],[[6,9]]]
Output:
[(5,6)]
Explanation: The three employees collectively have only one common free time interval, which is from 5 to 6.
* */
public class EmployeeFreeTime {

    static void main() {
        int[][][] schedules = {
                {{1, 2}, {5, 6}}, // E1
                {{1, 3}},
                {{4, 10}} // E2
        };

        int[][] result = employeeFreeTime(schedules);

        for (int[] interval :result) {
            System.out.println(interval[0] + ", " + interval[1]);
        }
    }

    private static int[][] employeeFreeTime(int[][][] schedules) {

        /*
        [
            [1,2],[5,6] // E1
        ],
        [
            [1,3] // E2
        ],
        [
            [4,10] // E3
        ]
        */

        /*
        1. We can create a list of intervals, and all the intervals in this new list.
        2. Sort this list as per the start time in increasing order.
        3. Then we do the merge of all overlapping interval and the ones that don't overlap we add them as it is in a newly created merge list.
        4. While iterating through this new merge list, when we encounter a time gap, that is the time all employees are free.

        ------------------------------------------Time Line------------------------------------------------------------------
        ------1<------->2----------------------------------------------------------------------------------------------------
        ------1<------------>3-----------------------------------------------------------------------------------------------
        -----------------------------4<----------------------------------------------------------------------->10------------
        ----------------------------------------5<------------------>6-------------------------------------------------------
                             ^^^^^^^^
                             FreeTime
        */

        List<int[]> intervals = new ArrayList<>();

        // Step1 : Adding all the intervals in this list
        for (int[][] schedule : schedules) {
            for (int[] interval : schedule) {
                intervals.add(interval);
            }
        }

        // Step2 : [1, 2] [1, 3] [4, 10] [5, 10]
        intervals.sort((a, b) -> (a[0] - b[0]));

        // Step3 : New Merged List in which we will merge the overlapping intervals while going thru the intervals list that we created above.
        int[] prev = intervals.get(0);
        List<int[]> merged = new ArrayList<>();

        for (int i=1; i<intervals.size(); i++) {
            int[] current = intervals.get(i);

            // Over lapping intervals
            if (prev[1] >= current[0]) {
                prev[1] = Math.max(prev[1], current[1]);
            } else {
                // If no overlap
                merged.add(prev);
                prev = current;
            }
        }

        // Don't forget to add this, because when our loop ends, the prev would have not been processed
        merged.add(prev);

        List<int[]> freeTime = new ArrayList<>();

        for (int i=0; i<merged.size()-1; i++) {
            int previousEndTime = merged.get(i)[1];
            int currentStartTime = merged.get(i+1)[0];

            if (previousEndTime < currentStartTime) {
                freeTime.add(new int[]{previousEndTime, currentStartTime});
            }
        }

        return freeTime.toArray(new int[freeTime.size()][]);
    }
}
