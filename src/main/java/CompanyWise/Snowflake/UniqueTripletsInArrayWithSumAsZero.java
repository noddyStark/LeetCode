package CompanyWise.Snowflake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an array of integers nums, your task is to find all unique triplets in the array which give the sum of zero.
Specifically, you need to find all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, j != k, and
the sum of nums[i] + nums[j] + nums[k] == 0.
Notice that the solution set must not contain duplicate triplets.

Example 1:
        Input: nums = [-1, 0, 1, 2, -1, -4]
        Output: [[-1, -1, 2], [-1, 0, 1]]

Explanation:
There are two triplets that sum up to 0 and are unique:
1. The triplet [-1, -1, 2] uses the numbers at index 0, 1, and 3 respectively.
2. The triplet [-1, 0, 1] uses the numbers at index 0, 2, and 5 respectively.


Example 2:
        Input: nums = [0,1,1]
        Output: []

Explanation:
There are no valid triplets that sum up to zero.

Example 3:
        Input: nums = [0,0,0,0]
        Output: [[0,0,0]]

Explanation:
Only one triplet can be formed that sums up to zero, using the numbers at index 0, 1, and 2.
Constraints:

0 <= nums.length <= 3000
-10^5 <= nums[i] <= 10^5
*/
public class UniqueTripletsInArrayWithSumAsZero {

    static void main() {
        int[] numbers = {-1, 0, 1, 2, -1, -4};
//        int[] numbers = {-2, 0, 0, 2, 2};
        // Output: [[-1, -1, 2], [-1, 0, 1]]

        List<List<Integer>> result = uniqueTriplets(numbers);
        System.out.println(result);
    }

    private static List<List<Integer>> uniqueTriplets(int[] numbers) {

        Arrays.sort(numbers);
        System.out.println("Sorted : " + Arrays.toString(numbers));
        // -2, 0, 0, 2, 2

        List<List<Integer>> result = new ArrayList<>();


        for (int i = 0; i < numbers.length; i++) {

            if (i > 0 && numbers[i] == numbers[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = numbers.length - 1;

            while (left < right) {

                int sum = numbers[i] + numbers[left] + numbers[right];

                if (sum == 0) {
                    left++;
                    right--;
                    result.add(Arrays.asList(numbers[i], numbers[left], numbers[right]));

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

        return result;
    }
}
