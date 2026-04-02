package org.leetcode.Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 *
 * LeetCode 503. Next Greater Element II
 * <p>
 * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]),
 * return the next greater number for every element in nums.
 * <p>
 * The next greater number of a number x is the first greater number to its traversing-order next in the array,
 * which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
 */

public class NextGreaterElement_II {

    public static void main(String[] args) throws Exception {
        int[] numbers = {100,1,11,1,120,111,123,1,-1,-100};
        System.out.println("Next Greater Element Array = " + Arrays.toString(nextGreaterElement(numbers)));
    }

    private static int[] nextGreaterElement(int[] numbers) throws Exception {
        int[] result = new int[numbers.length];
        int size = numbers.length;
        Stack<Integer> stack = new Stack<>();
        Arrays.fill(result, -1);

        // 100, 1, 11, 1, 120, 111, 123, 1, -1, -100  100,  1,  11,  1, 120, 111, 123,  1, -1,  -100
        //  0   1  2   3   4    5    6   7   8    9   10   11   12  13   14   15   16  17   18   19
        //                                       ^^^
        //                              when my index reaches here
        //                              we need to start storing in the result
        //                              array, since for these numbers we want
        //                              the next greater element
        for (int i = 2 * size - 1; i >= 0; i--) {
            int current = numbers[i % size];

            while (!stack.isEmpty() && current >= stack.peek()) {
                stack.pop();
            }

            if (i < size) {
                if (!stack.isEmpty()) {
                    result[i] = stack.peek();
                }
            }

            stack.push(current);
        }

        return result;
    }
}
