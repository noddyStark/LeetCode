package NeetCode;

import java.util.Arrays;

/*
You are given a m × n
m×n 2D grid initialized with these three possible values:

-1 - A water cell that can not be traversed.
0 - A treasure chest.
INF - A land cell that can be traversed. We use the integer 2^31 - 1 = 2147483647 to represent INF.
Fill each land cell with the distance to its nearest treasure chest. If a land cell cannot reach a treasure chest then the value should remain INF.
Assume the grid can only be traversed up, down, left, or right.

Modify the grid in-place.

Example 1:
Input: [
  [2147483647,-1,0,2147483647],
  [2147483647,2147483647,2147483647,-1],
  [2147483647,-1,2147483647,-1],
  [0,-1,2147483647,2147483647]
]

Output: [
  [3,-1,0,1],
  [2,2,1,-1],
  [1,-1,2,-1],
  [0,-1,3,4]
]
*/
public class IslandsAndTreasure_DFS {

    static void main() {
        int[][] grid = {
                {2147483647, -1, 0, 2147483647},
                {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1},
                {0, -1, 2147483647, 2147483647}
        };

        int rows = grid.length;
        int cols = grid[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 0) {
                    dfs(grid, row, col, 0);
                }
            }
        }

        System.out.println(Arrays.deepToString(grid));
    }

    /*
Input:
        [2147483647,    -1,                 0,              2147483647],
        [2147483647,    2147483647,         2147483647,         -1],
        [2147483647,    -1,                 2147483647,         -1],
        [0,             -1,                 2147483647,         2147483647]


Output:
        [
            [3,     -1,     0,       1],
            [2,      2,     1,      -1],
            [1,     -1,     2,      -1],
            [0,     -1,     3,       4]
         ]

    R D L U
*/

    public static void dfs(int[][] grid, int row, int col, int distance) {

        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            return;
        }

        // water can not be traversed
        if (grid[row][col] == -1) {
            return;
        }

        if (grid[row][col] < distance) {
            return;
        }

        // Store the shorter distance.
        grid[row][col] = distance;

        dfs(grid, row, col + 1, distance + 1);
        dfs(grid, row, col - 1, distance + 1);
        dfs(grid, row + 1, col, distance + 1);
        dfs(grid, row - 1, col, distance + 1);

    }
}
