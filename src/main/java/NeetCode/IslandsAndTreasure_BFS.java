package NeetCode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class IslandsAndTreasure_BFS {


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

*/
    static void main() {
        int[][] grid = {
                {2147483647, -1, 0, 2147483647},
                {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1},
                {0, -1, 2147483647, 2147483647}
        };

        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 0) {
                    queue.offer(new int[]{row, col});
                }
            }
        }

        // queue = [ [0,2], [3,0] ]

        int[][] dirs = {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1}
        };

        //Time:  O(m × n)
        //Space: O(m × n)
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            // current = [0,2]
            // queue = [ [3,0] ]

            int row = current[0]; // row = 0
            int col = current[1]; // col = 1

            System.out.println("current row = " + row + " current col = " + col);


            for (int[] dir : dirs) {
                int newRow = row + dir[0]; // newRow =
                int newCol = col + dir[1];

                System.out.println("newRow = " + newRow + " newCol = " + newCol);

                if (newRow < 0 || newCol < 0 || newRow >= grid.length || newCol >= grid[0].length) {
                    continue;
                }

                if (grid[newRow][newCol] != Integer.MAX_VALUE) {
                    continue;
                }

                grid[newRow][newCol] = grid[row][col] + 1;
                queue.offer(new int[]{newRow, newCol});
            }
        }

        System.out.println(Arrays.deepToString(grid));
    }
}
