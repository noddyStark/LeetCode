package org.leetcode.Stack;


import java.util.HashSet;
import java.util.Stack;

/**
 * Evaluate Reverse Polish Notation
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 * <p>
 * Evaluate the expression. Return an integer that represents the value of the expression.
 * <p>
 * Note that:
 * <p>
 * The valid operators are '+', '-', '*', and '/'.
 * Each operand may be an integer or another expression.
 * The division between two integers always truncates toward zero.
 * There will not be any division by zero.
 * The input represents a valid arithmetic expression in a reverse polish notation.
 * The answer and all the intermediate calculations can be represented in a 32-bit integer.
 *
 */
public class EvaluateReversePolishNotation {

    static void main() {
        String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println("Answer is = " + evalRPN(tokens));
    }

    private static int evalRPN(String[] tokens) {

        Stack<String> stack = new Stack<>();

        // '+', '-', '*', and '/'
        HashSet<String> setOfOperators = new HashSet<>();
        setOfOperators.add("+");
        setOfOperators.add("-");
        setOfOperators.add("*");
        setOfOperators.add("/");

        for (int i = 0; i < tokens.length; i++) {

            if (!setOfOperators.contains(tokens[i])) {
                stack.push(tokens[i]); // [4, 13, 5]
            } else {
                String operator = tokens[i];
                Integer last = Integer.valueOf(stack.pop());
                Integer secondLast = Integer.valueOf(stack.pop());

                int ans = 0;

                if (operator.equals("+")) {
                    ans = secondLast + last;
                }

                if (operator.equals("*")) {
                    ans = secondLast * last;
                }

                if (operator.equals("-")) {
                    ans = secondLast - last;
                }

                if (operator.equals("/")) {
                    ans = secondLast / last;
                }

                stack.push(Integer.toString(ans));
            }
        }

        return Integer.parseInt(stack.pop());
    }
}
