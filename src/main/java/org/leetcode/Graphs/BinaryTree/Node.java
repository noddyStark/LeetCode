package org.leetcode.Graphs.BinaryTree;

public class Node<T> {

    public T val;
    public Node<T> left;
    public Node<T> right;

    public Node(T val) {
        this.val = val;
    }

    public Node(T val, Node right, Node left) {
        this.val = val;
        this.right = right;
        this.left = left;
    }
}
