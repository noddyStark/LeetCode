package CompanyWise.Google;

/*
1368. Minimum Cost to Make at Least One Valid Path in a Grid
Given an m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if
you are currently in this cell. The sign of grid[i][j] can be:

1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
Notice that there could be some signs on the cells of the grid that point outside the grid.

You will initially start at the upper left cell (0, 0). A valid path in the grid is a path that starts
from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid.
The valid path does not have to be the shortest.

You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.

Return the minimum cost to make the grid have at least one valid path.
* */
public class MinimumCostToMakeAtLeastOneValidPathInAGrid {

    static int[][] dirs = {
            {0, 1}, // R
            {0, -1}, // L
            {1, 0}, // D
            {-1, 0} // U
    };

    static int minimumCost = Integer.MAX_VALUE;

    static void main() {
        int[][] grid = {
                {1, 1, 1, 1},
                {2, 2, 2, 2},
                {1, 1, 1, 1},
                {2, 2, 2, 2}
        };

        System.out.println(minCost(grid));
    }

    public static int minCost(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        boolean[][] visited = new boolean[rows][cols];
        backtrack(grid, 0, 0, visited, 0);
        return minimumCost;
    }

    public static void backtrack(int[][] grid, int row, int col, boolean[][] visited, int cost) {

        int rows = grid.length;
        int cols = grid[0].length;

        if (row == rows - 1 && col == cols - 1) {
            minimumCost = Math.min(minimumCost, cost);
            return;
        }

        if (cost >= minimumCost) {
            return;
        }

        visited[row][col] = true;

        for (int dir = 0; dir < 4; dir++) {
            int newRow = row + dirs[dir][0];
            int newCol = col + dirs[dir][1];

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || visited[newRow][newCol]) {
                continue;
            }

            int newCost = grid[newRow][newCol] == dir + 1 ? 0 : 1;
            backtrack(grid, newRow, newCol, visited, cost + newCost);
        }

        visited[row][col] = false;
    }
}
