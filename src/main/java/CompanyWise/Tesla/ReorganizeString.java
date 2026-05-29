package CompanyWise.Tesla;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
767. Reorganize String
Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

Return any possible rearrangement of s or return "" if not possible.

Example 1:
Input: s = "aab"
Output: "aba"

Example 2:
Input: s = "aaab"
Output: ""

* */
public class ReorganizeString {

    static class Pair {
        char ch;
        int freq;

        public Pair(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }

    static void main() {
        String s = "abbabbaaab";

        String result = reorganizeString(s);

        System.out.println(result);
    }

    private static String reorganizeString(String s) {

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int maxFreq = 0;

        for (int j : freq) {
            maxFreq = Math.max(maxFreq, j);
        }

        if (maxFreq > (s.length() + 1) / 2) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        System.out.println(Arrays.toString(freq));

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> (b.freq - a.freq));

        /*
         * a- > 2,
         * b -> 1,
         * c -> 1,
         * d -> 1,
         * e -> 2,
         * f -> 3
         * */

        for (char ch = 'a'; ch <= 'z'; ch++) {

            if (freq[ch - 'a'] > 0) {
                pq.add(new Pair(ch, freq[ch - 'a']));
            }
        }

        pq.forEach(pair -> {
            System.out.println(pair.ch + " → " + pair.freq);
        });

        while (pq.size() >= 2) {
            Pair pair1 = pq.poll();
            Pair pair2 = pq.poll();

            sb.append(pair1.ch);
            pair1.freq--;

            sb.append(pair2.ch);
            pair2.freq--;

            if (pair1.freq > 0) {
                pq.add(pair1);
            }

            if (pair2.freq > 0) {
                pq.add(pair2);
            }
        }

        if (!pq.isEmpty()) {
            sb.append(pq.poll().ch);
        } else {
            System.out.println("empty");
        }

        return sb.toString();
    }
}
