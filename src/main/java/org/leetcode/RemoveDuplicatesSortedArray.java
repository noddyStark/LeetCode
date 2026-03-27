package org.leetcode;

/**
 * 26. Remove Duplicates from Sorted Array
 * <p>
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such
 * that each unique element appears only once. The relative order of the elements should be kept the same.
 * <p>
 * Consider the number of unique elements in nums to be. After removing duplicates,
 * return the number of unique elements k.
 * <p>
 * The first k elements of nums should contain the unique numbers in sorted order.
 * The remaining elements beyond index k - 1 can be ignored.
 *
 */
public class RemoveDuplicatesSortedArray {

    public static void main(String[] args) {
        int[] numbers = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println("Number so unique elements are  = " + removeDuplicates(numbers));
    }

    private static int removeDuplicates(int[] numbers) {
        if (numbers.length == 0) return 0;

        int uniqueIndex = 0;

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] != numbers[uniqueIndex]) {
                uniqueIndex++;
                numbers[uniqueIndex] = numbers[i];
            }
        }

        return uniqueIndex + 1;
    }
}
