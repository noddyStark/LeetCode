package Top75.Stack;

import java.util.Arrays;
import java.util.Stack;

public class NextSmallerElement {

    static void main() {
        int[] input = {2, 1, 3, 2, 4, 3};
        int[] result = nextSmallerElement(input);
        System.out.println(Arrays.toString(result));
    }

    private static int[] nextSmallerElement(int[] input) {

        int[] result = new int[input.length];

        Stack<Integer> stack = new Stack<>();

        for (int i=0; i<input.length; i++) {

           while (!stack.isEmpty() && input[i] < input[stack.peek()]) {
               int prevIndex = stack.pop();
               result[prevIndex] = input[i];
           }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }

        return result;
    }
}
