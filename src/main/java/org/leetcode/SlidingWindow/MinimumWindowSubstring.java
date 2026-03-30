package org.leetcode.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. Minimum Window Substring
 * <p>
 * Using Sliding Window Technique
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        String original = "ADOBECODEBANC";
        String target = "ABC";

        System.out.println("Minimum Substring included in first = " + minimumWindow(original, target));
    }

    private static String minimumWindow(String original, String target) {

        if (original.length() < target.length()) {
            return "";
        }

        Map<Character, Integer> targetMap = buildFrequencyMap(target);
        int minLength = Integer.MAX_VALUE;
        int left = 0;
        String answer = "";
        Map<Character, Integer> windowMap = new HashMap<>();

        for (int right = 0; right < original.length(); right++) {
            char ch = original.charAt(right);
            windowMap.put(ch, windowMap.getOrDefault(ch, 0) + 1);

            while (containsAllChars(windowMap, targetMap)) {
                int len = right - left + 1;
                if (len < minLength) {
                    minLength = len;
                    answer = original.substring(left, right + 1);
                }
                windowMap.put(original.charAt(left), windowMap.getOrDefault(original.charAt(left), 0) - 1);
                left++;
            }
        }

        return answer;
    }

    private static boolean containsAllChars(Map<Character, Integer> window, Map<Character, Integer> target) {
        for (char ch : target.keySet()) {
            if (window.getOrDefault(ch, 0) < target.get(ch)) {
                return false;
            }
        }
        return true;
    }

    private static Map<Character, Integer> buildFrequencyMap(String target) {
        Map<Character, Integer> hmap = new HashMap<>();
        for (char ch : target.toCharArray()) {
            hmap.put(ch, hmap.getOrDefault(ch, 0) + 1);
        }
        return hmap;
    }
}
