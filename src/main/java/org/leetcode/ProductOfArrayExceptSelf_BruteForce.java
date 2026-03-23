package org.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProductOfArrayExceptSelf_BruteForce {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4};
        System.out.println((" product of all the elements of numbers except numbers[i]. " +
                Arrays.toString(productOfArrayExceptItself(numbers))));
    }


    /**
     * Constraints:
     *
     *  2 <= nums.length <= 105
     * -30 <= nums[i] <= 30
     * */
    private static int[] productOfArrayExceptItself(int[] numbers) {

        HashMap<List<Integer>, Integer> map = new HashMap<>();

        int[] result = new int[numbers.length];

        for (int i=0; i<numbers.length; i++) {
            int product = 1;
            for (int j=0; j<numbers.length; j++) {
                if(i != j) {
                    product = product * numbers[j];
                }
            }
            result[i] = product;
        }

        return result;
    }
}
