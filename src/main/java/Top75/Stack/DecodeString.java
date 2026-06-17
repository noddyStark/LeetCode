package Top75.Stack;

import java.util.Stack;

/*

Given an encoded string, write a function to return its decoded string that follows a specific encoding rule: k[encoded_string],
where the encoded_string within the brackets is repeated exactly k times. Note that k is always a positive integer.
The input string is well-formed without any extra spaces, and square brackets are properly matched. Also, assume that the original data
doesn't contain digits other than the ones that specify the number of times to repeat the following encoded_string.

Inputs:
s = "3[a2[c]]"
Output:
"accaccacc"

*/
public class DecodeString {

    static void main() {

        String input = "3[a2[c]]";
        // 3[acc]
        // acc acc acc
        System.out.println(decodeString(input));
    }

    private static String decodeString(String input) {

        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (ch != ']') {
                stack.push(ch);
            } else {
                // 3[a2[c]]
                // Step 1: pop the string inside [...]
                StringBuilder encodedString = new StringBuilder();

                while (!stack.isEmpty() && stack.peek() != '[') {
                    encodedString.append(stack.pop());
                }

                // c
                encodedString.reverse();

                // Step 2: remove '['
                // stack = [3[a2
                stack.pop();

                // Step 3: get the number before '['
                StringBuilder number = new StringBuilder();

                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    number.append(stack.pop());
                }
                // stack = [3a[

                // 2
                number.reverse();

                int repeatCount = Integer.parseInt(number.toString());

                // Step 4: repeat the encoded string
                StringBuilder decodedString = new StringBuilder();

                for (int k = 0; k < repeatCount; k++) {
                    decodedString.append(encodedString);
                }

                // Step 5: push decoded string back into stack
                for (int k = 0; k < decodedString.length(); k++) {
                    stack.push(decodedString.charAt(k));
                }

            }
        }

        while(!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.reverse().toString();
    }
}
