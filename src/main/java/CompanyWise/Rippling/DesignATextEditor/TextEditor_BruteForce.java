package CompanyWise.Rippling.DesignATextEditor;

public class TextEditor_BruteForce {

    final StringBuilder left;
    final StringBuilder right;

    public TextEditor_BruteForce() {
        left = new StringBuilder();
        right = new StringBuilder();
    }

    public void addText(String text) {
        left.append(text);
    }

    /**
     * Deletes k characters to the left of the cursor. Returns the number of characters actually deleted.
     * */
    public int deleteText(int k) {
        int deletedChars = Math.min(k, left.length());

        left.delete(left.length() - deletedChars, left.length());

        return deletedChars;
    }

    /**
     * Moves the cursor to the left k times. Returns the last min(10, len) characters to the left of the cursor,
     * where len is the number of characters to the left of the cursor.
     * */
    public String cursorLeft(int k) {
        int move = Math.min(k, left.length());

        while (move > 0) {
            char ch = left.charAt(left.length() - 1);
            left.deleteCharAt(left.length() - 1);

            // moved char is now right of cursor
            right.append(ch);

            move--;
        }

        return getLast10Chars();
    }

    public String cursorRight(int k) {
        int move = Math.min(k, right.length());

        while (move > 0) {
            char ch = right.charAt(right.length() - 1);
            right.deleteCharAt(right.length() - 1);

            left.append(ch);

            move--;
        }

        return getLast10Chars();
    }

    private String getLast10Chars() {
        int start = Math.max(0, left.length() - 10);
        return left.substring(start);
    }
}