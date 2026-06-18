package Top75.Stack;

import java.util.Stack;

/*
Given an integer array heights representing the heights of histogram bars, write a function to find the largest rectangular
area possible in a histogram, where each bar's width is 1.

Inputs:
heights = [2,8,5,6,2,3]
Output:
15

*/
public class LargestRectangleInHistogram {

    static void main() {
        int[] heights = {2, 8, 5, 6, 2, 3};

        System.out.println(largestRectangleInHistogram(heights));
        System.out.println(largestRectangleInHistogramUsingNextSmallerAndPrevSmaller(heights));

    }

    private static int largestRectangleInHistogram(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= heights.length; i++) {

            int currentHeight = (i == heights.length) ? 0 : heights[i];

            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {

                int indexForCalculatingArea = stack.pop();
                int height = heights[indexForCalculatingArea];

                int previousSmallerElementIndex = stack.isEmpty() ? -1 : stack.peek();

                int width = (i - 1) - (previousSmallerElementIndex + 1) + 1;

                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }


        return maxArea;
    }

    /**
     * heights = [2, 8, 5, 6, 2, 3]
     * <p>
     * Each bar width = 1
     * <p>
     * Index:     0    1    2    3    4    5
     * heights:    2    8    5    6    2    3
     * Width:     1    1    1    1    1    1
     * <p>
     * Histogram:
     * <p>
     * |
     * |
     * |         |
     * |    |    |
     * |    |    |
     * |    |    |         |
     * |    |    |    |    |    |
     * |    |    |    |    |    |
     * --------------------------------
     * 0    1    2    3    4    5
     * <p>
     * IDEA 💡 :
     * <p>
     * 1. Let's say if we are at 2, and we need to maximize the area, then to calculate the area while being at index 2
     * we first need to make sure the distance that we can travel on right side and on left side.
     * 2. Since the heights at index 2 is 5, and if our goal is to maximize the area, then in the right we can only go to index 3,
     * since the heights at index 4 is 2, in other words we can say that, we need to know the index of next smaller element while
     * being at index 2.
     * 3. Similarly, in the left side, to get the max area while being at index 2, we can only go till index 1, since the heights
     * at index 1 is 2, in other words we can say that we need to know the previous smaller element index while being at index 2.
     * 4. Now to calculate the area,
     * (a) we can do width = (indexOfNextSmallerElement - 1) - (indexOfPreviousSmallerElement + 1) - 1.
     * (b) heights = heights[2]
     */

    private static int largestRectangleInHistogramUsingNextSmallerAndPrevSmaller(int[] heights) {
        int n = heights.length;

        /*
         * heights = [2, 8, 5, 6, 2, 3]
         *
         * right[i] = index of next smaller element on right
         * left[i]  = index of previous smaller element on left
         */

        int[] right = new int[n];
        int[] left = new int[n];

        Stack<Integer> stack = new Stack<>();

        /*
         * Next Smaller Element index on right
         */
        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int poppedIndex = stack.pop();

                // i is the next smaller index for poppedIndex
                right[poppedIndex] = i;
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int index = stack.pop();

            // no smaller element on right
            right[index] = n;
        }

        /*
         * Previous Smaller Element index on left
         */
        stack = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                int poppedIndex = stack.pop();

                // i is the previous smaller index for poppedIndex
                left[poppedIndex] = i;
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int index = stack.pop();

            // no smaller element on left
            left[index] = -1;
        }

        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            int height = heights[i];

            int width = right[i] - left[i] - 1;

            int area = height * width;

            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
