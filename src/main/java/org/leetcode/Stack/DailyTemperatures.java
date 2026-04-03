package org.leetcode.Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 739. Daily Temperatures
 * <p>
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that
 * answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 * <p>
 * Example 1:
 * <p>
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * Example 2:
 * <p>
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * Example 3:
 * <p>
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 *
 */
public class DailyTemperatures {

    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};

        System.out.println("Wait for the days to get warmer temperature = " + Arrays.toString(dailyTemperatures(temperatures)));
    }

    private static int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        // 73, 74, 75, 71, 69, 72, 76, 73
        for (int i = 0; i < temperatures.length; i++) {
            int currentTemperature = temperatures[i];
            while (!stack.isEmpty() && currentTemperature > temperatures[stack.peek()]) {
                int previousIndex = stack.pop();
                answer[previousIndex] = i - previousIndex;
            }

            stack.push(i);
        }

        return answer;
    }
}
