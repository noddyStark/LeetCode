package org.leetcode.Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class NumberOfProvinces_BFS {
    static int numberOfProvinces = 0;
    static int numberOfProvinces_using_matrix = 0;

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


        for (int i = 1; i <= isConnected.length; i++) {
            bfs(adjacencyList, visited, i);
        }

        for (int i = 1; i <= isConnected.length; i++) {
            bfs_no_adjacencyList(isConnected, visited, i);
        }

        System.out.println("Number of Provinces  = " + numberOfProvinces);
        System.out.println("numberOfProvinces_using_matrix  = " + numberOfProvinces_using_matrix);
    }

    private static void bfs(ArrayList<ArrayList<Integer>> adjacencyList, boolean[] visited, int city) {

        if (visited[city]) {
            return;
        }

        Queue<Integer> cityQueue = new LinkedList<>();
        cityQueue.offer(city);
        visited[city] = true;

        System.out.println("cityQueue = " + cityQueue);

        while (!cityQueue.isEmpty()) {
            ArrayList<Integer> nextCities = adjacencyList.get(cityQueue.poll());
            System.out.println("nextCities = " + nextCities);

            for (int nextCity : nextCities) {
                System.out.println("nextCity = " + nextCity);
                if (!visited[nextCity]) {
                    System.out.println("nextCity is not visited yet = " + nextCity);
                    cityQueue.offer(nextCity);
                    visited[nextCity] = true;
                }
            }
        }
        System.out.println("visited = " + Arrays.toString(visited));
        numberOfProvinces++;
    }

    private static void bfs_no_adjacencyList(int[][] isConnected, boolean[] visited, int city) {

        Queue<Integer> cityQueue = new LinkedList<>();
        cityQueue.offer(city);
        visited[city] = true;

        while (!cityQueue.isEmpty()) {
            int currentCity = cityQueue.poll();

            for (int nextCity = 1; nextCity <= isConnected.length; nextCity++) {
                if (!visited[nextCity] && isConnected[currentCity - 1][nextCity - 1] == 1) {
                    cityQueue.offer(nextCity);
                    visited[nextCity] = true;
                }
            }
        }
        numberOfProvinces_using_matrix++;
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

/*
*
adjacencyList = [[], [2], [1], []]
cityQueue = [1]
nextCities = [2]
nextCity = 2
nextCity is not visited yet = 2
nextCities = [1]
nextCity = 1
visited = [false, true, true, false]
cityQueue = [3]
nextCities = []
visited = [false, true, true, true]
Number of Provinces  = 2
numberOfProvinces_using_matrix  = 3
* */
