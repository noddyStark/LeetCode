package org.leetcode.Graphs.BinaryTree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class SumOfBinaryTree {

    static void main() {
        Node<Integer> root = BinaryTreeHelper.createSampleTreeWithIntegers();
        BinaryTreeHelper.printTree(root);

        int bfsSum = bfsSum(root);
        System.out.println("bfsSum of Nodes = " + bfsSum);

        int dfsSum = dfsSum(root);
        System.out.println("dfsSum of Nodes  = " + dfsSum);

    }

    /*
            5
          /   \
         9     20
        / \   /  \
      15  17 18  20
    */
    private static int bfsSum(Node<Integer> root) {

        if (root == null) return 0;
        int bfsSum = 0;

        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<Integer> current = queue.poll();
            // ITERATION 1 -> Polled 5 <- [], current = 5
            // ITERATION 2 -> Polled 9 <- [20], current = 9
            bfsSum = bfsSum + current.val;
            // bfsSum = 5
            // bfsSum = 5 + 9

            if (Objects.nonNull(current.left)) {
                queue.offer(current.left); // [9] -> [20, 15]
            }

            if (Objects.nonNull(current.right)) {
                queue.offer(current.right); // [9, 20] -> [20, 15, 17]
            }
        }

        return bfsSum;
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
