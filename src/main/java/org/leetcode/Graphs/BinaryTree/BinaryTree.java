package org.leetcode.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/*

BINARY TREE

-> At most 2 children
-> Exactly 1 root
-> Exactly 1 path between root and any node

 *       A
 *     /   \
 *    B     C
 *   / \   /  \
 *  D   E  F    G
 *
 *
 */
public class BinaryTree {

    static void main() {

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

        printTree(root);
    }


    private static void printTree(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            for (int i = 0; i < queue.size(); i++) {
                Node curr = queue.poll();
                System.out.println(curr.val + " ");

                if (curr.left != null) {
                    queue.offer(curr.left); // Q = [C, D]
                }

                if (curr.right != null) {
                    queue.offer(curr.right); // Q = [C, D, E]
                }
            }

        }

    }
}
