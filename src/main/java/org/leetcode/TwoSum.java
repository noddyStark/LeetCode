package org.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/*
* 1. Two Sum
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Example 2:

Input: nums = [3,2,4], target = 6
Output: [1,2]
Example 3:

Input: nums = [3,3], target = 6
Output: [0,1]

Constraints:
2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.

Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
* */
public class TwoSum {
    static void main() {

        int[] numbers = {3,2,4};
        int target = 6;
        int[] result = new int[2];

        long startingTime =  System.currentTimeMillis();
        System.out.println("Indices whose sums adds up to the target " + Arrays.toString(returnIndices(numbers, target, result)));
        System.out.println("Total Execution Time : " + (System.currentTimeMillis() - startingTime) + " ms");
    }

    private static int[] returnIndices(int[] numbers, int target, int[] result) {

        /*
         * Brute Force Solution
         *
         * Time Complexity : O(n^2)
         * Space Complexity : O(n)
         * */
        int i,j;
        for (i = 0; i < numbers.length - 1; i++) {
            for (j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }

        /*
         * Using HashMap Instead of checking every pair, we store numbers we have already seen in a HashMap.
         * Idea: [target - current_number = complement]
         *
         * If complement already exists in map → we found the pair.
         * Time Complexity : O(n)
         * Space Complexity : O(n)
         * */

        HashMap<Integer, Integer> map = new HashMap<>();

        int k;
        // int[] numbers = {2,  7,  11, 15};
        //        index->  {0,  1,  2,  3}
        // target = 9

        for (k = 0; k <= numbers.length - 1; k++) {
            int complement = target - numbers[k];

            if (map.containsKey(complement)) {
                return new int[]{k, map.get(complement)};
            }

            map.put(numbers[k], k);
        }

        return result;
    }
}
