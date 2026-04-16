package org.leetcode.Graphs.BinaryTree;


import java.util.*;

/*
 * 226. Invert Binary Tree
 * Given the root of a binary tree, invert the tree, and return its root.
 * <p>
 * Original Tree:                    Inverted Tree:
 *
 *        4                               4
 *      /   \                           /   \
 *     2     7        ------>          7     2
 *    / \   / \                       / \   / \
 *   1   3 6   9                     9   6 3   1
 *
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 *
 * ---------------------------------------------------
 * Example with NULL nodes:
 *
 * Original Tree:                    Inverted Tree:
 *
 *        4                               4
 *      /   \                           /   \
 *     2     null      ------>        null   2
 *    / \                                   / \
 *   1   null                              null 1
 *
 * Explanation:
 * - Each node's left and right children are swapped.
 * - This is a classic "Invert Binary Tree" problem.
 *
 * Rule:
 *   node.left  <->  node.right
 *
 * Example:
 *   swap(4) -> swap(2, 7)
 *   swap(2) -> swap(1, 3)
 *   swap(7) -> swap(6, 9)
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h) (recursion stack)
 *
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 *
 */
public class InvertBinaryTree_DFS {
    static void main() {

        Node<Integer> root = new Node<>(4);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node7 = new Node<>(7);
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node6 = new Node<>(6);
        Node<Integer> node9 = new Node<>(9);

        root.left = node2;
        root.right = node7;
        node2.left = node1;
        node2.right = node3;
        node7.left = node6;
        node7.right = node9;

        BinaryTreeHelper.printTree(root);
        Node<Integer> revertedTree = invertTree(root);

        System.out.print("Reverted Tree = ");
        BinaryTreeHelper.printTree(revertedTree);
    }

    private static Node<Integer> invertTree(Node<Integer> root) {

        if (root == null) return root;

        Node<Integer> left = invertTree(root.left);
        Node<Integer> right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }
}
