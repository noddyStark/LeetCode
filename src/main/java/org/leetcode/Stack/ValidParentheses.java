package org.leetcode.Stack;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * 20. Valid Parentheses
 * <p>
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "()"
 * <p>
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: s = "()[]{}"
 * <p>
 * Output: true
 * <p>
 * Example 3:
 * <p>
 * Input: s = "(]"
 * <p>
 * Output: false
 * <p>
 * Example 4:
 * <p>
 * Input: s = "([])"
 * <p>
 * Output: true
 * <p>
 * Example 5:
 * <p>
 * Input: s = "([)]"
 * <p>
 * Output: false
 *
 */
public class ValidParentheses {

    public static void main(String[] args) {
        String input = "([)]";
        System.out.println("IsValid Parentheses = " + isValid(input));
    }

    // We need to make sure the bracket that is opened first needs to be closed first as well.
    private static boolean isValid(String input) {

        if (input.length() % 2 != 0) {
            return false;
        }

        HashMap<Character, Character> mapOfClosingBrackets = new HashMap<>();

        Stack<Character> stack = new Stack<>();

        // Opening Brackets = ( { [
        // Closing Brackets = ) } ]
        mapOfClosingBrackets.put(')', '(');
        mapOfClosingBrackets.put('}', '{');
        mapOfClosingBrackets.put(']', '[');

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            // Since the input can never start with closing bracket, because if it does, then it is an invalid Parentheses
            // because it can never get closed then
            if (mapOfClosingBrackets.containsKey(ch)) {
                if (stack.isEmpty()) {
                    return false;
                }

                if (stack.peek() != mapOfClosingBrackets.get(ch)) {
                    return false;
                }

                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }
}
