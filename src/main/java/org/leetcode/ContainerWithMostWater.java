package org.leetcode;


/**
 * LeetCode 11 -> Container With Most Water
 * <p>
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * <p>
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * <p>
 * Return the maximum amount of water a container can store.
 * <p>
 * Notice that you may not slant the container.
 *
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] height = {1,1};

        System.out.println("Max Amount of Water that can be stored = " + containerWithMostWater(height));
    }

    private static int containerWithMostWater(int[] height) {
        int maxAmountOfWater = 0;
        int start = 0;
        int end = height.length - 1;

        while (start < end) {
            int currentHeight = Math.min(height[start], height[end]);
            int currentWidth = end - start;
            int currentArea = currentHeight * currentWidth;

            maxAmountOfWater = Math.max(maxAmountOfWater, currentArea);

            // Move the pointer with smaller height
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }

        return maxAmountOfWater;
    }
}
