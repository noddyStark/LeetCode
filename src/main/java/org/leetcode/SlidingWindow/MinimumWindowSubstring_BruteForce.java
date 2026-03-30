package org.leetcode.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. Minimum Window Substring
 */
public class MinimumWindowSubstring_BruteForce {

    public static void main(String[] args) {
        String first = "AA";
        String second = "ABC";

        System.out.println("Minimum Substring included in first = " + minWindow(first, second));
    }

    private static String minWindow(String first, String second) {

        if (first.length() < second.length()) {
            return "";
        }

        Map<Character, Integer> target = buildFrequencyMap(second);
        int minLength = Integer.MAX_VALUE;
        String answer = "";

        for (int i = 0; i < first.length(); i++) {
            Map<Character, Integer> window = new HashMap<>();

            for (int j = i; j < first.length(); j++) {
                char ch = first.charAt(j);
                window.put(ch, window.getOrDefault(ch, 0) + 1);

                if (containsAll(window, target)) {
                    int len = j - i + 1;
                    if (len < minLength) {
                        minLength = len;
                        answer = first.substring(i, j + 1);
                    }
                    break;
                }
            }
        }

        return answer;
    }

    private static boolean containsAll(Map<Character, Integer> window, Map<Character, Integer> target) {
        for (char ch : target.keySet()) {
            if (window.getOrDefault(ch, 0) < target.get(ch)) {
                return false;
            }
        }
        return true;
    }

    private static Map<Character, Integer> buildFrequencyMap(String second) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : second.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        return map;
    }
}
