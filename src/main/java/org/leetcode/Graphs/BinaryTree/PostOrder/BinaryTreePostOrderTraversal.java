package org.leetcode.Graphs.BinaryTree.PostOrder;

import org.leetcode.Graphs.BinaryTree.BinaryTreeHelper;
import org.leetcode.Graphs.BinaryTree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePostOrderTraversal {
    static void main() {

        Node<Integer> root = BinaryTreeHelper.createSampleTreeWithIntegers();
        BinaryTreeHelper.printTreeOfIntegers(root);
        List<Integer> result = postOrderTraversalViaRecursion(root);
        System.out.println("Recursion Result = " + result);
        System.out.println("postOrderTraversalIterative = " + postOrderTraversalIterative(root));
    }


    private static List<Integer> postOrderTraversalViaRecursion(Node<Integer> root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);

        return result;
    }


    private static void dfs(Node<Integer> root, List<Integer> result) {

        if (root == null) return;

        dfs(root.left, result);
        dfs(root.right, result);
        result.add(root.val);
    }

    /*
            5
          /   \
         9     20
        / \   /  \
      15  17 18  22
      /    \
     4      16
     Left Right Node

     Reverse Order -> Node Right Left
    */
    private static List<Integer> postOrderTraversalIterative(Node<Integer> root) {
        List<Integer> result = new ArrayList<>();
        Stack<Node<Integer>> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            Node<Integer> current = stack.pop(); // take current node
            result.add(current.val);

            // push left first, so right is processed first
            if (current.left != null) {
                stack.push(current.left);
            }

            if (current.right != null) {
                stack.push(current.right);
            }
        }

        System.out.println("result is " + result);

        return result.reversed();
    }
}
