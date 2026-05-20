package org.leetcode;

public class IslandPerimeter {

    static void main() {


        int[][] grid = {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };

        System.out.println(islandPerimeter(grid));
    }

    private static int islandPerimeter(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    return dfs(grid, i, j, visited);
                }
            }
        }
        return 0;
    }
    private static int dfs(int[][] grid, int row, int col, boolean[][] visited) {

        int rows = grid.length;
        int cols = grid[0].length;

        // If we go outside grid, that side contributes 1 perimeter
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return 1;
        }

        // If we hit water, that side contributes 1 perimeter
        if (grid[row][col] == 0) {
            return 1;
        }

        // If already visited land, do not count again
        if (visited[row][col]) {
            return 0;
        }

        visited[row][col] = true;

        int right = dfs(grid, row, col + 1, visited);
        int left = dfs(grid, row, col - 1, visited);
        int up = dfs(grid, row - 1, col, visited);
        int down = dfs(grid, row + 1, col, visited);

        return right + left + up + down;
    }

}
