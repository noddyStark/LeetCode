package org.leetcode.SlidingWindow;

import java.util.Arrays;

/**
 * 3634. Minimum Removals to Balance Array
 * You are given an integer array nums and an integer k.
 * An array is considered balanced if the value of its maximum element is at most k times the minimum element.
 * You may remove any number of elements from nums without making it empty.
 * Return the minimum number of elements to remove so that the remaining array is balanced.
 *
 * Note: An array of size 1 is considered balanced as its maximum and minimum are equal, and the condition always holds true.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= k <= 105
 * */
public class MinimumRemovalToBalanceArray {

    public static void main(String[] args) {
        int[] numbers = {2,1,5};
        int k = 2;

        System.out.println("Minimun Removals Required : " + minimumRemovals(numbers, k));
    }

    // minimum removals = n - size of largest valid group
    private static int minimumRemovals(int[] numbers, int k) {
        Arrays.sort(numbers);

        int left = 0;
        int maxWindowSize = 0;

        for (int right = 0; right < numbers.length; right++) {
            while ((long) numbers[right] > (long) numbers[left] * k) {
                left++;
            }

            maxWindowSize = Math.max(maxWindowSize, right - left + 1);
        }

        return numbers.length - maxWindowSize;
    }
}
