package Top75.Stack;

import java.util.Arrays;
import java.util.Stack;

/*
Given an integer array temps representing daily temperatures, write a function to calculate the number of days one has to wait for a
warmer temperature after each given day. The function should return an array answer where answer[i] represents the wait time for a warmer day
after the ith day. If no warmer day is expected in the future, set answer[i] to 0.

Inputs:

temps = [65, 70, 68, 60, 55, 75, 80, 74]
Output:

[1,4,3,2,1,1,0,0]
*/
public class DailyTemperatures {

    static void main() {
        int[] temp = {65, 70, 68, 60, 55, 75, 80, 74};
        int[] result = dailyTemperature(temp);
        System.out.println(Arrays.toString(result));
    }

    private static int[] dailyTemperature(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();

        int[] answer = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            int currentTemp = temperatures[i];

            while(!stack.isEmpty() && currentTemp > temperatures[stack.peek()]) {
                int previousIndex = stack.pop();
                answer[previousIndex] = i - previousIndex;
            }

            stack.push(i);
        }

        return answer;
    }
}
