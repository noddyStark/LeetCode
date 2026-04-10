package org.leetcode.Sorting.BinarySearch;

/**
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
 * <p>
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile.
 * If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 * <p>
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 * <p>
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 */

public class KokoEatingBananas {

    public static void main(String[] args) {
        int[] piles = {30, 11, 23, 4, 20};
        int hours = 5;

        System.out.println("minEatingSpeed = " + minEatingSpeed(piles, hours));
    }

    private static int minEatingSpeed(int[] piles, int hours) {
        int maxPile = 0;

        for (int pile : piles) {
            maxPile = Math.max(maxPile, pile);
        }

        int low = 1;
        int high = maxPile;
        int answer = maxPile;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (canEatInTime(piles, hours, mid)) {
                high = mid;
            } else {
                low = mid+1;
            }

        }
        return low;
    }

    private static boolean canEatInTime(int[] piles, int hours, int speed) {

        long totalHours = 0;

        for (int pile : piles) {
            totalHours += (long) Math.ceil((double) pile / speed);
        }

        return totalHours <= hours;
    }
}


