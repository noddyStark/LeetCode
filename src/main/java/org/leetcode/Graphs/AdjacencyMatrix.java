package org.leetcode.Graphs;

/**
 * ✅ Adjacency Matrix Representation
 *
 * Complexity:
 * Space        → O(V^2) (uses full matrix even if edges are few)
 * Add Edge     → O(1)
 * Check Edge   → O(1)  (direct lookup matrix[u][v])
 * Traverse     → O(V^2)
 *
 * Best for: Dense graphs ✅ (many edges)
 * Not ideal for: Sparse graphs ❌ (wastes space)
 *
 * ⚠️ Indexing:
 * Uses 1-based indexing → matrix size = (V + 1) x (V + 1)
 * Row 0 and Column 0 are unused for simplicity
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
 * Total edges = 5 (undirected)
 */
public class AdjacencyMatrix {

    static void main() {

        int vertices = 5;

        int[][] matrix = new int[vertices + 1][vertices + 1];

        // Add edges
        addEdge(1, 2, matrix);
        addEdge(1, 3, matrix);
        addEdge(2, 4, matrix);
        addEdge(3, 4, matrix);
        addEdge(4, 5, matrix);

        // Print matrix
        printMatrix(matrix, vertices);
    }

    static void addEdge(int source, int target, int[][] matrix) {

        matrix[source][target] = 1;
        matrix[target][source] = 1;
    }

    // Print adjacency matrix
    static void printMatrix(int[][] matrix, int vertices) {
        System.out.println("Adjacency Matrix:");

        for (int i = 1; i <= vertices; i++) {
            for (int j = 1; j <= vertices; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

/*
 * Full Matrix (6 x 6 because of 1-based indexing):
 *
 *     0 1 2 3 4 5
 *   -------------
 * 0 | 0 0 0 0 0 0   ← unused
 * 1 | 0 0 1 1 0 0
 * 2 | 0 1 0 0 1 0
 * 3 | 0 1 0 0 1 0
 * 4 | 0 0 1 1 0 1
 * 5 | 0 0 0 0 1 0
 *
 * 👉 Only this submatrix is used (nodes 1 → 5):
 *
 *     1 2 3 4 5
 *   -----------
 * 1 | 0 1 1 0 0
 * 2 | 1 0 0 1 0
 * 3 | 1 0 0 1 0
 * 4 | 0 1 1 0 1
 * 5 | 0 0 0 1 0
 */
