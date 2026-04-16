package org.leetcode.Graphs.BinaryTree.BreadthFirstSearch;

import org.leetcode.Graphs.BinaryTree.Node;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {

    static void main() {

        Node<Integer> root = new Node<Integer>(3);
        Node<Integer> node9 = new Node<Integer>(9);
        Node<Integer> node20 = new Node<Integer>(20);
        Node<Integer> node15 = new Node<Integer>(15);
        Node<Integer> node7 = new Node<Integer>(7);

        root.left = node9;
        root.right = node20;
        node9.left = null;
        node9.right = null;
        node20.left = node15;
        node20.right = node7;

        List<List<Integer>> levelOrder = levelOrder(root);

        printLevelOrder(levelOrder);
    }

    private static List<List<Integer>> levelOrder(Node<Integer> root) {
        List<List<Integer>> levelOrder = new ArrayList<>();

        if (root == null) {
            return levelOrder;
        }

        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size(); // number of nodes at current level
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                Node<Integer> node = queue.poll();
                currentLevel.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            levelOrder.add(currentLevel);
        }


        return levelOrder;
    }

    private static void printLevelOrder(List<List<Integer>> levelOrder) {

        for (int i = 0; i < levelOrder.size(); i++) {
            System.out.print(levelOrder.get(i) + " ");
        }
    }
}
