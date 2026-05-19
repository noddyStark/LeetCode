package CompanyWise.Snowflake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
You are given a tree with n nodes rooted at node 0. The height of the tree is defined as the maximum number of nodes on a
path from the root to any leaf.

You are required to 'delete' the minimum number of nodes such that the height of the tree becomes at most k.
* Special Rule for Deletion: When a node is deleted, it is no longer counted toward the height. However,
* its children are effectively re-attached to its parent (or its ancestors), meaning the subtree rooted at the deleted
* node is still part of the overall structure and its remaining nodes still contribute to the height count of paths they
* belong to.

Follow-up Constraint: Among all sets of nodes that satisfy the minimum deletion count, find the set that maximizes the
* 'score'. Maximizing the score is defined as minimizing the sum of the original heights of the deleted
* nodes (prioritizing the deletion of nodes that are lower in the tree).

* Input Format:
An integer n: The number of nodes.
A 2D array edges: Representing the undirected edges of the tree.
An integer k: The target maximum height.
Output Format:
A list of integers representing the IDs of the nodes to be deleted.


Example 1:
Input: n = 5, edges = [[0, 1], [1, 2], [2, 3], [3, 4]], k = 3
Output: [3, 4]

Explanation: The path is 0-1-2-3-4. Deleting nodes 3 and 4 results in the path 0-1-2, which has a height of 3.

Example 2:
Input: n = 7, edges = [[0, 1], [0, 2], [1, 3], [1, 4], [2, 5], [2, 6]], k = 2
Output: [0]

Explanation: Deleting the root node (0) reduces the height contribution by 1 for all paths, bringing the height of subtrees within the limit of 2.

Constraints:
1 <= n <= 10^5
1 <= k <= n
The graph is a valid tree rooted at 0.
* */
public class MinimumNodeDeletionForMaximumTreeHeightK {

    static void main() {

        int nodes1 = 5;
        int nodes2 = 7;
        int[][] edges1 = {
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 4}};
        int[][] edges2 = {
                {0, 1},
                {0, 2},
                {1, 3},
                {1, 4},
                {2, 5},
                {2, 6}};
        int k1 = 3;
        int k2 = 2;

        List<Integer> result = minimumNodeDeletion(nodes2, edges2, k2);

        System.out.println(result);
    }

    /*
    EXAMPLE 1
                    0
                   /
                 1
                /
               2
              /
             3
            /
           4

     EXAMPLE 2
                     0
                   /   \
                 1       2
                / \     / \
               3   4   5   6

    */
    private static List<Integer> minimumNodeDeletion(int nodes, int[][] edges, int k) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < nodes; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] heights = new int[nodes];

        boolean[] visited = new boolean[nodes];
        precomputeHeights(0, graph, heights, visited);

        List<Integer> deletedNodes = new ArrayList<>();

        visited = new boolean[nodes];
        deleteNodes(0, graph, heights, deletedNodes, k, visited);

        return deletedNodes;

    }

    /*
    *               0
                   /
                 1
                /
               2
              /
             3
            /
           4
    * */
    private static int precomputeHeights(int node, List<List<Integer>> graph, int[] heights, boolean[] visited) {

        visited[node] = true;
        heights[node] = 1;

        System.out.println("node : " + node + " visited : " + Arrays.toString(visited) + " heights : " + Arrays.toString(heights));
        /*
                     0
                   /   \
                 1       2
                / \     / \
               3   4   5   6
        * [
        0->  [1, 2],
        1 -> [0, 3, 4],
        2 -> [0, 5, 6],
        3 -> [1],
        4 -> [1]
        5 -> [2],
        6 -> [2]

        *
        precomputeHeights(0)
        precomputeHeights(1)
        precomputeHeights(3)
        return height[3] = 1

        precomputeHeights(4)
        return height[4] = 1

        return height[1] = 2

        precomputeHeights(2)
        precomputeHeights(5)
        return height[5] = 1

        precomputeHeights(6)
        return height[6] = 1

         return height[2] = 2

        return height[0] = 3
        * */

        for (int child : graph.get(node)) {
            if (!visited[child]) {
                int childHeight = precomputeHeights(child, graph, heights, visited);
                System.out.println("Returning from recursion for child : " + child + " with childHeight = " + childHeight);
                heights[node] = Math.max(heights[node], childHeight + 1);
                System.out.println("Updated height of node = " + node + " heights = " + Arrays.toString(heights));
            }
        }

        return heights[node];
    }

    private static void deleteNodes(int node, List<List<Integer>> graph, int[] heights,
                                    List<Integer> deletedNodes, int k, boolean[] visited) {
        visited[node] = true;

        if (heights[node] > k) {
            deletedNodes.add(node);

            for (int child : graph.get(node)) {
                if (!visited[child]) {
                    deleteNodes(child, graph, heights, deletedNodes, k, visited);
                }
            }
        }
    }
}
