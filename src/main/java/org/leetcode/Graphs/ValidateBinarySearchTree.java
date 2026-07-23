package org.leetcode.Graphs;

import org.leetcode.Graphs.BinaryTree.BinaryTreeHelper;
import org.leetcode.Graphs.BinaryTree.Node;


/*
98. Validate Binary Search Tree
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:

The left subtree of a node contains only nodes with keys strictly less than the node's key.
The right subtree of a node contains only nodes with keys strictly greater than the node's key.
Both the left and right subtrees must also be binary search trees.
* */
public class ValidateBinarySearchTree {

    public static boolean isValidBST() {
        Node<Integer> root = BinaryTreeHelper.createSampleTreeWithIntegers();
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(Node<Integer> root, long min, long max) {

        if (root == null) {
            return true;
        }

        if (root.val <= min || root.val >= max) {
            return false;
        }

        boolean isLeftValid = validate(root.left, min, (long) root.val);
        boolean isRightValid = validate(root.right, (long) root.val, max);

        return isLeftValid && isRightValid;
    }

    static void main() {
        System.out.println(isValidBST());
    }
}
