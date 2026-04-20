package org.leetcode.Graphs.BinaryTree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
* LeetCode 104 Maximum Depth of Binary Tree
*
* Given the root of a binary tree, return its maximum depth.
*
* A binary tree's maximum depth is the number of nodes along the longest path
* from the root node down to the farthest leaf node.
*
*
            5
          /   \
         9     20
        / \   /  \
      15  17 18  22
      /    \
     4      16
 */
public class MaximumDepthBinaryTree {

    static void main() {
        Node<Integer> root = BinaryTreeHelper.createSampleTreeWithIntegers();
        BinaryTreeHelper.printTreeOfIntegers(root);

        System.out.println("Height is = " + maxHeightOfBinaryTreeIterative(root));
        System.out.println(maxHeightOfBinaryTreeRecursive(root));
    }

    private static int maxHeightOfBinaryTreeIterative(Node<Integer> root) {
        int result = 0;

        if (root == null) return result;

        Stack<Node<Integer>> stack = new Stack<>();
        Map<Node<Integer>, Integer> heightAtNode = new HashMap<>();

        stack.push(root);
        heightAtNode.put(root, 1);

        while (!stack.isEmpty()) {
            Node<Integer> current = stack.pop();
            int currentHeight = heightAtNode.get(current);

            result = Math.max(result, currentHeight);

            if (current.right != null) {
                stack.push(current.right);
                heightAtNode.put(current.right, currentHeight + 1);
            }

            if (current.left != null) {
                stack.push(current.left);
                heightAtNode.put(current.left, currentHeight + 1);
            }
        }

        heightAtNode.forEach((node, height) ->
                System.out.println("Node: " + node.val + ", Height: " + height)
        );

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
    * */
    private static int maxHeightOfBinaryTreeRecursive(Node<Integer> root) {
        if (root == null) return 0;

        int leftHeight = maxHeightOfBinaryTreeRecursive(root.left);
        int rightHeight = maxHeightOfBinaryTreeRecursive(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
