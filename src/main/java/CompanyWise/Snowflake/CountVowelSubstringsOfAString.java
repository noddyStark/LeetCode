package CompanyWise.Snowflake;


import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/*
2062. Count Vowel Substrings of a String
A substring is a contiguous (non-empty) sequence of characters within a string.
A vowel substring is a substring that only consists of vowels ('a', 'e', 'i', 'o', and 'u') and has all five vowels present in it.
Given a string word, return the number of vowel substrings in word.

Example 1:
Input: word = "aeiouu"
Output: 2
Explanation: The vowel substrings of word are as follows (underlined):
- "aeiouu"
- "aeiouu"

Example 2:
Input: word = "unicornarihan"
Output: 0
Explanation: Not all 5 vowels are present, so there are no vowel substrings.
Example 3:

Input: word = "cuaieuouac"
Output: 7
Explanation: The vowel substrings of word are as follows (underlined):
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"
- "cuaieuouac"

Constraints:
1 <= word.length <= 100
word consists of lowercase English letters only.
*/
public class CountVowelSubstringsOfAString {

    static void main() {

        String word = "cuaieuouac";

        int result = countVowelSubstrings(word);

        System.out.println("Result = " + result);
    }

    private static int countVowelSubstrings(String word) {

        int count = 0;
        int vowelStart = 0;

        HashMap<Character, Integer> lastSeen = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (!isVowel(ch)) {
                lastSeen.clear();
                vowelStart = i + 1;
                continue;
            }

            // Store latest index of this vowel
            lastSeen.put(ch, i);

            // c u a i e u o u a c
            // 0 1 2 3 4 5 6 7 8 9
            // Map = {a=2, u=7, e=4, i=3, o=6}

            if (lastSeen.size() == 5) {
                int minLastSeen = Collections.min(lastSeen.values());
                System.out.println("Map = " + lastSeen);
                System.out.println("vowelStart = " + vowelStart);
                System.out.println("minLastSeen = " + minLastSeen);

                /*
                 * For every index i, we are asking:
                 * How many valid vowel substrings can end at this current index?
                 * That number is:
                 * minLastSeen - vowelStart + 1
                 *
                 * Add all substrings ending at current index that start from vowelStart up to minLastSeen.
                 */
                count = count + minLastSeen - vowelStart + 1;
            }
        }

        return count;
    }

    private static boolean isVowel(char ch) {

        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

}
