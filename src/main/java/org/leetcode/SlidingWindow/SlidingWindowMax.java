package org.leetcode.SlidingWindow;

import java.util.*;

public class SlidingWindowMax {

    public static void main(String[] args) {

        int[] numbers = {9, 10, 9, -7, -4, -8, 2, -6};
        int k = 3;

        int[] result = slidingWindowWithMaxNumber(numbers, k);
        System.out.println("Sliding Window Of Maximum Numbers : " + Arrays.toString(result));
    }

    private static int[] slidingWindowWithMaxNumber(int[] numbers, int k) {

        int[] maxNumbersArray = new int[numbers.length - k + 1];

        Deque<Integer> deQueue = new ArrayDeque<>();

        // 9, 10, 9, -7, -4, -8, 2, -6
        for (int i = 0; i < numbers.length; i++) {

            // Remove Elements from the front of the DeQueue
            // left/start of window = i - k + 1
            // right/end of window = i
            // If the index at the front is too old and no longer inside the current window, remove it.
            while (!deQueue.isEmpty() && deQueue.peekFirst() <= i - k) {
                deQueue.pollFirst();
            }

            // If the new number is bigger than the numbers at the back, remove those smaller ones.
            while (!deQueue.isEmpty() && numbers[deQueue.peekLast()] < numbers[i]) {
                deQueue.pollLast();
            }

            deQueue.offerLast(i);

            if (i >= k - 1) {
                maxNumbersArray[i - k + 1] = numbers[deQueue.peekFirst()];
            }
        }
        return maxNumbersArray;
    }
}
