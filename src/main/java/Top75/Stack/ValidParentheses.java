package Top75.Stack;

import java.util.HashMap;
import java.util.Stack;

/*
Given an input string s consisting solely of the characters '(', ')', '{', '}', '[' and ']', determine whether s is a valid string. A string is considered valid if every
opening bracket is closed by a matching type of bracket and in the correct order, and every closing bracket has a corresponding opening bracket of the same type.

Example 1:

Inputs:
s = "(){({})}"
Output:
True

Example 2:
Inputs:
s = "(){({}})"
Output:
False
*/
public class ValidParentheses {

    static void main() {
        String s = "(){({})}";
        System.out.println(isValid(s));
    }

    public static Boolean isValid(String s) {

        if (s.length() % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        HashMap<Character, Character> mapOfClosingBrackets = new HashMap<>();
        mapOfClosingBrackets.put(')', '(');
        mapOfClosingBrackets.put('}', '{');
        mapOfClosingBrackets.put(']', '[');

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // For every closing bracket, the most recent unmatched opening bracket must be of the same type.
            if (mapOfClosingBrackets.containsKey(ch)) {
                if(stack.isEmpty()) {
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
