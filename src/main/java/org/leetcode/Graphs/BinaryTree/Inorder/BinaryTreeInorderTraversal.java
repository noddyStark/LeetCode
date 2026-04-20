package org.leetcode.Graphs.BinaryTree.Inorder;

import org.leetcode.Graphs.BinaryTree.BinaryTreeHelper;
import org.leetcode.Graphs.BinaryTree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {

    static void main() {

        Node<Integer> root = BinaryTreeHelper.createSampleTreeWithIntegers();
        BinaryTreeHelper.printTreeOfIntegers(root);
        List<Integer> inOrderTraversal = inOrderTraversal(root);
        List<Integer> inOrderTraversalIterative = dfsIterative(root);
        System.out.println(inOrderTraversal);
        System.out.println("inOrderTraversalIterative = " + inOrderTraversalIterative);
    }

    private static List<Integer> inOrderTraversal(Node<Integer> root) {
        List<Integer> result = new ArrayList<>();
        dfsRecursive(root, result);
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
    *
    Output = [4 15 9 17 16 5 18 20 22]
    */
    private static void dfsRecursive(Node<Integer> root, List<Integer> result) {
        if (root == null) return;
        dfsRecursive(root.left, result);
        result.add(root.val);
        dfsRecursive(root.right, result);
    }

    // Inorder Traversal is a method to traverse a tree such that for each node,
    // you first traverse its left subtree,
    // then visit the node itself,
    // and finally traverse its right subtree.
    private static List<Integer> dfsIterative(Node<Integer> root) {
        List<Integer> result = new ArrayList<>();

        Stack<Node<Integer>> stack = new Stack<>();
        Node<Integer> current = root;

        while (current != null || !stack.isEmpty()) {

            // Go left
            while (current != null) {
                stack.push(current); // [5, 9, 15, 4]
                current = current.left;
            }
            current = stack.pop(); // current = 4

            // Visit
            result.add(current.val); // result = [4, 15]

            // Go right
            current = current.right; // current = null
        }
        return result;
    }
}
