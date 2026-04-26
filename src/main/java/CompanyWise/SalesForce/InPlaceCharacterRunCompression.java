package CompanyWise.SalesForce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * In-Place Character Run Compression
 * Given a list chars where each element is a one-character string, compress consecutive identical
 * characters in place. For each maximal run, write the character once, followed by the run length
 * only if the length is greater than 1. The count must be written as separate digit characters.
 * Return the new length of the compressed list.
 * For example, ['a','a','a','b','b','c'] becomes ['a','3','b','2','c'], and the function returns 5.
 */
public class InPlaceCharacterRunCompression {

    static void main() {
        ArrayList<Character> listOfCharacters = new ArrayList<>(Arrays.asList('a', 'a', 'a', 'b', 'b', 'c'));
        int result = inPlaceCharacterRunCompression(listOfCharacters);
        System.out.println("Result = " + result);
    }

    private static int inPlaceCharacterRunCompression(List<Character> chars) {
        int read = 0;
        int write = 0;

        while (read < chars.size()) {
            char currentChar = chars.get(read);
            int count = 0;

            // Count same consecutive characters
            while (read < chars.size() && chars.get(read) == currentChar) {
                read++;
                count++;
            }

            // Write the character
            chars.set(write, currentChar);
            write++;

            // Write count only if count > 1
            if (count > 1) {
                String countStr = String.valueOf(count);

                for (char digit : countStr.toCharArray()) {
                    chars.set(write, digit);
                    write++;
                }
            }
        }

        return write;
    }
}
