package org.leetcode.Graphs.CycleDetection;

import java.util.ArrayList;

/**
 * Given an undirected graph with V vertices and E edges,
 * where each entry edges[i] = [u, v] denotes an edge between vertices u and v, determine whether the graph contains a cycle or not.
 * <p>
 * Note: The graph can have multiple component.
 * <p>
 * Examples:
 * <p>
 * Input: V = 4, E = 4, edges[][] = [[0, 1], [0, 2], [1, 2], [2, 3]]
 * Output: true
 * Explanation:
 * <p>
 * 1 -> 2 -> 0 -> 1 is a cycle.
 *
 */
public class DetectCycleInAnUndirectedGraph_DFS {

    static void main() {
        int vertices = 5;
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        addEdge(adjacencyList, 0, 1);
        addEdge(adjacencyList, 1, 2);
        addEdge(adjacencyList, 1, 4);
        addEdge(adjacencyList, 2, 3);
        addEdge(adjacencyList, 3, 4);

        System.out.println(adjacencyList);

        boolean[] visited = new boolean[vertices];
        boolean result = isCycle(adjacencyList, visited);
        System.out.println("has cycle = " + result);
    }

    private static boolean isCycle(ArrayList<ArrayList<Integer>> adjacencyList, boolean[] visited) {

        for (int i = 0; i < adjacencyList.size(); i++) {
            if (!visited[i] && isCycleDFS(adjacencyList, visited, i, -1)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isCycleDFS(ArrayList<ArrayList<Integer>> adjacencyList,
                                      boolean[] visited,
                                      int u,
                                      int parent) {
        visited[u] = true;

        for (int v : adjacencyList.get(u)) {
            if (v == parent) {
                continue;
            }

            if (visited[v]) {
                return true;
            }

            if (isCycleDFS(adjacencyList, visited, v, u)) {
                return true;
            }
        }

        return false;
    }

    private static void addEdge(ArrayList<ArrayList<Integer>> adjacencyList, int u, int v) {
        adjacencyList.get(u).add(v);
        adjacencyList.get(v).add(u);
    }
}
