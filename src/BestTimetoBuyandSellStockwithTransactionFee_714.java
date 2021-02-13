public class BestTimetoBuyandSellStockwithTransactionFee_714 {

    //transanc
    //Your are given an array of integers prices, for which the i-th element is the price of a given
    //stock on day i; and a non-negative integer fee representing a transaction fee.
    //You may complete as many transactions as you like, but you need to pay the transaction fee for
    //each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the
    //stock share before you buy again.) Return the maximum profit you can make.

    //Example 1:
    //Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
    //Output: 8
    //Explanation: The maximum profit can be achieved by:
    //Buying at prices[0] = 1
    //Selling at prices[3] = 8
    //Buying at prices[4] = 4
    //Selling at prices[5] = 9
    //The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

    //这题比best time 2多了一个手续费, 每次买入的时候扣除一个手续费， best time2的动态转移方程为：
    //dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + price[i])
    //dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - price[i])
    //加入手续费这个条件
    //dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - price[i] - fee)
    public int maxProfit61(int[] prices, int fee) {
        int dp_pre0 = 0, dp_pre1 = Integer.MIN_VALUE;
        for(int price : prices) {
            int temp = dp_pre0;
            dp_pre0 = Math.max(dp_pre0, dp_pre1 + price);
            dp_pre1 = Math.max(dp_pre1, temp - price - fee);
        }
        return dp_pre0;
    }
}
