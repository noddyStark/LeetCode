package org.leetcode.Graphs.BinaryTree.DepthFirstSearch;

import org.leetcode.Graphs.BinaryTree.BinaryTreeHelper;
import org.leetcode.Graphs.BinaryTree.Node;

import java.util.Stack;


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
public class DepthFirstSearch_Iterative {


    static void main() {
        Node root = BinaryTreeHelper.createSampleTree();
        BinaryTreeHelper.printTree(root);

        dfs(root);
    }

    private static void dfs(Node root) {

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        System.out.println("DFS Traversal...");


        while (!stack.isEmpty()) {
            Node curr = stack.pop();

            System.out.print(curr.val + "  ");

            if (curr.right != null) {
                stack.push(curr.right);
            }

            if (curr.left != null) {
                stack.push(curr.left);
            }

        }
    }
}
