package org.leetcode.Sorting.BinarySearch;

/**
 * 162. Find Peak Element
 * <p>
 * A peak element is an element that is strictly greater than its neighbors.
 * <p>
 * Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
 * <p>
 * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
 * <p>
 * You must write an algorithm that runs in O(log n) time.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 * <p>
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * nums[i] != nums[i + 1] for all valid i.
 *
 */
public class FindPeakElement {

    static void main() {
        int[] numbers = {1, 2, 3, 4, 5};
        System.out.println("Peak Element Index is = " + findPeakElement(numbers));
    }

    // numbers[i] != numbers[i + 1]
    private static int findPeakElement(int[] numbers) {
        return binarySearch(numbers, 0, numbers.length - 1);
    }

    private static int binarySearch(int[] numbers, int low, int high) {
        if (low == high) {
            return low;
        }

        int mid = low + (high - low) / 2;

        if (numbers[mid] > numbers[mid + 1]) {
            return binarySearch(numbers, low, mid);
        } else {
            return binarySearch(numbers, mid + 1, high);
        }
    }
}

/*
* Rule of thumb
    => Use low <= high, When:
       - you are searching for a target
       - and loop should continue until range becomes invalid

    => Use low < high, When:
        - you are shrinking to one final answer
        - and want loop to stop when only one candidate remains
This peak problem belongs to the second type.
* */
