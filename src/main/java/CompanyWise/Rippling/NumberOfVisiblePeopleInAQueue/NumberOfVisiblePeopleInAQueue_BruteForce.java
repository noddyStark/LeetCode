package CompanyWise.Rippling.NumberOfVisiblePeopleInAQueue;


import java.util.Arrays;

/*
 * There are n people standing in a queue, and they numbered from 0 to n - 1 in left to right order.
 * You are given an array heights of distinct integers where heights[i] represents the height of the ith person.
 * A person can see another person to their right in the queue if everybody in between is shorter than both of them.
 * More formally, the ith person can see the jth person if i < j and min(heights[i], heights[j]) > max(heights[i+1], heights[i+2], ..., heights[j-1]).
 * Return an array answer of length n where answer[i] is the number of people the ith person can see to their right in the queue.
 *
 * Input: heights = [10,6,8,5,11,9]
 * Output: [3,1,2,1,1,0]
 * Explanation:
Person 0 can see person 1, 2, and 4.
Person 1 can see person 2.
Person 2 can see person 3 and 4.
Person 3 can see person 4.
Person 4 can see person 5.
Person 5 can see no one since nobody is to the right of them.
 * */
public class NumberOfVisiblePeopleInAQueue_BruteForce {

    static void main() {
        int[] heights = {10, 6, 8, 5, 11, 9};

        int[] result = canSeePersonsCount(heights);

        System.out.println("Result = " + Arrays.toString(result));
    }

    private static int[] canSeePersonsCount(int[] heights) {
        int n = heights.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int currentHeight = heights[i];

            int maxHeightBetween = 0;

            for (int j = i + 1; j < n; j++) {
                int nextHeight = heights[j];

                // Person j is visible if everyone between i and j
                // is shorter than both currentHeight and nextHeight
                if (maxHeightBetween < Math.min(currentHeight, nextHeight)) {
                    result[i]++;
                }

                // Update tallest person between i and future j
                maxHeightBetween = Math.max(maxHeightBetween, nextHeight);

                // Once we see someone taller than currentHeight,
                // they block everyone behind them
                if (nextHeight > currentHeight) {
                    break;
                }

            }
        }
        return result;
    }

}
