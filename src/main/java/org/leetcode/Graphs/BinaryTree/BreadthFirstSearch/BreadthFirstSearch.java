package org.leetcode.Graphs;

import java.util.*;

/**
 * 1
 * / \
 * 2   3
 * \ /
 * 4
 * |
 * 5
 * <p>
 * Time: O(V + E)
 * Space: O(V) (queue + visited)
 * <p>
 * BFS uses a queue to explore nodes level by level and guarantees shortest path in unweighted graphs.
 *
 */
public class BreadthFirstSearch {

    static void main() {
        int vertices = 5;

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= vertices; i++) {
            graph.add(new ArrayList<>());
        }

        // add edges (undirected)
        addEdge(1, 2, graph);
        addEdge(1, 3, graph);
        addEdge(2, 4, graph);
        addEdge(3, 4, graph);
        addEdge(4, 5, graph);

        printGraph(graph);

        bfs(1, graph);
    }

    private static void bfs(int start, List<List<Integer>> graph) {

        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();
        int[] distance = new int[graph.size()];
        int[] predecessor = new int[graph.size()];

        Arrays.fill(predecessor, -1); // important

        // Step 1: Start node
        queue.offer(start);
        visited[start] = true;
        distance[start] = 0;
        predecessor[start] = -1;

        System.out.print("BFS Traversal: ");

        while (!queue.isEmpty()) {

            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbour : graph.get(node)) {
                if (!visited[neighbour]) {

                    queue.offer(neighbour);
                    visited[neighbour] = true;

                    // distance update
                    distance[neighbour] = distance[node] + 1;

                    // predecessor update
                    predecessor[neighbour] = node;
                }
            }
        }

        System.out.println("\n");

        // Print distances
        System.out.println("Distance from source " + start + ":");
        for (int i = 1; i < distance.length; i++) {
            System.out.println("Node " + i + " → " + distance[i]);
        }

        // Print Predecessor
        for (int i = 1; i < predecessor.length; i++) {
            System.out.println("Predecessor of " + i + " ← " + predecessor[i]);
        }

        System.out.println();

        // Print path to each node
        for (int i = 1; i < graph.size(); i++) {
            printPath(i, predecessor);
        }

    }

    // u = one vertex (node)
    // v = another vertex (node)
    private static void addEdge(int u, int v, List<List<Integer>> graph) {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    private static void printGraph(List<List<Integer>> graph) {

        for (int i = 1; i < graph.size(); i++) {
            System.out.print(i);
            System.out.print(" -> ");
            System.out.print(graph.get(i));
            System.out.println();
        }
    }

    private static void printPath(int target, int[] predecessor) {

        List<Integer> path = new ArrayList<>();
        int curr = target;

        while (curr != -1) {
            path.add(curr);
            curr = predecessor[curr];
        }

        Collections.reverse(path);

        System.out.println("Path to " + target + " → " + path);
    }
}
