package org.leetcode.Graphs.BinaryTree;


/**
 * LeetCode 543 Diameter of Binary Tree
 * <p>
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * <p>
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * <p>
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 */
public class DiameterOfBinaryTree {

    private static int diameter = 0;

    static void main() {
        Node<Integer> root = BinaryTreeHelper.createSampleTreeWithDefinedNulls();
        BinaryTreeHelper.printTreeOfIntegers(root);
        heightOfBinaryTree(root);
        System.out.println("Diameter of Binary Tree = " + diameter);
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
    private static int heightOfBinaryTree(Node<Integer> root) {

        if (root == null) return 0;

        int leftHeight = heightOfBinaryTree(root.left);
        int rightHeight = heightOfBinaryTree(root.right);

        // Diameter passing through current node
        diameter = Math.max(diameter, leftHeight + rightHeight);

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
