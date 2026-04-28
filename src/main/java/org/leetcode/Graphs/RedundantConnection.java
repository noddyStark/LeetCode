package org.leetcode.Graphs;

/*
684. Redundant Connection
In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added.
The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.
The graph is represented as an array edges of length n where edges[i] = [a(i), b(i)], indicates that there is an edge
between nodes a(i) and b(i) in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes.
If there are multiple answers, return the answer that occurs last in the input.

Input: edges = [[1,2],[1,3],[2,3]]
Output: [2,3]

Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
Output: [1,4]
*/

import java.util.ArrayList;
import java.util.Arrays;

public class RedundantConnection {

    static void main() {

        int[][] edges = {
                {1, 2},
                {1, 3},
                {2, 3}
        };

        int[] answer = findRedundantConnection(edges);
        System.out.println("Answer = " + Arrays.toString(answer));
    }

    private static int[] findRedundantConnection(int[][] edges) {

        int n = edges.length;

        // adjacency list (1-based indexing)
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // process edges one by one
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            boolean[] visited = new boolean[n + 1];

            // check if path already exists
            if (hasPath(adjacencyList, visited, u, v)) {
                return edge; // this edge creates cycle
            }

            // otherwise add edge
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
            System.out.println("adjacencyList = " + adjacencyList);
        }

        return new int[0];
    }

    // Can I reach target starting from current?
    private static boolean hasPath(ArrayList<ArrayList<Integer>> adjacencyList,
                                   boolean[] visited,
                                   int current,
                                   int target) {

        // Current = 2 and target = 3

        // Current = 1 and target = 3

        // Current = 3 and target = 3
        System.out.println("Current = " + current + " and target = " + target);
        System.out.println("Visited = " + Arrays.toString(visited));
        // Visited = [false, false, false, false]
        // Visited = [false, false, true, false]

        if (current == target) {
            return true;
        }

        visited[current] = true;
        // Visited = [false, false, true, false]
        // Visited = [false, true, true, false]
        System.out.println("Visited = " + Arrays.toString(visited));


        for (int neighbor : adjacencyList.get(current)) {
            // neighbor = 1

            // neighbor = 2
            // neighbor = 3
            System.out.println("neighbor = " + neighbor);
            if (!visited[neighbor] && hasPath(adjacencyList, visited, neighbor, target)) {
                return true;
            }
        }

        System.out.println("false is being returned for current = " + current);

        return false;
    }
}
