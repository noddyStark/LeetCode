package CompanyWise.Rippling.DesignATextEditor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class TextEditor {

    private Deque<Character> left;
    private Deque<Character> right;

    public TextEditor() {
        left = new ArrayDeque<>();
        right = new ArrayDeque<>();
    }

    public void addText(String text) {
        for (char c : text.toCharArray()) {
            left.offerLast(c);
        }
    }

    public int deleteText(int k) {

        int deleteChars = Math.min(k, left.size());

        for (int i = 0; i < deleteChars; i++) {
            left.pollLast();
        }

        return deleteChars;
    }

    /**
     * Moves the cursor to the left k times. Returns the last min(10, len) characters to the left of the cursor,
     * where len is the number of characters to the left of the cursor.
     *
     */
    public String cursorLeft(int k) {
        int move = Math.min(k, left.size());

        while (!left.isEmpty() && move != 0) {
            char ch = left.pollLast();
            right.offerFirst(ch);
            move--;
        }

        return getLast10Chars();
    }

    /**
     * Moves the cursor to the right k times. Returns the last min(10, len) characters to the left of the cursor,
     * where len is the number of characters to the left of the cursor.
     *
     */
    public String cursorRight(int k) {
        int move = Math.min(k, right.size());

        while (!right.isEmpty() && move != 0) {
            char ch = right.pollFirst();
            left.offerLast(ch);
            move--;
        }

        return getLast10Chars();
    }

    private String getLast10Chars() {
        StringBuilder result = new StringBuilder();

        // Returns an iterator over the elements in this deque in reverse sequential order.
        // The elements will be returned in order from last (tail) to first (head).
        // Returns: an iterator over the elements in this deque in reverse sequence
        Iterator<Character> iterator = left.descendingIterator();

        int count = 0;

        while (iterator.hasNext() && count < 10) {
            result.append(iterator.next());
            count++;
        }

        return result.reverse().toString();
    }
}
