package org.leetcode.Graphs.BinaryTree.DepthFirstSearch;

import org.leetcode.Graphs.BinaryTree.BinaryTreeHelper;
import org.leetcode.Graphs.BinaryTree.Node;

/*

BINARY TREE

-> At most 2 children
-> Exactly 1 root
-> Exactly 1 path between root and any node

 *       A
 *     /   \
 *    B     C
 *   / \   /  \
 *  D   E  F    G
 *
 * Time Complexity: O(n) → visit each node once
 * Space Complexity: O(h) → height of tree (worst O(n))
 */
public class DepthFirstSearch_Recursive {
    static void main() {
        Node root = BinaryTreeHelper.createSampleTree();
        BinaryTreeHelper.printTree(root);

        dfs(root);
    }

    private static void dfs(Node root) {

        if (root == null) return;

        System.out.print(root.val + " ");
        dfs(root.left);
        dfs(root.right);

    }
}

/**
 * | Type      | Order               |
 * | --------- | ------------------- |
 * | Preorder  | Root → Left → Right |
 * | Inorder   | Left → Root → Right |
 * | Postorder | Left → Right → Root |
 * */
