package org.leetcode;


/*
329. Longest Increasing Path in a Matrix
Given an m x n integers matrix, return the length of the longest increasing path in matrix.

From each cell, you can either move in four directions: left, right, up, or down.
You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
* */
public class LongestIncreasingPathInAMatrix {

    static int[][] dirs = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1}
    };


    static void main() {

        int[][] matrix = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };

        int result = longestIncreasingPath(matrix);

        System.out.println(result);
    }

    public static int longestIncreasingPath(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int maxLength = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                boolean[][] visited = new boolean[rows][cols];
                int length = dfs(matrix, visited, row, col, matrix[row][col], true);
                maxLength = Math.max(maxLength, length);
            }
        }
        return maxLength;
    }

    public static int dfs(int[][] matrix, boolean[][] visited, int row, int col, int previous, boolean isStarting) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col]) {
            return 0;
        }

        int current = matrix[row][col];

        if (!isStarting && current <= previous) {
            return 0;
        }

        visited[row][col] = true;

        int currentLength = 1;

        for (int[] dir : dirs) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];

            int candidateLength = 1 + dfs(matrix, visited, nextRow, nextCol, current, false);
            currentLength = Math.max(currentLength, candidateLength);
        }

        visited[row][col] = false;
        return currentLength;
    }
}
