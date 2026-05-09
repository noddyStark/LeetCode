package org.leetcode.Graphs;


import java.util.*;

/*
* Given two words source and target, and a list of words words, find the length of the shortest series of edits that transforms source to target.
* Each edit must change exactly one letter at a time, and each intermediate word (and the final target word) must exist in words.

If the task is impossible, return -1.
* */
public class ShortestWordEditPath {

    static void main() {

        String source = "bit";
        String target = "dog";
        String[] words = {"but", "put", "big", "pot", "pog", "dog", "lot"};

        int result = shortestWordEditPath(source, target, words);
        System.out.println("result = " + result);
    }

    /*
        Level 1 / transformation = 1

                        hit
                         |
                         |
        Level 2 / transformation = 2

                        hot
                      /     \
                     /       \
        Level 3 / transformation = 3

                  dot         lot
                   |           |
                   |           |
        Level 4 / transformation = 4

                  dog         log
                     \       /
                      \     /
        Level 5 / transformation = 5

                        cog
    * */

    private static int shortestWordEditPath(String source, String target, String[] words) {

        Set<String> wordSet = new HashSet<>(List.of(words));

        if (!wordSet.contains(target)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(source);

        wordSet.remove(source); // avoid revisiting source

        int levels = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int s = 0; s < size; s++) {
                String currentWord = queue.poll();

                if (currentWord.equals(target)) {
                    return levels;
                }

                char[] chars = currentWord.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    char originalChar = chars[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;

                        String nextWord = new String(chars);

                        if (wordSet.contains(nextWord)) {
                            queue.offer(nextWord);

                            // mark visited
                            wordSet.remove(nextWord);
                        }
                    }

                    chars[i] = originalChar;
                }
            }

            levels++;
        }

        return -1;

    }

    private static void dfs(String source, String target) {

    }
}

/*
 Why BFS?
Because this is really a graph problem.
Think of each word as a node:
 * bit ---- but ---- put ---- pot ---- pog ---- dog
 * \
 * \---- big
An edge exists if two words differ by one character.

 Now the problem becomes:
 * Find shortest path from "bit" to "dog""
 * And BFS is the standard algorithm for shortest path in an unweighted graph.
 *
 */