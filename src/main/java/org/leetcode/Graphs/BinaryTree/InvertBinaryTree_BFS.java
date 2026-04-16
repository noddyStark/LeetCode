package org.leetcode.Graphs.BinaryTree;


import java.util.*;

/*
 * 226. Invert Binary Tree
 * Given the root of a binary tree, invert the tree, and return its root.
 * <p>
 * Original Tree:                    Inverted Tree:
 *
 *        4                               4
 *      /   \                           /   \
 *     2     7        ------>          7     2
 *    / \   / \                       / \   / \
 *   1   3 6   9                     9   6 3   1
 *
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 *
 * ---------------------------------------------------
 * Example with NULL nodes:
 *
 * Original Tree:                    Inverted Tree:
 *
 *        4                               4
 *      /   \                           /   \
 *     2     null      ------>        null   2
 *    / \                                   / \
 *   1   null                              null 1
 *
 * Explanation:
 * - Each node's left and right children are swapped.
 * - This is a classic "Invert Binary Tree" problem.
 *
 * Rule:
 *   node.left  <->  node.right
 *
 * Example:
 *   swap(4) -> swap(2, 7)
 *   swap(2) -> swap(1, 3)
 *   swap(7) -> swap(6, 9)
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h) (recursion stack)
 *
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 *
 */
public class InvertBinaryTree_BFS {

    static void main() {

        Node<Integer> root = new Node<>(4);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node7 = new Node<>(7);
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node6 = new Node<>(6);
        Node<Integer> node9 = new Node<>(9);

        root.left = node2;
        root.right = node7;
        node2.left = node1;
        node2.right = node3;
        node7.left = node6;
        node7.right = node9;

        BinaryTreeHelper.printTree(root);
        Node revertedTree = invertTree(root);

        System.out.print("Reverted Tree = ");
        BinaryTreeHelper.printTree(revertedTree);
    }

    /*
     *        4                               4
     *      /   \                           /   \
     *     2     7        ------>          7     2
     *    / \   / \                       / \   / \
     *   1   3 6   9                     9   6 3   1
     *
     *        4                               4
     *      /   \                           /   \
     *     2     null      ------>        null   2
     *    / \                                   / \
     *   1   null                              null 1
     *
     */
    private static Node<Integer> invertTree(Node<Integer> root) {
        if (root == null) return null;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {


            /*
             *         4                               4
             *      /   \                           /   \
             *     2     7        ------>          7     2
             *    / \   / \                       / \   / \
             *   1   3 6   9                     9   6 3   1
             *
             *        4                               4
             *      /   \                           /   \
             *     2     null      ------>        null   2
             *    / \                                   / \
             *   1   null                              null 1
             */

            Node current = queue.poll(); // poll 9  <- [9, 6, 3, 1

            if (Objects.nonNull(current.right) && Objects.nonNull(current.left)) {
                Node right = current.right; // 3
                Node left = current.left; // 1

                current.right = left;
                queue.offer(right); // [9, 6, 3]

                current.left = right;
                queue.offer(left); // [9, 6, 3, 1]

            } else if (Objects.nonNull(current.left)) {
                Node left = current.left;
                current.left = null;
                current.right = left;
                queue.offer(left);

            } else if (Objects.nonNull(current.right)) {
                Node right = current.right;
                current.right = null;
                current.left = right;
                queue.offer(right);
            }
        }

        return root;
    }
}
