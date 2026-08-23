package CompanyWise.Google;

/*
You are given a 2D matrix where each cell contains a number representing a height.
From any cell, you can jump to an adjacent cell in one of four directions:
up, down, left, right

You can start from any cell in the matrix.

Jump rules
Suppose your path is:

A -> B -> C
For the first jump:
B height <= A height
For the next jump from B to C, the jump is valid if:
C height <= B height
OR
C height <= A height

So in general, when moving to the next cell, the next cell must be less than or equal to either:
current cell height
OR
previous cell height

Also, every cell can be visited at most once in a single path.
The goal is to return the length of the longest valid jumping path starting from any point in the matrix.

Input =
    {8, 7, 8},
    {5, 2, 4},
    {3, 1, 3}

Expected output: 9 (8 -> 7 -> 8 -> 5 -> 2 -> 4 -> 3 -> 1 -> 3)
* */

public class LongestValidJumpingPathInAMatrix {

    private static final int[][] DIRECTIONS = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    public int longestValidJumpingPathInAMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxLength = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                boolean[][] visited = new boolean[rows][cols];
                int startingHeight = matrix[row][col];
                int length = dfs(matrix, visited, row, col, startingHeight, startingHeight, true);
                maxLength = Math.max(maxLength, length);
            }
        }

        return maxLength;
    }

    private int dfs(int[][] matrix, boolean[][] visited, int row, int col, int previousHeight, int previousPreviousHeight, boolean isStartingCell) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col]) {
            return 0;
        }

        int currentHeight = matrix[row][col];

        // Validate the jump that brought us to the current cell.
        if (!isStartingCell && currentHeight > previousHeight && currentHeight > previousPreviousHeight) {
            return 0;
        }

        visited[row][col] = true;

        int longestPath = 1;

        for (int[] direction : DIRECTIONS) {
            int nextRow = row + direction[0];
            int nextCol = col + direction[1];

            // Current becomes previous
            // Previous becomes previous-previous
            int candidateLength = 1 + dfs(matrix, visited, nextRow, nextCol, currentHeight, previousHeight, false);

            longestPath = Math.max(longestPath, candidateLength);
        }

        // Backtrack.
        visited[row][col] = false;

        return longestPath;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {8, 7, 8},
                {5, 2, 4},
                {3, 1, 3}
        };

        LongestValidJumpingPathInAMatrix solver = new LongestValidJumpingPathInAMatrix();
        System.out.println(solver.longestValidJumpingPathInAMatrix(matrix));
    }
}