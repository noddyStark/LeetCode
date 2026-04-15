package org.leetcode.Graphs.BinaryTree;

import java.util.*;

public class BinaryTreeLevelOrderTraversal {

    static void main() {

        TreeNode root = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);

        root.left = node9;
        root.right = node20;
        node9.left = null;
        node9.right = null;
        node20.left = node15;
        node20.right = node7;

        List<List<Integer>> levelOrder = levelOrder(root);

        printLevelOrder(levelOrder);
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelOrder = new ArrayList<>();

        if (root == null) {
            return levelOrder;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size(); // number of nodes at current level
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
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
