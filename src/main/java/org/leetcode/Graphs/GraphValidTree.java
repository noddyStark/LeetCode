package org.leetcode.Graphs;

import java.util.ArrayList;

/*
 * 261. Graph Valid Tree
 * Hint
 * You have a graph of n nodes labeled from 0 to n - 1.
 * You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates
 * that there is an undirected edge between nodes a(i) and b(i) in the graph.
 * <p>
 * Return true if the edges of the given graph make up a valid tree, and false otherwise.
 * <p>
 *       0
 *    /  |  \
 *   1   2   3
 *   |
 *   4
 * <p>
 * <p>
 * Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 * Output: true
 */
public class GraphValidTree {

    static void main() {
        int nodes = 5;
        int[][] edges = {
                {0, 1},
                {0, 2},
                {0, 3},
                {1, 4}
        };

        /*
        According to the definition of a tree, a tree is an undirected graph with no cycles,
        all the nodes are connected as one component, and any two nodes have exactly one path
        * For a valid Tree
        *    - No cycle should be there
        *    - Exactly 1 component (connected)
        * */

        boolean result = validTree(nodes, edges);
        System.out.println("Valid Tree = " + result);
    }

    private static boolean validTree(int nodes, int[][] edges) {

        if (edges.length != nodes - 1) {
            return false;
        }

        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < nodes; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }

        boolean[] visited = new boolean[nodes];

        if (hasCycle(adjacencyList, visited, 0, -1)) {
            return false;
        }

        // all nodes are connected or not. If a node is not visited, then it would be disconnected and that means it is not a tree
        for (boolean b : visited) {
            if (b != true) {
                return false;
            }
        }

        return true;
    }

    private static boolean hasCycle(ArrayList<ArrayList<Integer>> adjacencyList, boolean[] visited, int node, int parent) {

        visited[node] = true;

        for (int neighbour : adjacencyList.get(node)) {

            if (neighbour == parent) {
                continue;
            }

            if (visited[neighbour]) {
                return true;
            }

            if (hasCycle(adjacencyList, visited, neighbour, node)) {
                return true;
            }

        }
        return false;
    }
}
