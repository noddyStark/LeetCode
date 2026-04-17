package org.leetcode.Graphs.BinaryTree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class SumOfBinaryTree {

    static void main() {
        Node<Integer> root = BinaryTreeHelper.createSampleTreeWithIntegers();

        BinaryTreeHelper.printTree(root);

        int sum = sumOfNodesInBinaryTree(root);

        int dfsSum = dfsSum(root);

        System.out.println("dfsSum sum  = " + dfsSum);

        System.out.println("Sum of Nodes = " + sum);
    }

    /*
            5
          /   \
         9     20
        / \   /  \
      15  17 18  20
    */
    private static int sumOfNodesInBinaryTree(Node<Integer> root) {

        if (root == null) return 0;
        int sum = 0;

        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<Integer> current = queue.poll();
            // ITERATION 1 -> Polled 5 <- [], current = 5
            // ITERATION 2 -> Polled 9 <- [20], current = 9
            sum = sum + current.val;
            // sum = 5
            // sum = 5 + 9

            if (Objects.nonNull(current.left)) {
                queue.offer(current.left); // [9] -> [20, 15]
            }

            if (Objects.nonNull(current.right)) {
                queue.offer(current.right); // [9, 20] -> [20, 15, 17]
            }
        }

        return sum;
    }


    /*
          5
        /   \
       9     20
      / \   /  \
    15  17 18  20
  */
    private static int dfsSum(Node<Integer> root) {

        if (root == null) {
            return 0;
        }

        int left = dfsSum(root.left);
        int right = dfsSum(root.right);

        return left+right+root.val;
    }
}
