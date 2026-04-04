package org.leetcode.Stack;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * LeetCode 84
 * Largest Rectangle in Histogram
 * <p>
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
 * <p>
 * Example 1:
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * Idx:     0 1 2 3 4 5
 * Height:  2 1 5 6 2 3
 * <p>
 * █
 * R █
 * █ █ R R
 * █ █ R R   █
 * █   █ █ R R █ █
 * █ █ █ █ █ █ █ █
 * <p>
 * Why area = 10
 * <p>
 * The marked rectangle covers:
 * <p>
 * index 2 and 3
 * minimum height across those bars = 5
 * width = 2
 * --------------------
 *
 * <p>
 * Example 2:
 * Input: heights = [2,4]
 * Output: 4
 *
 *
 */
public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        int[] heights = {5, 6, 2};
        System.out.println("Largest Rectangle Area Is = " + largestRectangleArea(heights));
    }

    private static int largestRectangleArea(int[] heights) {
        int maxArea = heights[0];
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;

        for (int i = 0; i <= n; i++) {

            // At the extra step, pretend current height is 0
            // so all bars left in stack will get popped
            int currentHeight = (i == n) ? 0 : heights[i];

            // If current bar is smaller than stack top,
            // then stack top cannot extend further right
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {

                // Height of rectangle = popped bar height
                int poppedIndex = stack.pop();
                int height = heights[poppedIndex];

                // Previous smaller element on left
                // If stack is empty, it means no smaller bar exists on left
                int leftBoundary = stack.isEmpty() ? -1 : stack.peek();

                /*
                 * Current index i is the first smaller bar on the right
                 *
                 * So rectangle can only exist from:
                 * leftBoundary + 1  to  i - 1
                 *
                 * width = (i - 1) - (leftBoundary + 1) + 1
                 *       = i - leftBoundary - 1
                 */
                int width = i - leftBoundary - 1;

                // Area using popped bar as minimum height
                int area = height * width;

                // Update max area
                maxArea = Math.max(maxArea, area);
            }

            // Push current index into stack
            stack.push(i);
        }

        return maxArea;
    }
}

/**
 * 💡💡💡💡💡💡💡💡
 *
 * [1, 2, 3, 4, 1, 5, 6, 8]   size = 8
 *              ^ -> i
 *
 * The way to think about this is:
 * as long as heights are increasing, we keep pushing indices into the stack
 * because those bars still have a chance to form a bigger rectangle later.
 *
 * But the moment we see a smaller height than the one at the top of the stack,
 * that means the taller bar at the top can no longer continue further to the right.
 *
 * So now we must calculate the area for that bar.
 *
 * In the above example, when we reach the value 1 at index i,
 * every bar taller than 1 before it gets blocked.
 *
 * Example:
 * height 4 cannot continue beyond this point,
 * height 3 cannot continue beyond this point,
 * height 2 cannot continue beyond this point.
 *
 * That is why we keep popping from the stack and calculating area.
 *
 * Yes, it is true that now a rectangle of height 1 can potentially extend very far,
 * maybe even till the end of the array.
 * But we do not know yet whether that will be the maximum area,
 * so this height 1 stays in the stack and gets its chance later as well.
 *
 * Important:
 * when we find a smaller height at index i,
 * the rectangle for the popped height will end at index i - 1.
 *
 * So:
 * right boundary for the rectangle = i - 1
 *
 * And on the left side,
 * after popping, the new top of the stack tells us the previous smaller element.
 *
 * So:
 * left boundary for the rectangle = stack.peek() + 1
 *
 * Which means the rectangle exists from:
 * stack.peek() + 1   to   i - 1
 *
 * That is why width becomes:
 *
 * width = i - leftBoundaryIndex - 1
 *
 * where:
 * leftBoundaryIndex = previous smaller element index
 *
 * This is the main idea:
 * every time a smaller bar appears,
 * we finalize all taller rectangles that were waiting for their right boundary.
 */

