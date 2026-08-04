package NeetCode;

import java.util.ArrayList;
import java.util.List;

/*
Combination Sum
You are given an array of distinct integers nums and a target integer target. Your task is to return a
list of all unique combinations of nums where the chosen numbers sum to target.

The same number may be chosen from nums an unlimited number of times. Two combinations are the same if
the frequency of each of the chosen numbers is the same, otherwise they are different.

You may return the combinations in any order and the order of the numbers in each combination can be in
any order.

Example 1:
Input:
nums = [2,5,6,9]
target = 9
Output: [[2,2,5],[9]]
Explanation:
2 + 2 + 5 = 9. We use 2 twice, and 5 once.
9 = 9. We use 9 once.

Example 2:
Input:
nums = [3,4,5]
target = 16
Output: [[3,3,3,3,4],[3,3,5,5],[4,4,4,4],[3,4,4,5]]

Example 3:
Input:
nums = [3]
target = 5
Output: []

Constraints:
All elements of nums are distinct.
1 <= nums.length <= 20
2 <= nums[i] <= 30
2 <= target <= 30
*/
public class CombinationSum {

    static void main() {
        int[] nums = {2, 5, 6, 9};
        int target = 9;

        List<List<Integer>> combinationSum = combinationSum(nums, target);
        System.out.println("combinationSum List = " + combinationSum);
    }

    public static List<List<Integer>> combinationSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();
        int currentSum = 0;
        List<Integer> currSumList = new ArrayList<>();
        backTrack(nums, currentSum, target, 0, result, currSumList);

        return result;
    }

            /*
        ROOT: [], sum=0, start=0

        ├── take 2: [2], sum=2, start=0
        │   │
        │   ├── take 2: [2,2], sum=4, start=0
        │   │   │
        │   │   ├── take 2: [2,2,2], sum=6, start=0
        │   │   │   │
        │   │   │   ├── take 2: [2,2,2,2], sum=8, start=0
        │   │   │   │   │
        │   │   │   │   ├── take 2: [2,2,2,2,2], sum=10
        │   │   │   │   │   └── ❌ sum > 9
        │   │   │   │   │
        │   │   │   │   └── skip 2: [2,2,2,2], sum=8, start=1
        │   │   │   │       │
        │   │   │   │       ├── take 5: [2,2,2,2,5], sum=13
        │   │   │   │       │   └── ❌ sum > 9
        │   │   │   │       │
        │   │   │   │       └── skip 5: [2,2,2,2], sum=8, start=2
        │   │   │   │           │
        │   │   │   │           ├── take 6: [2,2,2,2,6], sum=14
        │   │   │   │           │   └── ❌ sum > 9
        │   │   │   │           │
        │   │   │   │           └── skip 6: [2,2,2,2], sum=8, start=3
        │   │   │   │               │
        │   │   │   │               ├── take 9: [2,2,2,2,9], sum=17
        │   │   │   │               │   └── ❌ sum > 9
        │   │   │   │               │
        │   │   │   │               └── skip 9: [2,2,2,2], sum=8, start=4
        │   │   │   │                   └── ❌ no candidates left
        │   │   │   │
        │   │   │   └── skip 2: [2,2,2], sum=6, start=1
        │   │   │       │
        │   │   │       ├── take 5: [2,2,2,5], sum=11
        │   │   │       │   └── ❌ sum > 9
        │   │   │       │
        │   │   │       └── skip 5: [2,2,2], sum=6, start=2
        │   │   │           │
        │   │   │           ├── take 6: [2,2,2,6], sum=12
        │   │   │           │   └── ❌ sum > 9
        │   │   │           │
        │   │   │           └── skip 6: [2,2,2], sum=6, start=3
        │   │   │               │
        │   │   │               ├── take 9: [2,2,2,9], sum=15
        │   │   │               │   └── ❌ sum > 9
        │   │   │               │
        │   │   │               └── skip 9: [2,2,2], sum=6, start=4
        │   │   │                   └── ❌ no candidates left
        │   │   │
        │   │   └── skip 2: [2,2], sum=4, start=1
        │   │       │
        │   │       ├── take 5: [2,2,5], sum=9
        │   │       │   └── ✅ valid combination
        │   │       │
        │   │       └── skip 5: [2,2], sum=4, start=2
        │   │           │
        │   │           ├── take 6: [2,2,6], sum=10
        │   │           │   └── ❌ sum > 9
        │   │           │
        │   │           └── skip 6: [2,2], sum=4, start=3
        │   │               │
        │   │               ├── take 9: [2,2,9], sum=13
        │   │               │   └── ❌ sum > 9
        │   │               │
        │   │               └── skip 9: [2,2], sum=4, start=4
        │   │                   └── ❌ no candidates left
        │   │
        │   └── skip 2: [2], sum=2, start=1
        │       │
        │       ├── take 5: [2,5], sum=7, start=1
        │       │   │
        │       │   ├── take 5: [2,5,5], sum=12
        │       │   │   └── ❌ sum > 9
        │       │   │
        │       │   └── skip 5: [2,5], sum=7, start=2
        │       │       │
        │       │       ├── take 6: [2,5,6], sum=13
        │       │       │   └── ❌ sum > 9
        │       │       │
        │       │       └── skip 6: [2,5], sum=7, start=3
        │       │           │
        │       │           ├── take 9: [2,5,9], sum=16
        │       │           │   └── ❌ sum > 9
        │       │           │
        │       │           └── skip 9: [2,5], sum=7, start=4
        │       │               └── ❌ no candidates left
        │       │
        │       └── skip 5: [2], sum=2, start=2
        │           │
        │           ├── take 6: [2,6], sum=8, start=2
        │           │   │
        │           │   ├── take 6: [2,6,6], sum=14
        │           │   │   └── ❌ sum > 9
        │           │   │
        │           │   └── skip 6: [2,6], sum=8, start=3
        │           │       │
        │           │       ├── take 9: [2,6,9], sum=17
        │           │       │   └── ❌ sum > 9
        │           │       │
        │           │       └── skip 9: [2,6], sum=8, start=4
        │           │           └── ❌ no candidates left
        │           │
        │           └── skip 6: [2], sum=2, start=3
        │               │
        │               ├── take 9: [2,9], sum=11
        │               │   └── ❌ sum > 9
        │               │
        │               └── skip 9: [2], sum=2, start=4
        │                   └── ❌ no candidates left
        │
        └── skip 2: [], sum=0, start=1
            │
            ├── take 5: [5], sum=5, start=1
            │   │
            │   ├── take 5: [5,5], sum=10
            │   │   └── ❌ sum > 9
            │   │
            │   └── skip 5: [5], sum=5, start=2
            │       │
            │       ├── take 6: [5,6], sum=11
            │       │   └── ❌ sum > 9
            │       │
            │       └── skip 6: [5], sum=5, start=3
            │           │
            │           ├── take 9: [5,9], sum=14
            │           │   └── ❌ sum > 9
            │           │
            │           └── skip 9: [5], sum=5, start=4
            │               └── ❌ no candidates left
            │
            └── skip 5: [], sum=0, start=2
                │
                ├── take 6: [6], sum=6, start=2
                │   │
                │   ├── take 6: [6,6], sum=12
                │   │   └── ❌ sum > 9
                │   │
                │   └── skip 6: [6], sum=6, start=3
                │       │
                │       ├── take 9: [6,9], sum=15
                │       │   └── ❌ sum > 9
                │       │
                │       └── skip 9: [6], sum=6, start=4
                │           └── ❌ no candidates left
                │
                └── skip 6: [], sum=0, start=3
                    │
                    ├── take 9: [9], sum=9
                    │   └── ✅ valid combination
                    │
                    └── skip 9: [], sum=0, start=4
                        └── ❌ no candidates left
        */

    private static void backTrack(int[] nums,
                                  int currentSum,
                                  int target,
                                  int start,
                                  List<List<Integer>> result,
                                  List<Integer> currSumList) {

        if (currentSum == target) {
            result.add(new ArrayList<>(currSumList));
            return;
        }

        if (currentSum > target || start >= nums.length) {
            return;
        }

        currentSum += nums[start];
        currSumList.add(nums[start]);
        backTrack(nums, currentSum, target, start, result, currSumList);

        currentSum -= nums[start];
        currSumList.remove(currSumList.size() - 1);
        backTrack(nums, currentSum, target, start + 1, result, currSumList);
    }
}
