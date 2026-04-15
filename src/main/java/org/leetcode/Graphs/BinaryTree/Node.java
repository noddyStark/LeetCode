package org.leetcode.Graphs.BinaryTree;

public class Node {

    public char val;

    public Node left;
    public Node right;

    public Node(char val) {
        this.val = val;
    }

    public Node(char val, Node right, Node left) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
