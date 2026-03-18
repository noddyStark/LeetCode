package org.leetcode.SlidingWindow;

import java.util.Arrays;


/**
You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the
 array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.


<pre>

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation:
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7

Example 2:

Input: nums = [1], k = 1
Output: [1]
* */
public class SlidingWindowMaximum_BruteForce {

    public static void main(String[] args) {

        int[] numbers = {1,3,-1,-3,5,3,6,7};
        int k = 3;

        int[] result = slidingWindowWithMaxNumber(numbers, k);

        System.out.println("Sliding Window Of Maximum Numbers : " + Arrays.toString(result));
    }

    private static int[] slidingWindowWithMaxNumber(int[] numbers, int k) {

        if(numbers.length == 1 || k == 1) return numbers;

        int[] maxNumbersArray = new int[numbers.length -  k + 1];

        int i = 0;
        int windowMax;
        while (i < numbers.length - k + 1) {
            windowMax = numbers[i];
            for (int j = 1; j < k; j++) {
                if(numbers[i + j] > windowMax) {
                    windowMax = numbers[i + j];
                }
            }
            maxNumbersArray[i] = windowMax;
            i++;
        }

        return maxNumbersArray;
    }
}
