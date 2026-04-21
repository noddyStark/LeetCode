package org.leetcode.Graphs.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 113 | Path Sum II
 *
 * Given the root of a binary tree and an integer targetSum,
 * return all root-to-leaf paths where the sum of the node values in the path equals targetSum.
 */
public class PathSumII {
    static void main() {
        Node<Integer> root = BinaryTreeHelper.createSampleTreeWithIntegers();
        BinaryTreeHelper.printTreeOfIntegers(root);

        int targetSum = 22; // [[5,4,11,2], [5,8,4,5]]

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();

        dfs(root, targetSum, result, currentPath);

        System.out.println("nodesWithPathSum = " + result);
    }

    /*
            5
          /   \
         4     8
        /     /  \
      11     13   4
      / \        / \
     7   2      5   1
    */

    private static void dfs(Node<Integer> root,
                            int targetSum,
                            List<List<Integer>> result,
                            List<Integer> currentPath) {

        if (root == null) return;

        // choose
        currentPath.add(root.val);

        // leaf + sum match
        if (root.left == null && root.right == null && targetSum == root.val) {
            result.add(new ArrayList<>(currentPath));
        } else {
            int remaining = targetSum - root.val;

            System.out.println("remaining = " + remaining);

            dfs(root.left, remaining, result, currentPath);
            System.out.println("remaining after root.left returned = " + remaining + " root.val = " + root.val);
            dfs(root.right, remaining, result, currentPath);
            System.out.println("remaining after root.right returned = " + remaining + " root.val = " + root.val);
        }

        // backtrack
        currentPath.remove(currentPath.size() - 1);
    }
}