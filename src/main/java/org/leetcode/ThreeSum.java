package org.leetcode;

import java.util.*;


/**
 * LeetCode 15. 3Sum
 *
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 *      such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 * */
public class ThreeSum {

    public static void main(String[] args) {
        int[] numbers = {-1, 8, 1, 2, -1, -4, -2, -3, 3, 8, 4};
        System.out.println("All the triplets with nums[i] + nums[j] + nums[k] == 0 are " + threeSum(numbers));
    }

    private static List<List<Integer>> threeSum(int[] numbers) {

        HashSet<List<Integer>> uniqueTriplets = new HashSet<>();

        // We want to find 3 numbers such that:
        // first + second + third = 0

        // Step 1: Fix one number as the "first" number
        // Then the remaining problem becomes:

        // second + third = -first
        //
        // So now 3Sum becomes a 2Sum problem.

        // Step 2: For the current "first", our new target is:
        // pairTarget = -first
        //
        // Step 3: As we iterate through the remaining numbers,
        // we treat each number as "second"
        //
        // Then we ask:
        // "What value do I need as the third number so that
        // second + third = pairTarget ?"
        //
        // So:
        // third = pairTarget - second
        //
        // Step 4: Use a HashSet to remember numbers we have already seen
        // in this round for the current fixed "first".
        //
        // If "third" already exists in the set, then we found a valid triplet:
        //
        // first + second + third = 0
        //
        // We sort the triplet before storing it so that duplicates like
        // [-1, 0, 1] and [0, -1, 1] are treated as the same triplet.
        for (int i = 0; i < numbers.length - 2; i++) {
            int first = numbers[i];
            int pairTarget = -first;

            Set<Integer> seenInThisRound = new HashSet<>();

            for (int j = i + 1; j < numbers.length; j++) {
                int second = numbers[j];
                int third = pairTarget - second;   // second + third must equal pairTarget

                if (seenInThisRound.contains(third)) {
                    List<Integer> triplet = Arrays.asList(first, second, third);
                    Collections.sort(triplet);     // normalize order like [-1, 0, 1]
                    uniqueTriplets.add(triplet);
                }

                seenInThisRound.add(second);
            }
        }

        return new ArrayList<>(uniqueTriplets);
    }
}
