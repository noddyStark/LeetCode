package Top75.SlidingWindow;

/*
904. Fruit Into Baskets
You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.

Example 1:
Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.

Example 2:
Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].

Example 3:
Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].
*/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FruitIntoBaskets {

    static void main() {
        int[] fruits = {1, 2, 3, 2, 2};

        System.out.println(totalFruitBruteForce(fruits));
    }

    /*
     * 1. We only have 2 baskets
     * 2. Each basket can hold 1 type of fruit, no limit on the amount of fruit each basket can hold.
     * 3. Return the maximum number of fruits you can pick.
     * 4.
     * */
    private static int totalFruitBruteForce(int[] fruits) {

        int maxFruits = 0;

        for (int i = 0; i < fruits.length; i++) {
            Set<Integer> uniqueFruits = new HashSet<>();
            int count = 0;

            for (int j = i; j < fruits.length; j++) {
                uniqueFruits.add(fruits[j]);

                if (uniqueFruits.size() > 2) {
                    break;
                }
                count++;
                maxFruits = Math.max(maxFruits, count);
            }
        }

        return maxFruits;
    }

    private static int totalFruitSlidingWindow(int[] fruits) {

        // 1, 2, 3, 2, 2
        Map<Integer, Integer> freqMap = new HashMap<>();

        int left = 0;
        int maxFruit = 0;

        for (int right = 0; right < fruits.length; right++) {

            freqMap.put(fruits[right], freqMap.getOrDefault(fruits[right] , 0) + 1);

            while (freqMap.size() > 2) {
                freqMap.put(fruits[left], freqMap.get(fruits[left]) - 1);

                if (freqMap.get(fruits[left]) == 0) {
                    freqMap.remove(fruits[left]);
                }
                left++;
            }


            maxFruit = Math.max(maxFruit, right - left + 1);
        }

        return maxFruit;
    }
}
