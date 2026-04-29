package org.leetcode.Graphs.TopologicalSort;

import java.util.ArrayList;
import java.util.Stack;

/*
Given a Directed Acyclic Graph (DAG) of V (0 to V-1) vertices and E edges represented as a 2D list of edges[][],
where each entry edges[i] = [u, v] denotes a directed edge u -> v. Return the topological sort for the given graph.
Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every directed
edge u -> v, vertex u comes before v in the ordering.

Note: As there are multiple Topological orders possible, you may return any of them. If your returned Topological
sort is correct then the output will be true else false.

Examples:

Input: V = 4, E = 3, edges[][] = [[3, 0], [1, 0], [2, 0]]

Output: true
Explanation: The output true denotes that the order is valid. Few valid Topological orders for the given graph are:
[3, 2, 1, 0]
[1, 2, 3, 0]
[2, 3, 1, 0]

Examples:
* */
public class TopologicalSort_DFS {

    static void main() {

        int[][] edges = {{3, 0}, {1, 0}, {2, 0}};
        int V = 4;

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

        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(adjacencyList, visited, i, stack);
            }
        }

        ArrayList<Integer> topoSort = new ArrayList<>();

        while (!stack.isEmpty()) {
            topoSort.add(stack.pop());
        }

        System.out.println("TopoSort = " + topoSort);

    }

    private static void dfs(ArrayList<ArrayList<Integer>> adjacencyList, boolean[] visited, int u, Stack<Integer> stack) {

        visited[u] = true;

        for (int neighbour : adjacencyList.get(u)) {
            if (!visited[neighbour]) {
                dfs(adjacencyList, visited, neighbour, stack);
            }
        }
        stack.push(u);

    }
}
