package org.leetcode.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicateII {

    public static void main(String[] args) {

        int[] numbers = {0,1,2,3,2,5};
        int k = 3;

        System.out.println("Do we have two duplicated that are at most K distant apart : "
                + containsDuplicate(numbers, k));
    }

    private static boolean containsDuplicate(int[] numbers, int k) {

        Map<Integer, Integer> lastSeenIndex = new HashMap<>();

        for (int i=0; i < numbers.length; i++) {
            if (lastSeenIndex.containsKey(numbers[i])) {
                int previousIndex = lastSeenIndex.get(numbers[i]);

                if(i - previousIndex <= k) {
                    return true;
                }
            }

            lastSeenIndex.put(numbers[i], i);
        }
        return false;
    }
}
