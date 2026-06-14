package Top75.SlidingWindow;


import java.util.HashMap;

/*
Write a function to return the length of the longest substring in a provided string s where all characters in the substring are distinct.

Example 1: Input:
s = "eghghhgg"
Output:
3
The longest substring without repeating characters is "egh" with length of 3.

Example 2: Input:
s = "substring"
Output:
8
The answer is "ubstring" with length of 8.
*/
public class LongestSubstringWithoutRepeatingCharacters {

    static void main() {
        String s = "aabbcdef";

        // a a b b c c d d e f g

        System.out.println(longestSubstringWithoutRepeat(s));
    }

    public static Integer longestSubstringWithoutRepeat(String s) {

        /*
        s -> 1
        u -> 1
        b -> 1
        s -> 1
        * */

        int left = 0;
        int longestSubstring = 0;

        HashMap<Character, Integer> freqMap = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
            System.out.println("Map = " + freqMap);

            while (freqMap.get(ch) > 1) {
                char starChar = s.charAt(left);
                freqMap.put(starChar, freqMap.get(starChar) - 1);
                left++;
            }
            System.out.println("Updated Map = " + freqMap);

            longestSubstring = Math.max(longestSubstring, right - left + 1);

        }

        return longestSubstring;
    }

}
