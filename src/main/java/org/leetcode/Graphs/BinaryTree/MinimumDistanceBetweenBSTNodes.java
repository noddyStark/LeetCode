package org.leetcode.Graphs.BinaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 783. Minimum Distance Between BST Nodes
 * <p>
 * Given the root of a Binary Search Tree (BST),
 * return the minimum difference between the values of any two different nodes in the tree.
 *
 */
public class MinimumDistanceBetweenBSTNodes {


    static void main() {
        TreeNode root = new TreeNode(27);
        TreeNode node34 = new TreeNode(34);
        TreeNode node58 = new TreeNode(58);
        TreeNode node50 = new TreeNode(50);
        TreeNode node44 = new TreeNode(44);

        root.right = node34;
        node34.right = node58;
        node58.left = node50;
        node50.left = node44;

        int minDistance = minimumDistanceBetweenTwoNodes(root);

        System.out.println("Min Distance = " + minDistance);
    }

    // [27,null,34,null,58,50,null,44]
    /*
     *           27
     *             \
     *              34
     *                \
     *                58
     *               /
     *              50
     *              /
     *             44
     * */
    private static int minimumDistanceBetweenTwoNodes(TreeNode root) {
        List<Integer> inorderValues = new ArrayList<>();
        inorder(root, inorderValues);

        int minDistance = Integer.MAX_VALUE;

        for (int i = 1; i < inorderValues.size(); i++) {
            minDistance = Math.min(minDistance, inorderValues.get(i) - inorderValues.get(i - 1));
        }

        return minDistance;
    }

    private static void inorder(TreeNode root, List<Integer> inorderValues) {
        if (root == null) return;

        inorder(root.left, inorderValues);
        inorderValues.add(root.val);
        inorder(root.right, inorderValues);
    }
}

/*
*
  ✅ Time Complexity
    Step 1: Inorder traversal
    You visit every node once
    → O(n)
    Step 2: Traverse ArrayList to find min difference
    You loop through the list once
    → O(n)
 ✅ Total Time Complexity:
    O(n) + O(n) = O(n)



  ✅ Space Complexity
    1. ArrayList storage
    You store all n node values
    → O(n)
    2. Recursion stack (DFS)
    In worst case (skewed tree), height = n
    → O(n)`
    In balanced tree → O(log n)
  ✅ Total Space Complexity:

    Worst case (skewed tree): O(n) (list) + O(n) (stack) = O(n)

    Best / Average case (balanced tree): O(n) (list) + O(log n) (stack) ≈ O(n)
* */