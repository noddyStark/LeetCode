package org.leetcode;


import java.util.HashSet;

/**
 * LeetCode 217 : Contains Duplicate
 * */
public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] numbers = {1,2,3,4,1,3,8};
        System.out.println("Array Contains Duplicate : " + containsDuplicate(numbers));
    }

    private static boolean containsDuplicate(int[] numbers) {

        if (numbers.length == 1) return false;

        HashSet<Integer> setOfNumbers = new HashSet<>();
        for (int number : numbers) {
            if (setOfNumbers.contains(number)) {
                return true;
            }
            setOfNumbers.add(number);
        }
        return false;
    }
}
