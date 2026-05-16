package CompanyWise.Snowflake;

import java.util.Stack;

/*
917. Reverse Only Letters
Given a string s, reverse the string according to the following rules:

All the characters that are not English letters remain in the same position.
All the English letters (lowercase or uppercase) should be reversed.
Return s after reversing it.

Example 1:
Input: s = "ab-cd"
Output: "dc-ba"

Example 2:
Input: s = "a-bC-dEf-ghIj"
Output: "j-Ih-gfE-dCba"

Example 3:
Input: s = "Test1ng-Leet=code-Q!"
Output: "Qedo1ct-eeLg=ntse-T!"

Constraints:
1 <= s.length <= 100
s consists of characters with ASCII values in the range [33, 122].
s does not contain '\"' or '\\'.
*/
public class ReverseOnlyLetters {
    static void main() {

        String input = "a-bC-dEf-ghIj";
        String answer = reverseOnlyLetters(input);

        System.out.println("Answer = " + answer);
    }

    public static String reverseOnlyLetters(String s) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Character> stack = new Stack<>();

        System.out.println("Original sb = " + sb);

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (Character.isLetter(ch)) {
                stack.push(ch);
            }
        }

        int i = 0;

        while (!stack.isEmpty() && i < sb.length()) {

            if (Character.isLetter(sb.charAt(i))) {
                sb.setCharAt(i, stack.pop());
            }
            i++;
        }

        System.out.println("Updated sb = " + sb);


        return sb.toString();
    }
}
