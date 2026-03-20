package org.leetcode.SlidingWindow;

import java.util.Arrays;

public class FindMaxAverage_BruteForce {

    public static void main(String[] args) {

        int[] numbers = {1, 12, -5, -6, 50, 3};
        int k = 4;

        System.out.println("Max Average of contiguous subarray with " + k +
                " elements is : " + findMaxAverage(numbers, k) );
    }

    /**
     * Constraints:
     *      n == numbers.length
     *      1 <= k <= n <= 105
     *      -104 <= numbers[i] <= 104
     * */
    private static double findMaxAverage(int[] numbers, int k) {
        double maxAverage = 0;
        double sumOfNumbersInWindow = 0;

        if(numbers.length == 1) {
            return numbers[0];
        }

        if (k == 1) {
            Arrays.sort(numbers);
            return numbers[numbers.length - 1];
        }

        for (int i = 0; i < numbers.length - k + 1; i++) {
            sumOfNumbersInWindow = numbers[i];
            for (int j = 1; j < k; j++) {
                sumOfNumbersInWindow = sumOfNumbersInWindow + numbers[i + j];
            }
            maxAverage = Math.max(maxAverage, sumOfNumbersInWindow/4);
        }

        return maxAverage;
    }
}
