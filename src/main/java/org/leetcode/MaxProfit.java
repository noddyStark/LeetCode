package org.leetcode;


/*
* 121. Best Time to Buy and Sell Stock
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

You are given an array prices where prices[i] is the price of a given stock on the ith day.
You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future
to sell that stock. Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

Example 1:

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
Example 2:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transactions are done and the max profit = 0.

Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 104
* */

public class MaxProfit {
    static void main() {

        int[] prices = {7, 1, 5, 3, 6, 4};
        int maxProfit = maxProfit(prices);
        System.out.println(maxProfit);
    }

    private static Integer maxProfit(int[] prices) {

        for (int i = 0; i < prices.length - 1; i++) {

            // If the price of (i+1)th day is never greater than ith day, that basically
            // means we never had any profit, because we need to choose a date in future to sell ths stock.
            if (prices[i + 1] > prices[i]) {
                System.out.println("What is the value of current price = " + prices[i] + " & future price = " + prices[i+1]);
                System.out.println("Since for future date we have higher price, we will always have profit");
                break;
            } else {
                if (i == prices.length - 1) {
                    return 0;
                }
            }
        }

        int maxProfit = 0;

        /*
         * BRUTE FORCE SOLUTION
         * Using 2 for loops, with time complexity of O(N)2
         * */
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > prices[i]) {
                    maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
                }
            }
        }

        /*
        * GREEDY ALGORITHM
        *
        * Keeping track of minimum price and calculating profit
        * */

        int minimumPrice = Integer.MAX_VALUE;

        for (int currentPrice : prices) {
            if(currentPrice < minimumPrice) {
                System.out.println("Current price is " + currentPrice + " lesser than minimum price is " + minimumPrice + " No Profit as of now");
                minimumPrice = currentPrice;
            } else {
                System.out.println("Current price is " + currentPrice + " greater than minimum price is " + minimumPrice + " Calculation Profit");
                int profit = currentPrice - minimumPrice;
                maxProfit = Math.max(maxProfit, profit);
            }
        }

        return maxProfit;
    }
}
