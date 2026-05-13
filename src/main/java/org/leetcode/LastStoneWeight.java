package org.leetcode;

import java.util.PriorityQueue;

public class LastStoneWeight {
    static void main() {
        int[] stones = {2, 7, 4, 1, 8, 1};

        int lastStoneWeight = lastStoneWeight(stones);

        System.out.println("Result = " + lastStoneWeight);
    }

    private static int lastStoneWeight(int[] stones) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int stone : stones) {
            maxHeap.offer(stone);
        }

        System.out.println(maxHeap);

        if (maxHeap.size() == 1) {
            return maxHeap.peek();
        }

        while (maxHeap.size() > 1) {
            int y = maxHeap.poll(); // heaviest
            int x = maxHeap.poll(); // second heaviest

            if (y != x) {
                maxHeap.offer(y - x);
            }
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }

}
