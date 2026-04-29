package org.leetcode.Graphs.TopologicalSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Time complexity: O(V + E)
// Space complexity: O(V + E)
public class TopologicalSort_BFS {

    public static void main(String[] args) {

        int[][] edges = {{3, 0}, {1, 0}, {2, 0}};
        int V = 4;

        ArrayList<Integer> result = topologicalSort(V, edges);

        // If graph has a cycle, some nodes will never become indegree 0. So result size will be less than V.
        if (result.size() != V) {
            System.out.println("Cycle exists, topological sort not possible");
        } else {
            System.out.println("Topological Sort = " + result);
        }
    }

    private static ArrayList<Integer> topologicalSort(int V, int[][] edges) {

        ArrayList<ArrayList<Integer>> adjacencyList = createAdjacencyList(V, edges);

        int[] indegree = calculateIndegree(V, adjacencyList);

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int u = queue.poll();
            result.add(u);

            for (int v : adjacencyList.get(u)) {
                indegree[v]--;

                if (indegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return result;
    }

    private static ArrayList<ArrayList<Integer>> createAdjacencyList(int V, int[][] edges) {

        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adjacencyList.get(u).add(v);
        }

        return adjacencyList;
    }

    private static int[] calculateIndegree(int V, ArrayList<ArrayList<Integer>> adjacencyList) {

        int[] indegree = new int[V];

        for (int u = 0; u < V; u++) {
            for (int neighbour : adjacencyList.get(u)) {
                indegree[neighbour]++;
            }
        }

        return indegree;
    }
}