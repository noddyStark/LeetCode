package CompanyWise.Snowflake;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
45. Jump Game II

You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.

Each element nums[i] represents the maximum length of a forward jump from index i. In other words,
* if you are at index i, you can jump to any index (i + j) where:

0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach index n - 1.

Example 1:
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.


Example 2:
Input: nums = [2,3,0,1,4]
Output: 2


Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 1000
It's guaranteed that you can reach nums[n - 1].
**/

/*
* BFS tree:

                  0
               /     \
              1       2
           /  |  \     \
          2   3   4     3
              |         |
              4         4

        BFS levels:

        Level 0: [0]        -> 0 jumps
        Level 1: [1, 2]     -> 1 jump
        Level 2: [3, 4]     -> 2 jumps

        As soon as we reach index 4, answer = 2.
* */
public class JumpGameII {

    static void main() {
        int[] nums = {2, 3, 1, 1, 4};

        int result = jump(nums);

        System.out.println(result);
    }

    public static int jump(int[] nums) {

        int lastIndex = nums.length - 1;

        if (lastIndex == 0) {
            return 0;
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        // we start with 0 index, and from here we can jump to any other index, so we add it in the queue and mark it as visited
        queue.offer(0);
        visited.add(0);

        int jumps = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int index = queue.poll();
                System.out.println("Index =  " + index + " jumps = " + jumps);

                if (index == lastIndex) {
                    return jumps;
                }

                // if Index was 0, and nums[index] = 2, that means we can jump 1 index or 2 index at max.
                int maxJump = nums[index];
                // maxJump = 2

                for (int j = 1; j <= maxJump && index + j <= lastIndex; j++) {

                    int nextIndex = index + j;
                    // nextIndex = 1 & 2
                    System.out.println("nextIndex = " + nextIndex + " when index is = " + index);

                    if(!visited.contains(nextIndex)) {
                        queue.offer(nextIndex);
                        visited.add(nextIndex);
                    }

                }
            }
            // At this BFS level, the queue contains all indexes we can reach
            // using the current number of jumps.
            // We try jumping from each of those indexes to all possible next indexes.
            // After processing the whole level, it means we are moving to indexes
            // that require one more jump, so we increment jumps.
            jumps++;

        }
        return jumps;
    }
}
