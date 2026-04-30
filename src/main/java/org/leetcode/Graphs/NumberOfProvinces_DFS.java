package org.leetcode.Graphs;


import java.util.ArrayList;

/*
* 547. Number of Provinces
*
There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b,
and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.
You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

Example 1:
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2


Example 2:
Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3

Constraints:

1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
* */
public class NumberOfProvinces_DFS {
    static void main() {
        int[][] isConnected = {
                {1, 1, 0}, // City 1
                {1, 1, 0}, // City 2
                {0, 0, 1}  // City 3
        };

        ArrayList<ArrayList<Integer>> adjacencyList = createAdjacencyList(isConnected);
        System.out.println("adjacencyList = " + adjacencyList);
        /*
         * [
         *   [],
         *   [2], // City 1, 1 → 2
         *   [1], // City 2  2 → 1
         *   []   // City 3
         * ]
         * */

        boolean[] visited = new boolean[isConnected.length + 1];
        int numberOfProvinces = 0;

        for (int i = 1; i <= isConnected.length; i++) {
            if (!visited[i]) {
                dfs(adjacencyList, visited, i);
                numberOfProvinces++;
            }
        }

        System.out.println("Number of Provinces  = " + numberOfProvinces);
    }

    private static void dfs(ArrayList<ArrayList<Integer>> adjacencyList, boolean[] visited, int u) {

        // Time Complexity :  Node visits = O(n)
        visited[u] = true;

        // Iterate only over actual edges, Worst case (fully connected graph), Each node connects to (n-1) nodes
        // Total edges ≈ n²
        // DFS = O(nodes + edges) = O(n + n²) = O(n²)
        for (int nextCity : adjacencyList.get(u)) {
            if (!visited[nextCity]) {
                dfs(adjacencyList, visited, nextCity);
            }
        }

    }

    // Time Complexity = > Adjacency List Creation = O(n²)
    // Each node stores ~n neighbors
    // Total entries ≈ n²
    // Space Complexity => Adjacency List = O(n²)
    private static ArrayList<ArrayList<Integer>> createAdjacencyList(int[][] isConnected) {
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
        int size = isConnected.length;

        for (int i = 0; i <= size; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    if (isConnected[i][j] == 1) {
                        // cities are connected
                        adjacencyList.get(i + 1).add(j + 1);
                    }
                }
            }
        }

        return adjacencyList;
    }
}
