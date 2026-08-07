package NeetCode;

import java.util.ArrayList;
import java.util.List;

/*
Generate Parentheses
You are given an integer n. Return all well-formed parentheses strings that you can generate with n pairs of parentheses.

Example 1:
Input: n = 1
Output: ["()"]
Example 2:

Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
You may return the answer in any order.

Constraints:
1 <= n <= 7
*/
public class GenerateParentheses {


    static void main() {
        int n = 3;
        List<String> result = generateParenthesis(n);
        System.out.println(result);
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(n, result, new StringBuilder());
        return result;
    }

    // we have 2 options to select every time to either pick ( or pick ) and there are total 2n positions
    // TC : (2 ^ 2n or 4^n) * O(2n)
    // SC : O(2n)
    public static void backtrack(int n, List<String> result, StringBuilder sb) {

        if (sb.length() == 2 * n) {
            if (isValid(sb.toString())) {
                result.add(sb.toString());
            }
            return;
        }

        sb.append('(');
        backtrack(n, result, sb);
        sb.deleteCharAt(sb.length() - 1);

        sb.append(')');
        backtrack(n, result, sb);
        sb.deleteCharAt(sb.length() - 1);
    }

    // TC : O(2n)
    public static boolean isValid(String s) {
        int counter = 0;

        for (int i = 0; i < s.length(); i++) {

            if(s.charAt(i) == '(') {
                counter++;
            } else {
                counter--;
            }

            if (counter < 0) {
                return false;
            }
        }
        return counter == 0;
    }
}
