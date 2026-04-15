package org.leetcode.Graphs.BinaryTree;

import org.leetcode.Graphs.BinaryTree.Node;

public class BinaryTreeHelper {

    // Build and return the sample tree
    public static Node createSampleTree() {

        Node root = new Node('A');
        Node nodeB = new Node('B');
        Node nodeC = new Node('C');
        Node nodeD = new Node('D');
        Node nodeE = new Node('E');
        Node nodeF = new Node('F');
        Node nodeG = new Node('G');

        root.left = nodeB;
        root.right = nodeC;
        nodeB.left = nodeD;
        nodeB.right = nodeE;
        nodeC.left = nodeF;
        nodeC.right = nodeG;

        return root;
    }

    // Generic tree printer (level order)
    public static void printTree(Node root) {
        if (root == null) return;

        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.val + " ");

            if (current.left != null) queue.offer(current.left);
            if (current.right != null) queue.offer(current.right);
        }

        System.out.println();
    }
}