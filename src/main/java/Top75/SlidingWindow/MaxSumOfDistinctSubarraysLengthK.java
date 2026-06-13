package Top75.SlidingWindow;

import java.util.HashMap;

public class MaxSumOfDistinctSubarraysLengthK {

    static void main() {
        int[] numbers = {3, 2, 2, 3, 4, 6, 7, 7, -1};
        int k = 4;

        System.out.println(maxSum(numbers, k));
    }

    private static long maxSum(int[] numbers, int k) {

        HashMap<Integer, Integer> freqMap = new HashMap<>();
        int start = 0;
        long maxSum = Long.MIN_VALUE;
        long currentSum = 0;

        for (int end = 0; end<numbers.length; end++) {
            currentSum += numbers[end];
            freqMap.put(numbers[end], freqMap.getOrDefault(numbers[end], 0) + 1);

            /*
            currentSum = 9
            at end = 5
            currentSum = 9 + 6 = 15
            3 -> 2
            2 -> 2
            4 -> 1
            6 -> 1
            * */

            if (end - start + 1 == k) { // 5 - 2 + 1 = 4
                if (freqMap.size() == k) {
                    maxSum = Math.max(currentSum, maxSum); // update maxSum = 15
                }

                currentSum -= numbers[start]; // currentSum = 15 - 2 = 13

                /* deduct 1 occurrence of 2
                    3 -> 1
                    2 -> 0
                    4 -> 1
                    6 -> 1
                * */
                freqMap.put(numbers[start], freqMap.get(numbers[start]) - 1);

                if (freqMap.get(numbers[start]) == 0) {
                    System.out.println("Updating Map by Removing numbers[start] = " + freqMap + " at index end = " + end);
                    freqMap.remove(numbers[start]);
                }
                start++;
            }

        }

        return maxSum == Long.MIN_VALUE ?  0 : maxSum;
    }
}
