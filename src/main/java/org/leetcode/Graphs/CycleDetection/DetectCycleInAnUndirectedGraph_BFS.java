package org.leetcode.Graphs.CycleDetection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DetectCycleInAnUndirectedGraph_BFS {

    static class Pair {
        int node;
        int parent;

        Pair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    static void main() {
        int vertices = 6;
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        addEdge(adjacencyList, 0, 1);
        addEdge(adjacencyList, 0, 2);
        addEdge(adjacencyList, 3, 4);
        addEdge(adjacencyList, 3, 5);
        addEdge(adjacencyList, 4, 5);

        boolean[] visited = new boolean[vertices];
        boolean result = isCycle(adjacencyList, visited, vertices);
        System.out.println("Has Cycle = " + result);
    }

    private static boolean isCycle(ArrayList<ArrayList<Integer>> adjacencyList, boolean[] visited, int vertices) {

        for (int i = 0; i < vertices; i++) {
            if (!visited[i] && checkBFSCycle(adjacencyList, visited, i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkBFSCycle(ArrayList<ArrayList<Integer>> adjacencyList, boolean[] visited, int startNode) {

        Queue<Pair> queue = new LinkedList<>();

        queue.offer(new Pair(startNode, -1));
        visited[startNode] = true;

        while (!queue.isEmpty()) {
            Pair currentPair = queue.poll();

            int node = currentPair.node;
            int parent = currentPair.parent;

            for (int neighbor : adjacencyList.get(node)) {

                // This edge is just the reverse direction of the edge I used to come here
                if (neighbor == parent) {
                    continue;
                }

                if (visited[neighbor]) {
                    return true;
                }

                visited[neighbor] = true;
                queue.offer(new Pair(neighbor, node));
            }
        }
        return false;
    }

    private static void addEdge(ArrayList<ArrayList<Integer>> adjacencyList, int u, int v) {
        adjacencyList.get(u).add(v);
        adjacencyList.get(v).add(u);
    }
}
