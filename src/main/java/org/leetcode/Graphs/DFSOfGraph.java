package org.leetcode.Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a connected undirected graph containing V vertices represented by a 2-d adjacency list adj[][],
 * where each adj[i] represents the list of vertices connected to vertex i.
 * Perform a Depth First Search (DFS) traversal starting from vertex 0, visiting vertices from left to right
 * as per the given adjacency list, and return a list containing the DFS traversal of the graph.
 * <p>
 * Note: Do traverse in the same order as they are in the given adjacency list.
 * <p>
 * Examples:
 * Input: adj[][] = [[2, 3, 1], [0], [0, 4], [0], [2]]
 * Output: [0, 2, 4, 3, 1]
 * Explanation: Starting from 0, the DFS traversal proceeds as follows:
 * Visit 0 → Output: 0
 * Visit 2 (the first neighbor of 0) → Output: 0, 2
 * Visit 4 (the first neighbor of 2) → Output: 0, 2, 4
 * Backtrack to 2, then backtrack to 0, and visit 3 → Output: 0, 2, 4, 3
 * Finally, backtrack to 0 and visit 1 → Final Output: 0, 2, 4, 3, 1
 *
 */
public class DFSOfGraph {
    static void main() {

        int vertices = 5;

        // Step 1: Create the adjacencyList
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        // Step 2: Initialize empty lists for each vertex (1-based)
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        addEdge(adjacencyList, 0, 2);
        addEdge(adjacencyList, 0, 3);
        addEdge(adjacencyList, 0, 1);
        addEdge(adjacencyList, 2, 4);

        System.out.println(adjacencyList);

        boolean[] visited = new boolean[vertices];
        System.out.println(Arrays.toString(visited));

        ArrayList<Integer> result = new ArrayList<>();
        dfs(adjacencyList, visited, 0, result);

        System.out.println("Result = " + result);
    }

    private static void dfs(ArrayList<ArrayList<Integer>> adjacencyList, boolean[] visited,
                            int u, ArrayList<Integer> result) {

        if (visited[u]) {
            return;
        }

        visited[u] = true;
        result.add(u);
        List<Integer> neighbours = adjacencyList.get(u);

        for (int i = 0; i < neighbours.size(); i++) {
            int neighbour = neighbours.get(i);
            if (!visited[neighbour]) {
                dfs(adjacencyList, visited, neighbour, result);
            }
        }
    }

    private static void addEdge(ArrayList<ArrayList<Integer>> adjList, int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }
}
