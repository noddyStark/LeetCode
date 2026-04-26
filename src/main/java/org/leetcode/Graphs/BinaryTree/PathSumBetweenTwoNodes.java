package org.leetcode.Graphs.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 112. Path Sum
 * <p>
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf
 * path such that adding up all the values along the path equals targetSum.
 * <p>
 * A leaf is a node with no children.
 *
 */
public class PathSumBetweenTwoNodes {

    static void main() {

        Node<Integer> root = BinaryTreeHelper.createSampleTreeWithDefinedNulls();
        BinaryTreeHelper.printTree(root);
        int targetSum = 9;
        System.out.println("hasPathSum = " + hasPathSum(root, targetSum));
        System.out.println("hasPathSum Iterative = " + hasPathSumIterative(root, targetSum));
    }

    private static boolean hasPathSum(Node<Integer> root, int targetSum) {

        if (root == null) return false;

        // If it's a leaf node
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        int remaining = targetSum - root.val;

        return hasPathSum(root.left, remaining) || hasPathSum(root.right, remaining);

        //                  left                    |
        // 1 -> remaining = (9-1), hasPathSum(4, 8) |
        // 2 -> remaining = (8-4), hasPathSum(2, 4) |
        // 3 -> return false;                       | 3 -> return false;
    }

    /*
     * Input: [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
     *
     *                1
     *            /       \
     *           4         3
     *         /  \        / \
     *       2    4       2   5
     *      / \  / \     / \ / \
     *      N N N   N    N  N 4   6
     */
    private static boolean hasPathSumIterative(Node<Integer> root, int targetSum) {
        if (root == null) return false;

        Queue<Node<Integer>> queue = new LinkedList<>();
        Queue<Integer> sumQueue = new LinkedList<>();

        queue.offer(root);
        sumQueue.offer(root.val);

        while (!queue.isEmpty()) {
            Node<Integer> current = queue.poll();
            int currentSum = sumQueue.poll();

            if (current.left == null && current.right == null && currentSum == targetSum) {
                return true;
            }

            /*
            * Think of your queues like:
            Queue    = [node1, node2, node3]
            SumQueue = [sum1 , sum2 , sum3 ]

            Meaning:

            node1 has sum = sum1
            node2 has sum = sum2
            *
            * Basically when we are polling from both queue we are polling parallel as part of this code,
            * like for this node, this was the sum
            * These two queues:

            Queue<Node<Integer>> queue;
            Queue<Integer> sumQueue;

            are behaving like one combined structure:

            (node, sumForThatNode)
            * */
            if (current.left != null) {
                queue.offer(current.left);
                sumQueue.offer(currentSum + current.left.val);
            }

            if (current.right != null) {
                queue.offer(current.right);
                sumQueue.offer(currentSum + current.right.val);
            }
        }
        return false;
    }
}
