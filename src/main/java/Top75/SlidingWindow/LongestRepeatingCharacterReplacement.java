package Top75.SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
Write a function to find the length of the longest substring containing the same letter in a given string s, after performing at most k
operations in which you can choose any character of the string and change it to any other uppercase English letter.

Input:
s = "BBABCCDD"
k = 2
Output:
5

Explanation: Replace the first 'A' and 'C' with 'B' to form "BBBBBCDD". The longest substring with identical letters is "BBBBB", which has a length of 5.
*/
public class LongestRepeatingCharacterReplacement {

    static void main() {

        String input = "BBABCCDD";
        int k = 2;

        System.out.println(characterReplacement(input, k));
    }

    public static Integer characterReplacement(String s, Integer k) {

        HashMap<Character, Integer> charCount = new HashMap<>();
        int start = 0;
        int maxFreq = 0;
        int maxLength = 0;

        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end); // C
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1); // Map = {B -> 3, A -> 1, C -> 2}
            maxFreq = Math.max(maxFreq, charCount.get(ch)); // maxFreq = 3

            // B B A B C C
            // 6 - 3 > 2
            while ((end - start + 1) - maxFreq > k) {
                char leftChar = s.charAt(start);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                start++;
            }

            maxLength = Math.max(maxFreq, end - start + 1); // maxLength = 5
        }
        return maxLength;
    }

}
