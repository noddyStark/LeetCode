package org.leetcode;

/**
 * LeetCode 560 : Subarray Sum Equals K
 */
public class SubArraySumEqualsK_BruteForce {

    static void main(String[] args) {

        int[] numbers = {-1,-1,1};
        int k = 0;

        System.out.println("Total Number of SubArrays : " + totalNumberOfSubArrays(numbers, k));
    }

    private static int totalNumberOfSubArrays(int[] numbers, int k) {
        int totalNumberOfSubArrays = 0;

        for (int i=0; i < numbers.length; i++) {
            int currentSum = 0;  // reset for each new starting point

            for (int j=i; j < numbers.length; j++) {
                currentSum = currentSum + numbers[j];

                if(currentSum == k) {
                    totalNumberOfSubArrays++;
                }
            }
        }
        return totalNumberOfSubArrays;
    }
}
