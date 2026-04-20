package org.leetcode.Graphs.BinaryTree.PreOrder;

import org.leetcode.Graphs.BinaryTree.BinaryTreeHelper;
import org.leetcode.Graphs.BinaryTree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreOrderTraversal {
    static void main() {

        Node<Integer> root = BinaryTreeHelper.createSampleTreeWithIntegers();
        BinaryTreeHelper.printTreeOfIntegers(root);
        List<Integer> result = preOrderTraversalViaRecursion(root);
        System.out.println("Recursion Result = " + result);
        System.out.println("Iterative Result = " + preOrderIterativeTraversal(root));
    }

    private static List<Integer> preOrderTraversalViaRecursion(Node<Integer> root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
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
    private static void dfs(Node<Integer> root, List<Integer> result) {

        if (root == null) return;

        result.add(root.val);
        dfs(root.left, result);
        dfs(root.right, result);
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
    private static List<Integer> preOrderIterativeTraversal(Node<Integer> root) {
        if (root == null) return new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();

        Stack<Node<Integer>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {

            Node<Integer> current = stack.pop(); // take current node
            result.add(current.val); // visit root

            // push right first, so left is processed first
            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }
        }

        return result;
    }
}
