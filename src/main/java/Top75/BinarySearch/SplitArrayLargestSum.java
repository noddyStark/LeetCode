package Top75.BinarySearch;

import java.util.Arrays;

/*
You are given an array nums and an integer k. nums represents the weights of n consecutive tasks while k represents the number of workers.
Tasks are assigned to the workers as contiguous blocks.

Your goal is to distribute the work so that the heaviest workload (sum of task weights for any single worker) is as small as possible.

Return the minimum possible value of the maximum workload.

Example 1:
Input:
nums = [4, 8, 15, 7, 3], k = 3
Output: 15
Explanation: One optimal split is [4, 8] (sum=12), [15] (sum=15), [7, 3] (sum=10). The maximum workload among workers is 15.

Example 2:
Input:
nums = [6, 3, 9, 2, 1, 8], k = 2
Output: 18

Explanation:
Split into [6, 3, 9] (sum=18) and [2, 1, 8] (sum=11). The heaviest workload is 18. Any other split with 2 groups results in a higher maximum.
*/
public class SplitArrayLargestSum {

    static void main() {
        int[] numbers = {6, 3, 9, 2, 1, 8};
        int k = 2;

        System.out.println(splitArray(numbers, k));
    }

    private static int splitArray(int[] numbers, int k) {

        int left = 0;
        int right = 0;
        for (int num : numbers) {
            left = Math.max(left, num);
            right += num;
        }


        while (left < right) {
            System.out.println("Left = " + left + " Right = " + right);
            int mid = left + (right - left) / 2;
            System.out.println("Mid = " + mid);
            if (canSplit(numbers, k, mid)) {
                right = mid;
            } else {
                System.out.println("Can't Split");
                left = mid + 1;
            }
        }

        return left;

    }

    private static boolean canSplit(int[] nums, int k, int maxSum) {
        int subarrays = 1;
        int currentSum = 0;
        for (int num : nums) {
            // maxSum = 17
            if (currentSum + num > maxSum) {
                subarrays++;
                currentSum = num;
            } else {
                currentSum += num;
            }
        }
        return subarrays <= k;
    }

}