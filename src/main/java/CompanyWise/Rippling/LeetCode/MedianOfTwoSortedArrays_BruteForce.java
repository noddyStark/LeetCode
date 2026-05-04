package CompanyWise.Rippling.LeetCode;

import java.util.Arrays;

/*
4. Median of Two Sorted Arrays
Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.

Example 2:
Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
*/
public class MedianOfTwoSortedArrays_BruteForce {

    static void main() {

        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};

        double answer = medianOfArray(nums1, nums2);

        System.out.println("Median is = " + answer);

    }

    private static double medianOfArray(int[] nums1, int[] nums2) {

        /*
         *  arr = [1 2 3] => median is 2 => arr[n/2]
         *
         * arr = [1 2 3 4] => median is (2+3)/2 = 2.5 => (arr[n/2] + arr[n/2 - 1])/2
         */

        int m = nums1.length;
        int n = nums2.length;

        // Space Complexity: O(m + n)

        int[] mergedArray = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;

        // Time Complexity: O(m + n)
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                mergedArray[k++] = nums1[i++];
            } else {
                mergedArray[k++] = nums2[j++];
            }
        }

        while (i < m) {
            mergedArray[k++] = nums1[i++];
        }

        while (j < n) {
            mergedArray[k++] = nums2[j++];
        }

        System.out.println("Merged Array = " + Arrays.toString(mergedArray));

        int size = mergedArray.length;

        if (size % 2 == 0) {
            return (mergedArray[size / 2] + mergedArray[size / 2 - 1]) / 2.0;
        } else {
            return mergedArray[size / 2];
        }
    }
}
