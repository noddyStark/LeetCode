package CompanyWise.Snowflake;

public class MaxAreaOfIsland {

    static void main() {

        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };

        System.out.println(maxAreaOfIsland(grid));
    }

    private static int maxAreaOfIsland(int[][] grid) {

        int rows = grid.length;
        int columns = grid[0].length;
        int maxArea = 0;

        boolean[][] visited = new boolean[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area =  dfs(i, j, grid, visited);
                    maxArea = Math.max(area, maxArea);
                }
            }
        }

        return maxArea;
    }

    private static int dfs(int row, int column, int[][] grid, boolean[][] visited) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (row < 0 || row >= rows || column < 0 || column >= cols) {
            return 0;
        }

        if (grid[row][column] == 0) {
            return 0;
        }

        if (visited[row][column]) {
            return 0;
        }

        visited[row][column] = true;

        int area = 1;

        area += dfs(row + 1, column, grid, visited); // down
        area += dfs(row, column + 1, grid, visited); // right
        area += dfs(row - 1, column, grid, visited); // up
        area += dfs(row, column - 1, grid, visited); // left

        return area;
    }
}
