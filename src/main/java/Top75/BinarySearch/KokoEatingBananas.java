package Top75.BinarySearch;

/*
Bobby has an orchard of apple trees, and each tree has a certain number of apples on it.

Bobby wants to collect all the apples by the end of the day by collecting a fixed number of apples per hour.
He can only harvest apples from one tree per hour - if he finishes collecting apples from a tree before the hour is up,
he must wait until the next hour to move to the next tree.

For example, if there are 3 apples on a tree and Bobby collects 1 apple per hour, it will take him 3 hours to finish collecting the apples on that tree.
If he harvests 2 apples per hour, it will take him 2 hours to finish collecting all the apples on that tree (he waits until the hour is up even though he finishes early).
Write a function to determine the slowest rate of apples Bobby can harvest per hour to finish the job in at most 'h' hours. The input to the function is an array of integers representing the number of apples on each tree and an integer 'h' representing the number of hours Bobby has to finish the job within.

Example 1:

Input:

apples = [3, 6, 7], h = 8
Output: 3

Explanation:

1 apple per hour: 3 hours for first tree, 6 hours the second tree, and 7 hours for third tree. This totals 16 hours, which is more than the 8 hours he has to finish the job. NOT VALID.
2 apples per hour: 2 + 3 + 4 = 9 hours, which is more than the 8 hours he has to finish the job. NOT VALID.
3 apples per hour: 1 + 2 + 3 = 6 hours, which is less than the 8 hours he has to finish the job. VALID.
4 apples per hour: 1 + 2 + 2 = 5 hours, which is less than the 8 hours he has to finish the job. VALID.
5 apples per hour: 1 + 2 + 2 = 5 hours, which is less than the 8 hours he has to finish the job. VALID.
Therefore, the minimum number of apples Bobby must harvest per hour to finish the job in 8 hours or less is 3.

Example 2:

Input:

apples = [25, 9, 23, 8, 3], h = 5
Output: 25 (Bobby must harvest 25 apples per hour to finish in 5 hours or less)
*/
public class KokoEatingBananas {

    static void main() {
        int[] apples = {3, 6, 7};
        int hour = 8;

        System.out.println(slowestRateOfEatingApples(apples, hour));
    }

    private static int slowestRateOfEatingApples(int[] apples, int hour) {

        // Max at 7 apples per hour
        // 1 -> 7
        // 1 2 3 4 5 6 7

        int maxRate = 0;

        for (int apple : apples) {
            maxRate = Math.max(maxRate, apple);
        }

        int left = 1;
        int right = maxRate;
        int answer = maxRate;

        while (left <= right) {

            int midRate = left + (right - left)/2;
            int totalHours = totalTimeTakeToEatBananas(midRate, apples);

            if (totalHours <= hour) {
                answer = midRate;
                right = midRate - 1;
            } else {
                // midRate is too slow, need faster rate
                left = midRate + 1;
            }
        }

        return answer;
    }

    private static int totalTimeTakeToEatBananas(int rate, int[] apples) {
        int totalHours = 0;

        for (int i = 0; i < apples.length; i++) {
            int quotient = apples[i]/rate; // => 3 / 4 = 0
            int remainder = apples[i] % rate; // => 3 % 4 = 3

            if (remainder == 0) {
                totalHours += quotient;
            } else if (quotient == 0) {
                totalHours += 1;
            } else {
                totalHours += quotient + 1;
            }
        }

        return totalHours;
    }
}
