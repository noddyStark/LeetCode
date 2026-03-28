package org.leetcode.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring without duplicate characters.
 * <p>
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
 * <p>
 * Example 2:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 */
public class LongestSubstringWithoutRepeating {

    public static void main(String[] args) {
        String s = " ";
        System.out.println("Length of longest substring without repeating characters = " + lengthOfLongestSubstring(s));
    }

    private static int lengthOfLongestSubstring(String s) {

        int windowStart = 0;
        int maxLength = 0;

        Map<Character, Integer> uniqueChar = new HashMap<>();

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char rightchar = s.charAt(windowEnd);
            if (uniqueChar.containsKey(rightchar)) {
                int newWindowStart = uniqueChar.get(rightchar);
                windowStart = Math.max(windowStart, newWindowStart);
            }

            uniqueChar.put(rightchar, windowEnd + 1);
            if (windowEnd - windowStart + 1 > maxLength) {
                maxLength = windowEnd - windowStart + 1;
            }
        }
        return maxLength;
    }
}
