package org.leetcode.Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFSOfGraph {
    static void main() {
        int vertices = 5;

        // Create the adjacency list
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        // Initialize the list
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        addEdge(adjacencyList, 0, 2);
        addEdge(adjacencyList, 0, 3);
        addEdge(adjacencyList, 0, 1);
        addEdge(adjacencyList, 2, 4);

        System.out.println("AdjacencyList = " + adjacencyList);

        boolean[] visited = new boolean[vertices];

        ArrayList<Integer> result = new ArrayList<>();
        bfs(adjacencyList, 0, visited, result);

        System.out.println("Result = " + result);

    }

    private static void bfs(ArrayList<ArrayList<Integer>> adjacencyList, int u,
                            boolean[] visited, ArrayList<Integer> result) {

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(u);
        visited[u] = true;
        result.add(u);

        while (!queue.isEmpty()) {
            ArrayList<Integer> neighbours = adjacencyList.get(queue.poll());

            for (int i = 0; i < neighbours.size(); i++) {
                int neighbour = neighbours.get(i);

                if(!visited[neighbour]) {
                    queue.offer(neighbour);
                    visited[neighbour] = true;
                    result.add(neighbour);
                }
            }
        }

    }

    private static void addEdge(ArrayList<ArrayList<Integer>> adjacencyList, int u, int v) {
        adjacencyList.get(u).add(v);
        adjacencyList.get(v).add(u);
    }
}
