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

    /*
            5
          /   \
         9     20
        / \   /  \
      15  17 18  22
      /    \
     4      16
    */
    public static Node<Integer> createSampleTreeWithIntegers() {

        Node<Integer> root = new Node<>(5);
        Node<Integer> node9 = new Node<>(9);
        Node<Integer> node20 = new Node<>(20);
        Node<Integer> node15 = new Node<>(15);
        Node<Integer> node17 = new Node<>(17);
        Node<Integer> node18 = new Node<>(18);
        Node<Integer> node22 = new Node<>(20);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node16 = new Node<>(16);

        root.left = node9;
        root.right = node20;
        node9.left = node15;
        node9.right = node17;
        node20.left = node18;
        node20.right = node22;
        node15.left = node4;
        node17.right = node16;

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