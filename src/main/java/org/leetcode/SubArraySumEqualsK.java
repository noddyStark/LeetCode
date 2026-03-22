package org.leetcode;

import java.util.Arrays;
import java.util.HashMap;

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
     *
     */

    private static int totalNumberOfSubArrays(int[] numbers, int k) {
        int totalNumberOfSubArrays = 0;

        int[] prefixSumArray = new int[numbers.length];

        prefixSumArray[0] = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            prefixSumArray[i] = prefixSumArray[i - 1] + numbers[i];
        }

        System.out.println("prefixSum = " + Arrays.toString(prefixSumArray));
        HashMap<Integer, Integer> prefixSumCountMap = new HashMap<>();

        for (int sum : prefixSumArray) {
            // Case when from index 0 till ith position, we found a subarray whose sum was K
            if (sum == k) {
                totalNumberOfSubArrays++;
            }

            if (prefixSumCountMap.containsKey(sum - k)) {
                totalNumberOfSubArrays = totalNumberOfSubArrays + prefixSumCountMap.get(sum - k);
            }

            prefixSumCountMap.put(sum, prefixSumCountMap.getOrDefault(sum, 0) + 1);
        }

        return totalNumberOfSubArrays;
    }
}
