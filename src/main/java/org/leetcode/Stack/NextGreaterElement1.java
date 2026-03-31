package org.leetcode.Stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * 496. Next Greater Element I
 * <p>
 * All the integers of nums1 also appear in nums2.
 */
public class NextGreaterElement1 {
    public static void main(String[] args) {
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        System.out.println("Next Greater Element Array = " + Arrays.toString(nextGreaterElement(nums1, nums2)));
    }

    private static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];

        HashMap<Integer, Integer> nextGreaterMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                nextGreaterMap.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }

        while (!stack.isEmpty()) {
            nextGreaterMap.put(stack.pop(), -1);
        }

        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreaterMap.get(nums1[i]);
        }

        return result;
    }
}
