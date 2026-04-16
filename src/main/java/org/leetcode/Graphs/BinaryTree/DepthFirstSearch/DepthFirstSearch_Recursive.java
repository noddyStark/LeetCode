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

        Node toFind = new Node('G');

        dfs(root);

        boolean result = nodePresentinTree(root, toFind);

        System.out.println("Node : " + toFind.val + " is present in the tree = " + result);
    }

    private static void dfs(Node root) {

        if (root == null) return;

        System.out.print(root.val + " ");
        dfs(root.left);
        dfs(root.right);

    }

    private static boolean nodePresentinTree(Node root, Node toFind) {

        if (root == null) return false;

        if (root.val == toFind.val) return true;

        // search left, if found → stop immediately
        if (nodePresentinTree(root.left, toFind)) return true;

        // otherwise search right
        return nodePresentinTree(root.right, toFind);
    }
}

/**
 * | Type      | Order               |
 * | --------- | ------------------- |
 * | Preorder  | Root → Left → Right |
 * | Inorder   | Left → Root → Right |
 * | Postorder | Left → Right → Root |
 *
 */
