package org.leetcode;

import java.util.*;


/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 *
 */
public class ThreeSum_BruteForce {

    public static void main(String[] args) {
        int[] numbers = {-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4};
        System.out.println("All the triplets with nums[i] + nums[j] + nums[k] == 0 are " + threeSum(numbers));
    }

    private static List<List<Integer>> threeSum(int[] numbers) {

        List<List<Integer>> threeSum = new ArrayList<>();
        HashSet<List<Integer>> uniqueTriplets = new HashSet<>();

        for (int i = 0; i < numbers.length-2; i++) {
            for (int j = i+1; j < numbers.length-1; j++) {
                for (int k = j+1; k < numbers.length; k++) {
                    if (i != j && i != k && j != k) {
                        if (numbers[i] + numbers[j] + numbers[k] == 0) {
                            List<Integer> triplet = new ArrayList<>(List.of(numbers[i], numbers[j], numbers[k]));
                            Collections.sort(triplet);
                            if (!uniqueTriplets.contains(triplet)) {
                                uniqueTriplets.add(triplet);
                                threeSum.add(triplet);
                            }
                        }
                    }
                }
            }
        }
        return threeSum;
    }
}
