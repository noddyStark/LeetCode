package org.leetcode.Graphs.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class SmallestValueInBinaryTree {

    static void main() {
        Node<Integer> root = BinaryTreeHelper.createSampleTreeWithIntegers();
        BinaryTreeHelper.printTree(root);

        int smalledElement_dfs = dfsSmallestElement(root);
        System.out.println("Smallest Element in Tree via DFS = " + smalledElement_dfs);

        int smallestElement_bfs = bfsSmallestElement(root);
        System.out.println("Smallest Element in Tree via BFS = " + smallestElement_bfs);
    }

    /*
                  5
                /   \
               9     20
              / \   /  \
            15  17 18  20
    Postorder traversal (Left → Right → Root)
  */
    private static int dfsSmallestElement(Node<Integer> root) {

        if (root == null) {
            return Integer.MAX_VALUE;
        }

        int left = dfsSmallestElement(root.left);
        int right = dfsSmallestElement(root.right);

        return Math.min(root.val, Math.min(left, right));
    }

    /*
                  5
                /   \
               9     20
              / \   /  \
            15  17 18  20
    */
    private static int bfsSmallestElement(Node<Integer> root) {

        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.offer(root);

        int min = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node<Integer> current = queue.poll();

            min = Math.min(min, current.val);

            if (current.left != null) {
                queue.offer(current.left);
            }

            if(current.right != null) {
                queue.offer(current.right);
            }
        }

        return min;
    }
}
