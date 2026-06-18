package Top75.Stack;

import java.util.Stack;

/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed)
parentheses substring. A well-formed parentheses string is one that follows these rules:

Open brackets must be closed by a matching pair in the correct order.
For example, given the string "(()", the longest valid parentheses substring is "()", which has a length of 2.
Another example is the string ")()())", where the longest valid parentheses substring is "()()", which has a length of 4.

Example 1:
Inputs:
s = "())))"
Output:
2
(Explanation: The longest valid parentheses substring is "()")

Example 2:
Inputs:
s = "((()()())"
Output:
8
(Explanation: The longest valid parentheses substring is "(()()())" with a length of 8)

Example 3:
Inputs:
s = ""
Output:
0
*/
public class LongestValidParentheses {

    static void main() {
        String input = "((()()())";
        String input1 = "()())()"; // ()())()
        String input2 = "()(()";
        System.out.println(longestValidParentheses(input1));
    }

    private static int longestValidParentheses(String input) {

        Stack<Integer> stack = new Stack<>();
        int maxLength = 0;
        int n = input.length();

        // This acts as the index before the start of a valid substring
        stack.push(-1); // Stack = [-1]

        for (int i = 0; i < n; i++) {

            char ch = input.charAt(i);

            if (ch == '(') {
                stack.push(i); // Stack = [-1, 0, 1, 2]
            } else {
                // ch == ')' i = 3
                stack.pop(); // 2, Stack = [-1, 0, 1]

                if (stack.isEmpty()) {
                    System.out.println("stack was empty :" + stack);
                    stack.push(i);
                } else {
                    // stack.peek() gives the index before the current valid substring started.
                    System.out.println("stack peek = " + stack.peek() + " at i = " + i);
                    int currentLength = i - stack.peek(); // 3 - 1 = 2
                    maxLength = Math.max(maxLength, currentLength);
                }
            }
        }

        System.out.println("Stack = " + stack);

        return maxLength;
    }
}
