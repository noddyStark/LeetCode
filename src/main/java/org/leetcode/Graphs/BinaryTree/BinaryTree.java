package org.leetcode.Graphs.BinaryTree;

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

        Node<Character> root = BinaryTreeHelper.createSampleTree();
        BinaryTreeHelper.printTree(root);
    }
}
