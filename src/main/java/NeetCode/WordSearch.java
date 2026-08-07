package NeetCode;


/*
Word Search
Given a 2-D grid of characters board and a string word, return true if the word is present in the grid,
otherwise return false.

For the word to be present it must be possible to form it with a path in the board with horizontally or
vertically neighboring cells. The same cell may not be used more than once in a word.

Example 1:
Input:
board = [
  ["A","B","C","D"],
  ["S","A","A","T"],
  ["A","C","A","E"]
],
word = "CAT"

Output: true

Example 2:
Input:
board = [
  ["A","B","C","D"],
  ["S","A","A","T"],
  ["A","C","A","E"]
],
word = "BAT"

Output: false
* */
public class WordSearch {

    static void main() {
        char[][] board = {
                {'A', 'B', 'C', 'D' },
                {'S', 'A', 'A', 'T' },
                {'A', 'C', 'A', 'E' }
        };

        String word = "CAT";
        System.out.println(exist(board, word));
    }

    public static boolean exist(char[][] board, String word) {

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (dfs(board, word, visited, r, c, 0))
                    return true;
            }
        }

        return false;
    }

    public static boolean dfs(char[][] board, String word, boolean[][] visited, int r, int c, int index) {

        if (index == word.length()) {
            return true;
        }

        if (r >= board.length || c >= board[0].length || r < 0 || c < 0 || visited[r][c]) {
            return false;
        }

        if (board[r][c] != word.charAt(index)) {
            return false;
        }

        visited[r][c] = true;
        index++;

        boolean found = dfs(board, word, visited, r + 1, c, index) ||
                dfs(board, word, visited, r - 1, c, index) ||
                dfs(board, word, visited, r, c + 1, index) ||
                dfs(board, word, visited, r, c - 1, index);

        visited[r][c] = false;
        return found;
    }
}
