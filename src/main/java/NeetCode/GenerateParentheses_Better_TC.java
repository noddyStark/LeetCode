package NeetCode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses_Better_TC {
    static void main() {
        int n = 3;
        List<String> result = generateParenthesis(n);
        System.out.println(result);
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(n, result, new StringBuilder(), 0, 0);
        return result;
    }

    public static void backtrack(int n, List<String> result, StringBuilder sb, int open, int close) {

        if (sb.length() == 2 * n) {
            result.add(sb.toString());
            return;
        }

        if (open < n) {
            sb.append('(');
            // open = 0 => (
            // open = 1 => ((
            // open = 2 => (((
            backtrack(n, result, sb, open + 1, close);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (close < open) {
            sb.append(')');
            backtrack(n, result, sb, open, close + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
