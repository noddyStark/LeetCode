package org.leetcode.Graphs.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

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
        root.leftDefined = true;
        root.right = nodeC;
        root.rightDefined = true;

        nodeB.left = nodeD;
        nodeB.leftDefined = true;
        nodeB.right = nodeE;
        nodeB.rightDefined = true;

        nodeC.left = nodeF;
        nodeC.leftDefined = true;
        nodeC.right = nodeG;
        nodeC.rightDefined = true;

        return root;
    }

    /*
            5
          /   \
         4     8
        /     /  \
      11     13   4
      / \        / \
     7   2      5   1
    */
    public static Node<Integer> createSampleTreeWithIntegers() {

        Node<Integer> root = new Node<>(5);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node8 = new Node<>(8);
        Node<Integer> node11 = new Node<>(11);
        Node<Integer> node13 = new Node<>(13);
        Node<Integer> node4_2 = new Node<>(4);
        Node<Integer> node7 = new Node<>(7);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node1 = new Node<>(1);

        root.left = node4;
        root.right = node8;

        node4.left = node11;
        node11.left = node7;
        node11.right = node2;

        node8.left = node13;
        node8.right = node4_2;

        node4_2.left = node5;
        node4_2.right = node1;

        return root;
    }

    /*
     * Input: [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
     *
     *                1
     *            /       \
     *           4         3
     *         /  \        / \
     *       2    4       2   5
     *      / \  / \     / \ / \
     *      N N N   N    N  N 4   6
     */
    public static Node<Integer> createSampleTreeWithDefinedNulls() {
        Node<Integer> root = new Node<>(1);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node4_1 = new Node<>(4);
        Node<Integer> node2_1 = new Node<>(2);
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node4_2 = new Node<>(4);
        Node<Integer> node6 = new Node<>(6);

        // root = 1
        root.left = node4;
        root.leftDefined = true;
        root.right = node3;
        root.rightDefined = true;

        // 4
        node4.left = node2;
        node4.leftDefined = true;
        node4.right = node4_1;
        node4.rightDefined = true;

        // 3
        node3.left = node2_1;
        node3.leftDefined = true;
        node3.right = node5;
        node3.rightDefined = true;

        // 2  -> [null, null]
        node2.left = null;
        node2.leftDefined = true;
        node2.right = null;
        node2.rightDefined = true;

        // 4  -> [null, null]
        node4_1.left = null;
        node4_1.leftDefined = true;
        node4_1.right = null;
        node4_1.rightDefined = true;

        // 2  -> [null, null]
        node2_1.left = null;
        node2_1.leftDefined = true;
        node2_1.right = null;
        node2_1.rightDefined = true;

        // 5 -> [4, 6]
        node5.left = node4_2;
        node5.leftDefined = true;
        node5.right = node6;
        node5.rightDefined = true;

        // 4 and 6 have no entries after them in input,
        // so leave them UNDEFINED
        // node4_2.leftDefined = false;
        // node4_2.rightDefined = false;
        // node6.leftDefined = false;
        // node6.rightDefined = false;

        return root;
    }

    public static void printTree(Node<?> root) {
        if (root == null) {
            System.out.println("null");
            return;
        }

        Queue<Node<?>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<?> current = queue.poll();

            // Print current node value
            System.out.print(current.val + " ");

            // LEFT CHILD
            if (current.leftDefined) {
                if (current.left == null) {
                    System.out.print("null ");
                } else {
                    queue.offer(current.left);
                }
            }

            // RIGHT CHILD
            if (current.rightDefined) {
                if (current.right == null) {
                    System.out.print("null ");
                } else {
                    queue.offer(current.right);
                }
            }
        }

        System.out.println();
    }

    public static void printTreeOfIntegers(Node<?> root) {
        if (root == null) {
            System.out.println("null");
            return;
        }

        Queue<Node<?>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<?> current = queue.poll();

            System.out.print(current.val + " ");

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        System.out.println();
    }
}