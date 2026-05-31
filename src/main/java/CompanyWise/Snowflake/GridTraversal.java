package CompanyWise.Snowflake;

import java.util.*;

public class GridTraversal {

    static class State {
        int row;
        int col;

        // forcedDir tells us what direction we MUST go next.
        // -1 means we are free to go in any direction.
        // 0 = right, 1 = left, 2 = down, 3 = up
        int forcedDir;

        int jumps;

        State(int row, int col, int forcedDir, int jumps) {
            this.row = row;
            this.col = col;
            this.forcedDir = forcedDir;
            this.jumps = jumps;
        }
    }

    static int getMinJumps(String[] grid) {

        int rows = grid.length;
        int cols = grid[0].length();

        int startRow = -1;
        int startCol = -1;

        // Find the starting cell 'S'
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i].charAt(j) == 'S') {
                    startRow = i;
                    startCol = j;
                }
            }
        }

        return bfs(grid, startRow, startCol);
    }

    private static int bfs(String[] grid, int startRow, int startCol) {

        int rows = grid.length;
        int cols = grid[0].length();

        /*
         * Directions:
         * 0 = right
         * 1 = left
         * 2 = down
         * 3 = up
         */
        int[][] dirs = {
                {0, 1},    // right
                {0, -1},   // left
                {1, 0},    // down
                {-1, 0}    // up
        };

        /*
         * visited[row][col][dirState]
         *
         * dirState meaning:
         * 0 means forcedDir = -1, can move in any direction
         * 1 means forcedDir = 0, must move right
         * 2 means forcedDir = 1, must move left
         * 3 means forcedDir = 2, must move down
         * 4 means forcedDir = 3, must move up
         */
        boolean[][][] visited = new boolean[rows][cols][5];

        Queue<State> queue = new LinkedList<>();

        // At start, there is no previous jump,
        // so we are free to move in any direction.
        queue.offer(new State(startRow, startCol, -1, 0));
        visited[startRow][startCol][0] = true;

        while (!queue.isEmpty()) {

            State current = queue.poll();

            int row = current.row;
            int col = current.col;
            int forcedDir = current.forcedDir;
            int jumps = current.jumps;

            // If we reached destination, return jumps so far.
            // Since this is BFS, this is the minimum jumps.
            if (grid[row].charAt(col) == 'E') {
                return jumps;
            }

            /*
             * Try all 4 directions.
             *
             * But if previous jump was length > 1,
             * then forcedDir will not be -1.
             * In that case, we can only continue in the same direction.
             */
            for (int d = 0; d < 4; d++) {

                // If we are forced to continue in one direction,
                // skip all other directions.
                if (forcedDir != -1 && d != forcedDir) {
                    continue;
                }

                /*
                 * Try every possible jump length.
                 *
                 * jumpLength = 1, 2, 3, ...
                 *
                 * We can jump over blocked cells '#'.
                 * Only the landing cell cannot be '#'.
                 */
                int maxJump = Math.max(rows, cols);

                for (int jumpLength = 1; jumpLength <= maxJump; jumpLength++) {

                    int newRow = row + dirs[d][0] * jumpLength;
                    int newCol = col + dirs[d][1] * jumpLength;

                    // If we go outside the grid, stop trying bigger jumps
                    // in this direction.
                    if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                        break;
                    }

                    // We can jump over '#', but we cannot land on '#'.
                    if (grid[newRow].charAt(newCol) == '#') {
                        continue;
                    }

                    /*
                     * If current jump length is greater than 1,
                     * then next jump must continue in the same direction.
                     *
                     * If current jump length is 1,
                     * then next jump can be in any direction.
                     */
                    int nextForcedDir;

                    if (jumpLength > 1) {
                        nextForcedDir = d;
                    } else {
                        nextForcedDir = -1;
                    }

                    int visitedIndex = nextForcedDir + 1;

                    if (!visited[newRow][newCol][visitedIndex]) {
                        visited[newRow][newCol][visitedIndex] = true;
                        queue.offer(new State(newRow, newCol, nextForcedDir, jumps + 1));
                    }
                }
            }
        }

        // If BFS finishes and we never reach E, answer is -1.
        return -1;
    }

    public static void main(String[] args) {

        String[] grid = {
                "S****#",
                "**#***",
                "*****#",
                "*#**#*",
                "#****E"
        };

        int result = getMinJumps(grid);

        System.out.println(result);
    }
}