package org.leetcode.Graphs.DirectedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DetectCycleInADirectedGraph_BFS {
    static void main() {
        int V = 4;
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}, {2, 3}};

        boolean result = hasCycle(V, edges);

        System.out.println("Has Cycle = " + result);
    }

    private static boolean hasCycle(int V, int[][] edges) {

        ArrayList<ArrayList<Integer>> adjacencyList = createAdjacencyList(edges, V);
        int[] indegree = createIndegreeArray(adjacencyList, V);

        Queue<Integer> queue = new LinkedList<>();

        int countOfNodes = 0;

        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                countOfNodes++;
            }
        }

        System.out.println(Arrays.toString(indegree));


        while(!queue.isEmpty()) {
            int current = queue.poll();

            for (int v : adjacencyList.get(current)) {
                indegree[v]--;

                if (indegree[v] == 0) {
                    queue.offer(v);
                    countOfNodes++;
                }

            }
        }

        return countOfNodes != V;
    }

    private static ArrayList<ArrayList<Integer>> createAdjacencyList(int[][] edges, int V) {


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

    private static int[] createIndegreeArray(ArrayList<ArrayList<Integer>> adjacencyList, int V) {

        int[] indegree = new int[V];

        for (int v = 0; v < V; v++) {
            for (int neighbor : adjacencyList.get(v)) {
                indegree[neighbor]++;
            }
        }

        return indegree;
    }
}
