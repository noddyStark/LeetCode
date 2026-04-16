package org.leetcode.Graphs.BinaryTree;

public class BinaryTreeHelper {

    // Build and return the sample tree
    public static Node<Character> createSampleTree() {

        Node<Character> root = new Node<>('A');
        Node<Character> nodeB = new Node<>('B');
        Node<Character> nodeC = new Node<>('C');
        Node<Character> nodeD = new Node<>('D');
        Node<Character> nodeE = new Node<>('E');
        Node<Character> nodeF = new Node<>('F');
        Node<Character> nodeG = new Node<>('G');

        root.left = nodeB;
        root.right = nodeC;
        nodeB.left = nodeD;
        nodeB.right = nodeE;
        nodeC.left = nodeF;
        nodeC.right = nodeG;

        return root;
    }

    public static Node<Integer> createSampleTreeWithIntegers() {

        Node<Integer> root = new Node<>(5);
        Node<Integer> nodeB = new Node<>(9);
        Node<Integer> nodeC = new Node<>(20);
        Node<Integer> nodeD = new Node<>(15);
        Node<Integer> nodeE = new Node<>(17);
        Node<Integer> nodeF = new Node<>(18);
        Node<Integer> nodeG = new Node<>(20);

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