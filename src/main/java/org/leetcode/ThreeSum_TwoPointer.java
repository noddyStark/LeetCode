package org.leetcode;

import java.util.*;

public class ThreeSum_TwoPointer {

    public static void main(String[] args) {
        int[] numbers = {-1, 8, 1, 2, -1, -4, -2, -3, 3, 8, 4};
        System.out.println("All the triplets with nums[i] + nums[j] + nums[k] == 0 are " + threeSum(numbers));
    }

    /**
     * 3Sum using sorting + two pointers
     * <p>
     * Idea:
     * 1. Sort the array
     * 2. Fix one number as the first number
     * 3. Use two pointers to find the other two numbers
     * <p>
     * Why two pointers work:
     * - If sum is too small, move left forward to increase the sum
     * - If sum is too large, move right backward to decrease the sum
     * <p>
     * Time Complexity: O(n^2)
     * Space Complexity: O(1) extra space (ignoring output list)
     * <p>
     * -4, -3, -2, -1, -1,  1,  2,  3,  4,  8,  8
     * 0,  1,  2,  3,  4,  5,  6,  7,  8,  9,  10
     */
    private static List<List<Integer>> threeSum(int[] numbers) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(numbers);
        System.out.println("Sorted array : = " + Arrays.toString(numbers));

        for (int i = 0; i < numbers.length; i++) {
            if (i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = numbers.length - 1;

            while (left < right) {
                int sum = numbers[i] + numbers[left] + numbers[right];

                if (sum == 0) {
                    result.add(Arrays.asList(numbers[i], numbers[left], numbers[right]));

                    left++;
                    right--;

                    //  Since we have found the triplet and we have the while loop of left < right
                    // there could be a chance that we again find the duplicate numbers from either then
                    // In case we find that, we move the left & right pointers respectfully

                    while (left < right && numbers[left] == numbers[left - 1]) {
                        left++;
                    }

                    while (left < right && numbers[right] == numbers[right + 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return new ArrayList<>(result);
    }
}
