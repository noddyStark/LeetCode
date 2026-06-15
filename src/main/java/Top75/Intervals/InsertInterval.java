package Top75.Intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InsertInterval {

    static void main() {

        int[][] intervals = {
                {1, 2},
                {3, 5},
                {6, 7},
                {8, 10},
                {12, 16}
        };

        int[] newInterval = {4, 8};

        int[][] result = insertInterval(intervals, newInterval);

        for (int[] interval : result) {
            System.out.println(interval[0] + ", " + interval[1]);
        }

        // Expected = [[1,2],[3,10],[12,16]]
        // Output = [[1,2],[3,8],[6,7],[8,10],[12,16]]
    }

    // Brute Force
    private static int[][] insertIntervalBruteForce(int[][] intervals, int[] newInterval) {

        List<int[]> mergedInterval = new ArrayList<>();

        for (int[] interval : intervals) {
            mergedInterval.add(interval);
        }

        mergedInterval.add(newInterval);

        Collections.sort(mergedInterval, (a, b) -> (a[0] - b[0]));

        int i = 0;

        while (i < mergedInterval.size() - 1){

            int currentStartTime = mergedInterval.get(i)[0];
            int currentEndTime = mergedInterval.get(i)[1];

            int nextStartTime = mergedInterval.get(i + 1)[0];
            int nextEndTime = mergedInterval.get(i + 1)[1];

            // Case1: No OverLapping
            if (currentEndTime < nextStartTime) {
                i++;
            }  // Case2 : Overlapping
            else {
                // merge into 1
                mergedInterval.get(i)[0] = currentStartTime;
                mergedInterval.get(i)[1] = Math.max(currentEndTime, nextEndTime);
                mergedInterval.remove(i + 1);
            }
        }

        int[][] result = new int[mergedInterval.size()][2];

        for (int j = 0; j < mergedInterval.size(); j++) {
            result[j] = mergedInterval.get(j);
        }

        return result;
    }

    private static int[][] insertInterval(int[][] intervals, int[] newInterval) {

        List<int[]> result = new ArrayList<>();
        int n = intervals.length;

        int i = 0;

        // Add all the intervals that ends before the newInterval starts, because they do not overlap
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        /**
         * We are left with overlapping intervals now, so we need to perform the merge, there could be also a possibility that intervals do not overlap at all
         * for example if we had intervals like [1,2], [10, 12] and newInterval was [4,8], by now we would have added [1,2] in the result but the [10,12] does not
         * even overlap with the newInterval we have
         *
         * -----1----2-------3----------5---------------------
         * -----------------------4----------8----------------
         * ----------------------------------------10-----12--
         *
         * */
        // interval = [3,5] and newInterval = [4,8]
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }

        result.add(newInterval);

        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }
}
