package NeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
Task Scheduler
You are given an array of CPU tasks tasks, where tasks[i] is an uppercase english character from A to Z.
You are also given an integer n.
Each CPU cycle allows the completion of a single task, and tasks may be completed in any order.
The only constraint is that identical tasks must be separated by at least n CPU cycles, to cooldown the CPU.

Return the minimum number of CPU cycles required to complete all tasks.

Example 1:
Input: tasks = ["X","X","Y","Y"], n = 2
Output: 5
Explanation: A possible sequence is: X -> Y -> idle -> X -> Y.

Example 2:
Input: tasks = ["A","A","A","B","C"], n = 3
Output: 9
Explanation: A possible sequence is: A -> B -> C -> Idle -> A -> Idle -> Idle -> Idle -> A.

Constraints:
1 <= tasks.length <= 1000
0 <= n <= 100
*/
public class TaskScheduler {

    public static int leastInterval(char[] tasks, int n) {
        int totalCycles = 0;

        int[] frequency = new int[26];

        for (char ch : tasks) {
            frequency[ch - 'A']++;
        }

        // frequency = [  3     ,   1    ,    1,      0,0,0,0,0,0,0,0,0,0,0,0...]
        // Index         0 (A)    1 (B)      1 (C)

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));

        for (int count : frequency) {
            if (count > 0) {
                maxHeap.offer(count);
            }
        }

        /*
        maxHeap = [3, 1, 1]

        Our task should be completed somewhat like this which will get the count to 9, when n = 3
                   A _ B _ C _ Idle _ A _ Idle _ Idle _ Idle _ A
                   0   1   1    3     4
        */

        while (!maxHeap.isEmpty()) {

            List<Integer> remainingTasks = new ArrayList<>();

            int executedTasks = 0;

            // One cooldown window has n + 1 positions (look above for help)
            for (int i = 0; i < n + 1 && !maxHeap.isEmpty(); i++) {
                int count = maxHeap.poll(); // 3
                count--;

                if (count > 0) {
                    remainingTasks.add(count);
                }

                executedTasks++;
            }

            for (int count : remainingTasks) {
                maxHeap.offer(count);
            }

            if (maxHeap.isEmpty()) {
                // No more tasks remain, so no trailing idle time is needed.
                totalCycles += executedTasks;
            } else {
                // Tasks remain, so this entire window is used,
                // including any idle positions.
                totalCycles += n + 1;
            }
        }

        return totalCycles;
    }

    static void main() {
        char[] tasks = {'A', 'A', 'A', 'B', 'C' };
        int n = 3;
        int result = leastInterval(tasks, n);
        System.out.println("Total Cycles required = " + result);
    }
}
