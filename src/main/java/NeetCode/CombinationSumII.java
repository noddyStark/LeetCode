package NeetCode;

import java.util.*;

public class CombinationSumII {

    static void main() {

        int[] candidates = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1,
                2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int target = 28;

        List<List<Integer>> result = combinationSum2(candidates, target);

        System.out.println(result);
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> currentSumList = new ArrayList<>();
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, currentSumList, result, 0, 0);

        return new ArrayList<>(result);
    }

    public static void backtrack(int[] candidates, int target, List<Integer> currentSumList,
                                 Set<List<Integer>> result, int currentSum, int start) {

        if (currentSum == target) {
            result.add(new ArrayList<>(currentSumList));
            return;
        }

        if (currentSum > target || start >= candidates.length) {
            return;
        }

        currentSum += candidates[start];
        currentSumList.add(candidates[start]);
        backtrack(candidates, target, currentSumList, result, currentSum, start + 1);
        currentSum -= candidates[start];
        currentSumList.removeLast();

        // Exclude candidates[start] and all duplicate values.
        int nextStart = start + 1;

        while (nextStart < candidates.length &&
                candidates[nextStart] == candidates[start]) {
            nextStart++;
        }
        backtrack(candidates, target, currentSumList, result, currentSum, nextStart);
    }

}
