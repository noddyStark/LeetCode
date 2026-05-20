package CompanyWise.Snowflake;

import java.util.Arrays;

public class HorizontalPodAutoScaler {

    static void main() {

        // pods[i] -> num of pods for ith MS
        int[] pods = {2, 4, 1, 4};
        int n = 4;

        int[][] logs = {
                {1, 2, 30},
                {1, 3, 4},
                {2, -1, 10}
        };

        findPodCount(pods, logs);
    }

    private static int[] findPodCount(int[] pods, int[][] logs) {

        int n = pods.length;
        int[] result = Arrays.copyOf(pods, n);

        // updated[i] tells us whether the final direct update
        // for pod i has already been handled.
        boolean[] updated = new boolean[n];

        // Type 1 log: [1, p, x]
        // Type 2 log: [2, -1, x]
        // maxThreshold stores the biggest type 2 value seen so far
        // while going from right to left.
        // It means: any pod value before this point must be at least this value.
        int maxThreshold = 0;

        for (int i = logs.length - 1; i >= 0; i--) {

            int type = logs[i][0];
            int p = logs[i][1];
            int x = logs[i][2];

            System.out.println("i = " + i + " type = " + type + " p = " + p + " x = " + x);

            if (type == 2) {
                /*
                 * Type 2 log: [2, -1, x]
                 *
                 * Meaning:
                 * All pods with value less than x should become x.
                 *
                 * Instead of updating every pod immediately,
                 * we only remember the largest x value.
                 *
                 * Example:
                 * [2, -1, 10] means every earlier pod value
                 * must be at least 10.
                 */
                maxThreshold = Math.max(maxThreshold, x);
            } else {
                int index = p - 1;

                if (!updated[index]) {
                    result[index] = Math.max(maxThreshold, x);
                    System.out.println("result after update = " + Arrays.toString(result));
                    updated[index] = true;
                }

            }
        }

        System.out.println("Updated = " + Arrays.toString(updated));
        System.out.println("result before 2nd update = " + Arrays.toString(result) + " and maxThreshold = " + maxThreshold);

        for (int i = 0; i < n; i++) {
            if (!updated[i]) {
                result[i] = Math.max(result[i], maxThreshold);
            }
        }

        System.out.println("Updated result = " + Arrays.toString(result));

        return result;
    }
}
