package org.leetcode.Graphs;

import java.util.ArrayList;

/**
 * ✅ Adjacency List Representation
 *
 * Complexity:
 * Space        → O(V + E) ✅
 * Add edge     → O(1)
 * Traverse     → O(V + E)
 *
 * Best for: Sparse graphs ✅
 * Not ideal for: Dense graphs (too many edges → large lists)
 *
 * Graph Structure:
 *
 *        1
 *       / \
 *      2   3
 *       \ /
 *        4
 *        |
 *        5
 *
 * Total vertices = 5
 * Total edges = 5
 *
 * Adjacency list is preferred in real-world systems because most graphs are sparse, so it saves space and improves traversal performance.
 */
public class AdjacencyList {


    static void main() {

        int vertices = 5;

        // Step 1: Create list of lists
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        // Step 2: Initialize empty lists for each vertex (1-based)
        for (int i = 0; i <= vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // Step 3: Add edges
        addEdge(1, 2, adjacencyList);
        addEdge(1, 3, adjacencyList);
        addEdge(2, 4, adjacencyList);
        addEdge(3, 4, adjacencyList);
        addEdge(4, 5, adjacencyList);

        // Step 4: Print
        printGraph(adjacencyList, vertices);

    }

    /**
     * Adds an undirected edge between source and target
     */
    private static void addEdge(int source, int target, ArrayList<ArrayList<Integer>> adjacencyList) {
        adjacencyList.get(source).add(target);
        adjacencyList.get(target).add(source);
    }

    private static void printGraph(ArrayList<ArrayList<Integer>> adjacencyList, int vertices) {
        for (int i = 1; i <= vertices; i++) {
            System.out.print(i + " -> ");
            for (int neighbor : adjacencyList.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
}
