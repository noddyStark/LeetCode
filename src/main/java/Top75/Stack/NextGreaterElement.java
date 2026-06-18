package Top75.Stack;

import java.util.Stack;

/*
Given an array of integers, find the next greater element for each element in the array. The next greater element of an element x
is the first element to the right of x that is greater than x. If there is no such element, then the next greater element is -1.
Example
Input: [2, 1, 3, 2, 4, 3]
Output: [3, 3, 4, 4, -1, -1]
*/
public class NextGreaterElement {
    static void main() {
        int[] input = {2, 1, 3, 2, 4, 3};

        int[] result = nextGreaterElement(input);

        for (int val : result) {
            System.out.print(val);
            System.out.print(" , ");
        }
    }

    private static int[] nextGreaterElement(int[] input) {

        int[] result = new int[input.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < input.length; i++) {
            while (!stack.isEmpty() && input[i] > input[stack.peek()]) {
                int index = stack.pop();
                result[index] = input[i];
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int index = stack.pop();
            result[index] = -1;
        }

        return result;
    }
}
