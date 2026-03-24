package org.leetcode;

import java.util.Arrays;


/**
 *
 *
 * Explanation -> The whole concept of this question is around to get the product of numbers on the left side of
 * ith index and right side of ith index, when we are standing at index i.
 * <p>
 * For example arr =  [1, 2, 3, 4], when this is the array, the product of numbers except itself would create
 * this resultant array => [ 2*3*4, 1*3*4, 1*2*4, 1*2*3] = [24, 12, 8, 6]
 * <p>
 * Now, if we are at index 2 in the input array, where arr[2] = 3, to get the desired result, we want to
 * get the product of integers on left side of index 2 and product of integers on right side of index 2.
 * <p>
 * LeftProduct = 1*2 = 2
 * RightProduct = 4
 * Result = LeftProduct * RightProduct = 2 * 4 = 8
 * <p>
 * THIS IS IMPORTANT, YOU SEE HERE WE MULTIPLY LeftProduct WITH RightProduct TO GET THE RESULT. WE WILL DO
 * SOMETHING SIMILAR HERE AS WELL TO GET THE RESULT.
 * <p>
 * We will first figure out the LeftProduct and then figure out the RightProduct and later multiply the
 * LeftProduct with RightProduct.
 * <p>
 *  IMPORTANT NOTES ->
 * <p>
 * <p> (1) WHEN CALCULATING LEFT PRODUCT [1, 2, 3, 4]
 *
 * <p> (a) Go left → right
 * <p> (b) When we start from the index 0 of the input array, the we assume the product is 1 in the beginning.
 * <p> (c) answer[0] = 1
 * <p> (d) answer[i] = numbers[i-1] * (whatever was the product of numbers till now, which is answer[i-1])
 * <p>     answer[i] = numbers[i-1] * answer[i-1]
 *
 * <p> (2) WHEN CALCULATING RIGHT PRODUCTS ->
 * <p> (a) Go right → left, and keep a variable:
 * <p> (b) rightProduct = 1
 * <p> (c) answer[i] = answer[i] × rightProduct
 * <p> (d) rightProduct = rightProduct * numbers[i]
 *
 * <p> Why this works
 * <p> At any index i:
 * <p> answer[i] already has → left product
 * <p> rightProduct has → right product
 *
 * <p> Multiply both → done ✅
 */
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4};
        System.out.println((" product of all the elements of numbers except numbers[i]. " +
                Arrays.toString(productOfArrayExceptItself(numbers))));
    }

    private static int[] productOfArrayExceptItself(int[] numbers) {
        int[] answer = new int[numbers.length];
        // answer[i] = product of everything BEFORE i
        answer[0] = 1; // (nothing on left)

        for (int i = 1; i < numbers.length; i++) {
            answer[i] = answer[i - 1] * numbers[i - 1];
        }

        /**
         * 🔹 Step 2: Multiply RIGHT products (no array!)
         *    1, 2, 3, 4
         * */
        int rightProduct = 1;

        for (int i = numbers.length - 1; i >= 0; i--) {
            answer[i] = answer[i] * rightProduct;
            rightProduct = rightProduct * numbers[i];
        }

        return answer;
    }
}
