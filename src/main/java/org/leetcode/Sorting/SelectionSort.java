package org.leetcode.Sorting;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {

        int[] numbers = {7, -4, 1, 8, -7, 6, 9, 10, 2, 3, 5, 4};
        System.out.println("Selection Sort Result = " + Arrays.toString(selectionSort(numbers)));
    }

    private static int[] selectionSort(int[] numbers) {

        for (int i = 0; i < numbers.length; i++) {
            int minIndex = i;

            // 7, -4, 1, 8, -7, 6, 9, 10, 2, 3, 5, 4
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[minIndex]) {
                    minIndex = j; // 1
                }
            }

            int temp = numbers[i];
            numbers[i] = numbers[minIndex];
            numbers[minIndex] = temp;
        }

        return numbers;
    }
}
