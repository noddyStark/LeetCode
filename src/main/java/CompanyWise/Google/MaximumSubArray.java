package CompanyWise.Google;

import java.util.Arrays;

/*
You are given an integer array nums.

Find a non-empty contiguous subarray having the maximum possible sum.
If multiple subarrays have the same maximum sum:

- Choose the subarray with the shortest length.
- If multiple shortest subarrays remain, choose the one with the smallest starting index.

Return [startIndex, endIndex] of the selected subarray.
Example 1
Input: nums = [1, -1, 1, -1, 1]

Output: [0, 0]

1 <= nums.length <= 100,000
-10^9 <= nums[i] <= 10^9
*/
public class MaximumSubArray {

    static void main() {
        int[] nums = {1, -1, 1, -1, 1};

        int[] result = shortestLengthSubArrayIndex(nums);
        System.out.println(Arrays.toString(result));
    }

    public static int[] shortestLengthSubArrayIndex(int[] nums) {

        long currentSum = nums[0];
        long maxSum = nums[0];

        int currentStart = 0;
        int bestStart = 0;
        int bestEnd = 0;

        for (int currentEnd = 1; currentEnd < nums.length; currentEnd++) {

            if (nums[currentEnd] > currentSum + nums[currentEnd]) {
                currentSum = nums[currentEnd];
                currentStart = currentEnd;
            } else {
                currentSum += nums[currentEnd];
            }

            int currentLength = currentEnd - currentStart + 1;
            int bestLength = bestEnd - bestStart + 1;

            // 1. Prefer the larger sum (currentSum > maxSum)
            // 2. If sums are equal, prefer the shorter subarray (currentSum == maxSum && currentLength < bestLength)
            // 3. If sum and length are equal, prefer the earlier subarray ( currentSum == maxSum && currentLength == bestLength & currentStart < bestStart)
            if (currentSum > maxSum ||
                    (currentSum == maxSum && currentLength < bestLength) ||
                    (currentSum == maxSum && currentLength == bestLength && currentStart < bestStart)) {
                maxSum = currentSum;
                bestStart = currentStart;
                bestEnd = currentEnd;
            }
        }

        return new int[]{bestStart, bestEnd};
    }
}
