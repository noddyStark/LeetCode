package org.leetcode.Stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * On a single-threaded CPU, we execute a program containing n functions. Each function has a unique ID between 0 and n-1.
 * <p>
 * Function calls are stored in a call stack: when a function call starts, its ID is pushed onto the stack,
 * and when a function call ends, its ID is popped off the stack. The function whose ID is at the top of the stack is
 * the current function being executed. Each time a function starts or ends, we write a log with the ID,
 * whether it started or ended, and the timestamp.
 * <p>
 * You are given a list logs, where logs[i] represents the ith log message formatted as a
 * string "{function_id}:{"start" | "end"}:{timestamp}". For example, "0:start:3" means a function call with function
 * ID 0 started at the beginning of timestamp 3, and "1:end:2" means a function call with function ID 1 ended at the
 * end of timestamp 2. Note that a function can be called multiple times, possibly recursively.
 * <p>
 * A function's exclusive time is the sum of execution times for all function calls in the program.
 * For example, if a function is called twice, one call executing for 2 time units and another call
 * executing for 1 time unit, the exclusive time is 2 + 1 = 3.
 * <p>
 * Return the exclusive time of each function in an array, where the value at the ith index represents the
 * exclusive time for the function with ID i.
 *
 */
public class ExclusiveTimeOfFunctions {

    static void main() {

        List<String> logs = List.of(
                "0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7"
        );

        int n = 2;

        int[] result = exclusiveTime(n, logs);
        System.out.println(Arrays.toString(result));
    }

    private static int[] exclusiveTime(int n, List<String> logs) {

        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();

        int prevTime = 0;

        for (String log : logs) {

            String[] parts = log.split(":");

            int id = Integer.parseInt(parts[0]);
            String action = parts[1];
            int time = Integer.parseInt(parts[2]);

            if (action.equals("start")) {

                if (!stack.isEmpty()) {
                    // previousFunction here is same as running function
                    int previousFunction = stack.peek();
                    result[previousFunction] += time - prevTime;
                }

                stack.push(id);
                System.out.println("stack = " + stack);
                prevTime = time;

            } else {
                int finishedId = stack.pop();

                int executionTime = time - prevTime + 1;
                result[finishedId] += executionTime;

                prevTime = time + 1;
            }
        }

        return result;
    }
}
