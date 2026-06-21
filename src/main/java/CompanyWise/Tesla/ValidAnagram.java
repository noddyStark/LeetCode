package CompanyWise.Tesla;

/*
Given two strings s and t, return true if t is an anagram of s, and false otherwise.
An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, using all the original letters exactly once.

Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false
*/
public class ValidAnagram {

    static void main() {

        String s = "anagram";
        String t = "nagaram";
        String s1 = "cat";
        String t1 = "rat";

        System.out.println(isAnagram(s1, t1));
    }

    private static boolean isAnagram(String s, String t) {

        // anagram
        /*
         * a -> 3
         * n -> 1
         * g -> 1
         * r -> 1
         * m -> 1
         * */

        // nagaram
        /*
         * n -> 1
         * a -> 3
         * g -> 1
         * r -> 1
         * m -> .
         * */

        int[] charS = new int[26];
        int[] charT = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - 'a';
            charS[val] += 1;
        }

        for (int i = 0; i < t.length(); i++) {
            int val = t.charAt(i) - 'a';
            charT[val] += 1;
        }

        for (int i = 0; i < charS.length; i++) {
            if (charS[i] != charT[i]) {
                return false;
            }
        }

        return true;
    }
}
