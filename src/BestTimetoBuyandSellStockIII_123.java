public class BestTimetoBuyandSellStockIII_123 {

    //Say you have an array for which the ith element is the price of a given stock on day i.
    //Design an algorithm to find the maximum profit. You may complete at most two transactions.
    //Note: You may not engage in multiple transactions at the same time (i.e., you must sell the
    //stock before you buy again).

    //Example 1:
    //Input: prices = [3,3,5,0,0,3,1,4]
    //Output: 6
    //Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
    //Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.

    //Example 2:
    //Input: prices = [1,2,3,4,5]
    //Output: 4
    //Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
    //Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging
    //multiple transactions at the same time. You must sell before buying again.

    //Example 3:
    //Input: prices = [7,6,4,3,1]
    //Output: 0
    //Explanation: In this case, no transaction is done, i.e. max profit = 0.

    //Example 4:
    //Input: prices = [1]
    //Output: 0

    //基础框架
    //把上面的状态转移方程总结一下 base case：
    //dp[-1][k][0] = dp[i][0][0] = 0
    //dp[-1][k][1] = dp[i][0][1] = -infinity

    //状态转移方程：
    //dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    //dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

    //dp[i][k][0] i天数 k是已交易数 0是非持有状态 1是持有状态。
    //Best time 3, 最大交易数为 2, dp[i][2][0/1]
    //动态转移方程：dp[i][k][0] = max(dp[i][k][1] + price[i], dp[i][k][0])
    //            dp[i][k][1] = max(dp[i][k - 1][0] - price[i], dp[i][k][1])   买入的时候进行k-1的操作
    //时间复杂度O（n） 空间复杂度O（n）
    public int maxProfit31(int[] prices) {
        int len = prices.length;
        int maxk = 2; //考虑到basecase k = 0代表已交易数为 0的情况
        int[][][] dp = new int[len][maxk + 1][2];
        for(int i = 0; i < len; i++) {
            for(int k = maxk; k >= 1; k--) {
                if(i == 0) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = Integer.MIN_VALUE;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[len - 1][maxk][0];
    }

    //base case：
    //dp[-1][k][0] = dp[i][0][0] = 0
    //dp[-1][k][1] = dp[i][0][1] = -infinity
    //因为k只有2,且每次只跟前一天信息有关，所以不需要数组，可以用4个变量穷举
    //dp[i][2][0] = max(dp[i-1][2][0], [i-1][2][1] + price[i])
    //dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - price[i])
    //dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + price[i])
    //dp[i][1][1] = max(dp[i-1][1][1], -price[i])
    public int maxProfit(int[] prices) {
        int dp_10 = 0, dp_11 = Integer.MIN_VALUE, dp_20 = 0, dp_21 = Integer.MIN_VALUE;
        for(int p : prices) {
            dp_20 = Math.max(dp_20, dp_21 + p);
            dp_21 = Math.max(dp_21, dp_10 - p);
            dp_10 = Math.max(dp_10, dp_11 + p);
            dp_11 = Math.max(dp_11, -p);
        }
        return dp_20;
    }

}
