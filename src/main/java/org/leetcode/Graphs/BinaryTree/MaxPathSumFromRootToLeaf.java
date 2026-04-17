package org.leetcode.Graphs.BinaryTree;

public class MaxPathSumFromRootToLeaf {

    static void main() {
        Node<Integer> root = BinaryTreeHelper.createSampleTreeWithIntegers();
        BinaryTreeHelper.printTree(root);

        int maxPathSum = maxPathSumFromRootToLeaf(root);

        System.out.println("Max Path Sum = " + maxPathSum);
    }

    /*
                5
              /   \
             9     20
            / \   /  \
          15  17 18  20
          /    \
         4      15
    */
    private static int maxPathSumFromRootToLeaf(Node<Integer> root) {

        if (root == null) {
            return Integer.MIN_VALUE;
        }

        // base case: leaf node
        if (root.left == null && root.right == null) {
            return root.val;
        }

        int left = maxPathSumFromRootToLeaf(root.left); // left = 4, root = 15
        int right = maxPathSumFromRootToLeaf(root.right); // right = MIN_VALUE, root = 15

        return root.val + Math.max(left, right);
    }
}
