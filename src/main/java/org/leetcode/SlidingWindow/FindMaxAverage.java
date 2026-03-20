package org.leetcode.SlidingWindow;

/**
 * 643. Maximum Average Subarray
 * You are given an integer array numbers consisting of n elements, and an integer k.
 * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
 *
 * <pre>
 * Example 1:
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 *
 * Example 2:
 * Input: nums = [5], k = 1
 * Output: 5.00000
 *
 * Constraints:
 * n == nums.length
 * 1 <= k <= n <= 105
 * -104 <= nums[i] <= 104
* */
public class FindMaxAverage {
    public static void main(String[] args) {

        int[] numbers = {3, 3, 4, 3, 0};
        int k = 3;

        System.out.println("Max Average of contiguous subarray with " + k +
                " elements is : " + findMaxAverage(numbers, k));
    }

    private static double findMaxAverage(int[] numbers, int k) {

        int windowSum = 0;

        for (int i = 0; i < k; i++) {
            windowSum = windowSum + numbers[i];
        }

        int maxSum = windowSum;

        // {3, 3, 4, 3, 0}
        for (int i=k; i < numbers.length; i++) {
            windowSum = windowSum + numbers[i] - numbers[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return (double) (maxSum) / k;
    }
}
