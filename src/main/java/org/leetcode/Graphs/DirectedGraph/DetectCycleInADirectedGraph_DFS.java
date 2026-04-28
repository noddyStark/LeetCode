package org.leetcode.Graphs.DirectedGraph;

import java.util.ArrayList;

/*
* Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges, check whether it contains any
* cycle or not.
The graph is represented as a 2D vector edges{}{}, where each entry edges{i} = {u, v} denotes an edge from vertex
* u to v.
*
* Input: V = 4, edges[][] = [[0, 1], [1, 2], [2, 0], [2, 3]]
*
*     1 ---→ 2
*     ↑    ↗ |
*     |   /  |
*     |  /   |
*     | /    ↓
*     0      3
* Output: true
* Explanation: The diagram clearly shows a cycle 0 → 1 → 2 → 0
*/
public class DetectCycleInADirectedGraph_DFS {

    static void main() {
        int V = 4;
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}, {2, 3}};

        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adjacencyList.get(u).add(v);
        }

        System.out.println(adjacencyList);
        boolean result = isCycle(adjacencyList, V);
        System.out.println("Result = " + result);
    }

    private static boolean isCycle(ArrayList<ArrayList<Integer>> adjacencyList, int vertices) {

        boolean[] visited = new boolean[vertices];
        boolean[] inRecursion = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            if (!visited[i] && isCycleDFS(adjacencyList, visited, i, inRecursion)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isCycleDFS(ArrayList<ArrayList<Integer>> adjacencyList,
                                      boolean[] visited,
                                      int node,
                                      boolean[] inRecursion) {

        visited[node] = true;
        inRecursion[node] = true;

        for (int neighbour : adjacencyList.get(node)) {
            // if not visited, we check for cycle further
            if(!visited[neighbour] && isCycleDFS(adjacencyList, visited, neighbour, inRecursion)) {
                return true;
            } else if (inRecursion[neighbour]) {
                return true;
            }
        }
        inRecursion[node] = false;
        return false;
    }
}
