package org.leetcode;

/**
 * LeetCode 9 - Palindrome Number
 *
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 *
 * A palindrome number reads the same from left to right and right to left.
 *
 * Example 1:
 * Input: x = 121
 * Output: true
 * Explanation: 121 reads the same in both directions.
 *
 * Example 2:
 * Input: x = -121
 * Output: false
 * Explanation: From left to right it is -121, but from right to left it becomes 121-.
 *
 * Example 3:
 * Input: x = 10
 * Output: false
 * Explanation: From right to left it becomes 01, which is not the same as 10.
 *
 * Follow up:
 * Could you solve it without converting the integer to a string?
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        int input = 1234567899;
        System.out.println("Input Number is : " + input);
        System.out.println("Number " + input + " is Palindrome : " + isPalindrome(input));
    }

    static boolean isPalindrome(int number) {

        /*
         * Quick rejection cases:
         *
         * 1. Negative numbers are never palindrome.
         *    Example: -121 != 121-
         *
         * 2. Any number ending with 0 cannot be palindrome,
         *    unless the number itself is 0.
         *    Example: 10 -> reverse is 01, so not palindrome
         */
        if (number < 0 || (number % 10 == 0 && number != 0)) {
            return false;
        }

        /*
         * ============================================================
         * Solution 1: Convert number to char array and compare both ends
         * ============================================================
         *
         * Idea:
         * Convert the number to a string, then to a character array.
         * Use two pointers:
         * - one starting from the left
         * - one starting from the right
         * Compare characters until they meet.
         *
         * Example:
         * number = 99899
         * arr = ['9', '9', '8', '9', '9']
         *
         * Compare:
         * arr[0] == arr[4]
         * arr[1] == arr[3]
         * middle element does not matter
         *
         * Time Complexity: O(d)
         * Space Complexity: O(d)
         *
         * where d = number of digits = log10(n) + 1
         */
//        char[] arr = Integer.toString(number).toCharArray();
//        int start = 0;
//        int end = arr.length - 1;
//
//        while (start < end) {
//            if (arr[start] != arr[end]) {
//                return false;
//            }
//            start++;
//            end--;
//        }
//
//        return true;

        /*
         * ============================================================
         * Solution 2: Build reversed number as a String
         * ============================================================
         *
         * Idea:
         * Extract digits from the end using % 10 and append them into a StringBuilder.
         * This creates the reversed representation of the number.
         *
         * Example:
         * number = 99899
         *
         * Step 1: 99899 % 10 = 9, number = 9989,  sb = "9"
         * Step 2: 9989  % 10 = 9, number = 998,   sb = "99"
         * Step 3: 998   % 10 = 8, number = 99,    sb = "998"
         * Step 4: 99    % 10 = 9, number = 9,     sb = "9989"
         * Step 5: 9     % 10 = 9, number = 0,     sb = "99899"
         *
         * Then convert that string into a number and compare with original.
         *
         * Important:
         * Use long instead of int for reversed number, because reversing an int
         * may overflow int range.
         *
         * Example:
         * input = 1234567899
         * reversed = 9987654321, which does not fit in int
         *
         * Time Complexity: O(d)
         * Space Complexity: O(d)
         */
//        StringBuilder sb = new StringBuilder();
//        long original = number;
//
//        while (number > 0) {
//            sb.append(number % 10);
//            number = number / 10;
//        }
//
//        long reversed = Long.parseLong(sb.toString());
//
//        System.out.println("Reversed number is " + reversed);
//
//        return original == reversed;

        /*
         * ============================================================
         * Solution 3: Reverse the full number mathematically
         * ============================================================
         *
         * Idea:
         * Instead of using a string, build the reversed number mathematically.
         *
         * Formula:
         * reversed = reversed * 10 + lastDigit
         *
         * Example:
         * number = 123
         *
         * Iteration 1:
         * digit = 3
         * reversed = 0 * 10 + 3 = 3
         * number = 12
         *
         * Iteration 2:
         * digit = 2
         * reversed = 3 * 10 + 2 = 32
         * number = 1
         *
         * Iteration 3:
         * digit = 1
         * reversed = 32 * 10 + 1 = 321
         * number = 0
         *
         * Compare original with reversed.
         *
         * Time Complexity: O(d)
         * Space Complexity: O(1)
         *
         * Note:
         * This is better than the string-based approach because it uses constant space.
         * But reversing the full integer can overflow in some problems.
         */
//        int original = number;
//        int reversed = 0;
//
//        while (number > 0) {
//            int digit = number % 10;
//            reversed = reversed * 10 + digit;
//            number = number / 10;
//        }
//
//        return original == reversed;

        /*
         * ============================================================
         * Solution 4: Reverse only half of the number (Optimal Solution)
         * ============================================================
         *
         * This is the best solution for the problem.
         *
         * Idea:
         * We do not need to reverse the full number.
         * We only need to reverse the last half and compare it with the first half.
         *
         * Why?
         * In a palindrome, left half and right half are mirror images.
         *
         * Example 1: Even number of digits
         * number = 1221
         *
         * Build reversed half from the end:
         * number = 1221, reversedHalf = 0
         * number = 122,  reversedHalf = 1
         * number = 12,   reversedHalf = 12
         *
         * Stop when number <= reversedHalf
         *
         * Compare:
         * number == reversedHalf
         * 12 == 12 -> true
         *
         * Example 2: Odd number of digits
         * number = 99899
         *
         * number = 99899, reversedHalf = 0
         * number = 9989,  reversedHalf = 9
         * number = 998,   reversedHalf = 99
         * number = 99,    reversedHalf = 998
         *
         * Now stop because number <= reversedHalf
         *
         * At this point:
         * - number contains the first half
         * - reversedHalf contains the reversed right half,
         *   and for odd length numbers it also includes the middle digit
         *
         * So:
         * number = 99
         * reversedHalf = 998
         *
         * Since the middle digit does not matter in a palindrome,
         * remove it using reversedHalf / 10:
         *
         * 998 / 10 = 99
         *
         * Then compare:
         * 99 == 99 -> true
         *
         * Final comparison rules:
         * 1. Even digits: number == reversedHalf
         * 2. Odd digits:  number == reversedHalf / 10
         *
         * Time Complexity: O(d / 2) -> O(d) -> O(log10(n))
         * Space Complexity: O(1)
         */
        int reversedHalf = 0;

        while (number > reversedHalf) {
            int digit = number % 10;
            reversedHalf = reversedHalf * 10 + digit;
            number = number / 10;
        }

        return number == reversedHalf || number == reversedHalf / 10;
    }
}