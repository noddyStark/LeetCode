package Top75.SlidingWindow;

/*
DESCRIPTION
Given an array of integers nums and an integer k, find the maximum sum of any contiguous subarray of size k.

Example 1: Input:
nums = [2, 1, 5, 1, 3, 2]
k = 3
Output:
9
Explanation: The subarray with the maximum sum is [5, 1, 3] with a sum of 9.*/
public class MaximumSumOfSubarraysOfSizeK {

    static void main() {
        int[] nums = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println(maxSubArrayOfSizeK(nums, k));
    }

    private static int maxSubArrayOfSizeK(int[] nums, int k) {

        int maxSum = Integer.MIN_VALUE;
        int windowSum = 0;

        for (int i=0; i<nums.length; i++) {
            windowSum = windowSum + nums[i];

            if (i >= k - 1) {
                maxSum = Math.max(windowSum, maxSum);
                windowSum = windowSum - nums[i - k + 1];
            }
        }

        return maxSum;
    }
}
