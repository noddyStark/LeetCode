package org.leetcode.Graphs.BinaryTree;


/**
 * Leetcode 110. Balanced Binary Tree
 *
 * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every
 * node never differs by more than one.
 *
 */
public class BalancedBinaryTree {

    static void main() {
        Node<Integer> root = BinaryTreeHelper.createSampleTreeWithIntegers();
        BinaryTreeHelper.printTreeOfIntegers(root);
        System.out.println("isBalanced = " + isBalanced(root));
    }

    private static boolean isBalanced(Node<Integer> root) {
        return heightOfSubTree(root) != -1;
    }

    /*
            5
          /   \
         9     20
        / \   /  \
      15  17 18  22
      /    \
     4      16
    */

    private static int heightOfSubTree(Node<Integer> root) {

        if (root == null) return 0;

        int leftHeight = heightOfSubTree(root.left);
        if (leftHeight == -1) return -1; // early stop

        int rightHeight = heightOfSubTree(root.right);
        if (rightHeight == -1) return -1; // early stop


        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // mark unbalanced
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
