package NeetCode;

import java.util.ArrayList;
import java.util.List;

/*
Subsets
Given an array nums of unique integers, return all possible subsets of nums.

The solution set must not contain duplicate subsets. You may return the solution in any order.

Example 1:
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Example 2:
Input: nums = [7]
Output: [[],[7]]

Constraints:
1 <= nums.length <= 10
-10 <= nums[i] <= 10

                []
               /  \
             [1]
*/
public class Subsets {

    static void main() {
        int[] nums = {1, 2, 3};
        List<Integer> current = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        subsets(nums, 0, current, result);
        System.out.println(result);
    }

    public static void subsets(int[] nums, int start, List<Integer> current,
                               List<List<Integer>> result) {

        if (start >= nums.length) {
            result.add(new ArrayList<>(current));
            //  result = [[1, 2, 3]] start = 3
            //  result = [[1, 2]] start = 3
            return;
        }

        current.add(nums[start]);
        // current = [1], start = 0
        // current = [1, 2], start = 1
        // current = [1, 2, 3], start = 2
        subsets(nums, start + 1, current, result);
        current.remove(current.size() - 1);
        // current = [1, 2], start = 2
        subsets(nums, start + 1, current, result);
    }
}
