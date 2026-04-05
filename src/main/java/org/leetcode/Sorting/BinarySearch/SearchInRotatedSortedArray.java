package org.leetcode.Sorting.BinarySearch;


/**
 * LeetCode 33
 * Search in Rotated Sorted Array
 * There is an integer array numbers sorted in ascending order (with distinct values).
 * <p>
 * Prior to being passed to your function, numbers is possibly left rotated at an unknown index k (1 <= k < numbers.length) such
 * that the resulting array is [numbers[k], numbers[k+1], ..., numbers[n-1], numbers[0], numbers[1], ..., numbers[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7]
 * might be left rotated by 3 indices and become [4,5,6,7,0,1,2].
 * <p>
 * Given the array numbers after the possible rotation and an integer target, return the index of target if it is in numbers, or -1 if it is not in numbers.
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 * <p>
 * -10^4 <= nums[i] <= 10^4
 */
public class SearchInRotatedSortedArray {

    static void main() {
        int[] numbers = {0, 2, 3, 4, -4, -3, -2};
        int target = -3;
        System.out.println("Number found in the rotated array = " + search(numbers, target));
    }

    private static int search(int[] numbers, int target) {

        int low = 0;
        int high = numbers.length - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (target == numbers[mid]) {
                return mid;
            }

            // If Left half is sorted
            if (numbers[mid] >= numbers[low]) {

                if (target >= numbers[low] && target < numbers[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                // If right half is sorted
                // Another example for right sorted array = > 2, 3, 4, -4, -3, -2, 0

                if (target > numbers[mid] && target <= numbers[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }

            }
        }

        return -1;
    }
}
