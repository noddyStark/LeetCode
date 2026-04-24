package org.leetcode.DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ManyNumbersAreSmallerThanTheCurrentNumber {

    static void main() {
        int[] numbers = {8, 1, 2, 2, 3};
        int[] result = smallerNumbersThanCurrent(numbers);
        System.out.println("result = " + Arrays.toString(result));
        smallerNumbersThanCurrent(numbers);
        smallerNumbersThanCurrent_Frequency(numbers);
    }

    private static int[] smallerNumbersThanCurrent(int[] numbers) {
        int[] sorted = numbers.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> map = new HashMap<>();

        // store first occurrence index
        // {1=0, 2=1, 3=3, 8=4}
        for (int i = 0; i < sorted.length; i++) {
            map.putIfAbsent(sorted[i], i);
        }

        System.out.println("Numbers Array = " + Arrays.toString(numbers));
        System.out.println("Sorted Array = " + Arrays.toString(sorted));
        System.out.println("HashMap = " + map);

        int[] result = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            result[i] = map.get(numbers[i]);
        }

        return result;
    }

    // Using Frequency Array
    public static int[] smallerNumbersThanCurrent_Frequency(int[] numbers) {
        int[] count = new int[101];

        // Step 1: frequency
        for (int num : numbers) {
            count[num]++;
        }

        // Array ➡️  0  1  2  1  0  0  0  0  1
        // Index ➡️  0  1  2  3  4  5  6  7  8


        // “Instead of comparing numbers, we precompute how many numbers are ≤ each value.”
        // count[i] = number of elements ≤ i
        // count[i - 1] = number of elements < i
        for (int i = 1; i < 101; i++) {
            count[i] = count[i] + count[i-1];
        }

        System.out.print("Count Array Update = ");
        // Array ➡️  0  1  3  4  4  4  4  4  5
        // Index ➡️  0  1  2  3  4  5  6  7  8
        //                    ⬆️
        // so this basically means, number 3 was present in the array
        // and there are total 4 numbers which are less than or equal to 3 in the array
        // 4 is not the correct, since we want to subtract the number itself,
        // so correct answer would be three
        // count[3] = 4
        // means 4 numbers are ≤ 3 (1,2,2,3)
        // But we need strictly smaller (< 3)
        // So we use count[2]
        // count[2] = 3 → (1,2,2)
        // Hence answer = 3

        int[] result = new int[numbers.length];

        // Step 3: result
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                result[i] = 0;
            } else {
                result[i] = count[numbers[i] - 1];
                // count[x]     → number of elements ≤ x
                // count[x - 1] → number of elements < x   ← what we need
            }
        }

        return result;
    }
}
