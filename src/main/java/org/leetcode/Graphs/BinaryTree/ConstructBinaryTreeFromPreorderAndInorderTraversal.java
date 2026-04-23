package org.leetcode.Graphs.BinaryTree;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * <p>
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder
 * is the inorder traversal of the same tree, construct and return the binary tree.
 * <p>
 * Constraints:
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 *
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    /*
     * This pointer tracks the next root to create from preorder.
     * Preorder is Root -> Left -> Right, so every recursive call consumes one value from left to right.
     */
    private static int preOrderIndex;
    private static Map<Integer, Integer> inorderIndexMap;

    public static void main(String[] args) {
        int[] preorder = {3, 9, 4, 6, 20, 15, 7};
        int[] inorder = {4, 9, 6, 3, 15, 20, 7};

        Node<Integer> root = buildTree(preorder, inorder);
        BinaryTreeHelper.printTreeOfIntegers(root);
    }


    /*
     *               3
     *            /      \
     *          9         20
     *         /  \      /  \
     *        4    6    15   7
     *
     * PreOrder => 3 9 4 6 20 15 7 (V L R)
     * Inorder => 4 9 6 3 15 20 7  (L V R)
     *
     *
     * I know in Inorder, the first integer would be the left most element of the tree, and it's right and left child
     * would be null
     *
     * I know in Preorder, the first integer would be root of the binary tree.
     *
     * */
    private static Node<Integer> buildTree(int[] preorder, int[] inorder) {
        /*
         * Reset before every build so repeated calls start reading preorder from the root again.
         * The full inorder range represents the whole tree at the beginning.
         */
        preOrderIndex = 0;
        inorderIndexMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        System.out.println("inorderIndexMap = " + inorderIndexMap);
        // {3=3, 4=0, 20=5, 6=2, 7=6, 9=1, 15=4}

        return buildTree(preorder, 0, inorder.length - 1);
    }

    private static Node<Integer> buildTree(int[] preorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }

        int rootValue = preorder[preOrderIndex++];
        Node<Integer> root = new Node<Integer>(rootValue);

        int rootIndex = inorderIndexMap.get(rootValue);

        root.left = buildTree(preorder, inStart, rootIndex - 1);
        root.right = buildTree(preorder, rootIndex + 1, inEnd);

        return root;
    }
}
