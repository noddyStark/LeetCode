package org.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 560 : Subarray Sum Equals K
 */
public class SubArraySumEqualsK {
    static void main(String[] args) {

        int[] numbers = {1, 0, 1, 2, 1, 0, 4, 1, 3};
        int k = 4;

        System.out.println("Total Number of SubArrays : " + totalNumberOfSubArrays(numbers, k));
    }

    /**
     * The key insight is that if prefixSum[j] - prefixSum[i] = k, then the subarray from index i+1 to j has sum k.
     * This transforms the problem: for each position, we want to count how many earlier positions have a prefix sum equal to currentPrefixSum - k.
     * A hash map lets us track prefix sum frequencies as we iterate.
     * */

    private static int totalNumberOfSubArrays(int[] numbers, int k) {
        int totalNumberOfSubArrays = 0;
        int currentPrefixSum = 0;

        Map<Integer, Integer> prefixSumCount = new HashMap<>();

        // handles subarrays starting at index 0
        // we have found one subarray of 0 sum
        prefixSumCount.put(0, 1);

        for (int number : numbers) {
            // Step 1: update running sum
            currentPrefixSum = currentPrefixSum + number;
            System.out.println("currentPrefixSum = " + currentPrefixSum);

            // Step 2: check if (currentSum - k) exists
            if(prefixSumCount.containsKey(currentPrefixSum - k)) {
                totalNumberOfSubArrays += prefixSumCount.get(currentPrefixSum - k);
            }

            // Step 3: store current prefix sum
            prefixSumCount.put(currentPrefixSum, prefixSumCount.getOrDefault(currentPrefixSum, 0) + 1);

            System.out.println("PrefixSumCount Map = " + prefixSumCount.toString());
        }
        return totalNumberOfSubArrays;
    }
}
