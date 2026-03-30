package org.leetcode.Stack;

import java.util.Arrays;

/**
 * 496. Next Greater Element I
 * <p>
 * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2.
 * If there is no next greater element, then the answer for this query is -1.
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * All integers in nums1 and nums2 are unique.
 * All the integers of nums1 also appear in nums2.
 */


public class NextGreaterElement {

    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        System.out.println("Next Greater Element Array = " + Arrays.toString(nextGreaterElement(nums1, nums2)));
    }

    private static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    int findNext = j;
                    while (findNext <= nums2.length - 1) {
                        if (findNext == nums2.length - 1) {
                            result[i] = -1;
                            break;
                        } else if (nums2[j] < nums2[findNext + 1]) {
                            result[i] = nums2[findNext + 1];
                            break;
                        }
                        findNext++;
                    }
                }
            }
        }
        return result;
    }
}
