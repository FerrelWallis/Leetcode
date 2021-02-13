public class BestTimetoBuyandSellStockII_122 {
    //2
    //Say you have an array prices for which the ith element is the price of a given stock on day i.
//
//Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
//
//Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
//
//Example 1:
//
//Input: [7,1,5,3,6,4]
//Output: 7
//Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
//             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
//Example 2:
//
//Input: [1,2,3,4,5]
//Output: 4
//Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
//             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
//             engaging multiple transactions at the same time. You must sell before buying again.
//Example 3:
//
//Input: [7,6,4,3,1]
//Output: 0
//Explanation: In this case, no transaction is done, i.e. max profit = 0.
//

    //基础框架
    //把上面的状态转移方程总结一下 base case：
    //dp[-1][k][0] = dp[i][0][0] = 0
    //dp[-1][k][1] = dp[i][0][1] = -infinity

    //状态转移方程：
    //dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    //dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

    //dp[i][k][0] i天数 k是最大交易数 0是非持有状态 1是持有状态。
    //Best time 2, 最大交易数为 +Inf，无限制的话加一点一没有影响所以k条件也可以去掉。只需要 dp[i][0/1]
    //动态转移方程：dp[i][0] = max(dp[i][1] + price[i], dp[i][0])
    //            dp[i][1] = max(dp[i][0] - price[i], dp[i][1])
    //时间复杂度O（n） 空间复杂度O（n）
    public int maxProfit21(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for(int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }

    //空间优化 O（1）无论是hold还是nothold都表示当前可获得的最大利润，确保无论是持有状态还是非持有状态都是利润最大的情况
    public int maxProfit22(int[] prices) {
        int len = prices.length;
        int nothold = 0, hold = -prices[0];
        for(int i = 1; i < len; i++) {
            int prenothold = nothold;
            nothold = Math.max(nothold, hold + prices[i]);
            hold = Math.max(hold, prenothold - prices[i]);
        }
        return nothold;
    }

    //贪心算法 因为可以无限多此交易,之所以只要后一天比前一天大,就相减并累加到profit中,
    //虽然一天不可以操作两次，指能买或者卖，但是累加的profit相当于最初一次买入到最后一次卖出的最大利润
    //例如1 2 3 4 5 6 7 8 9，累加的profit是8，实际上表示第一天买入，最后一天卖出的最大利润
    public int maxProfit2(int[] prices) {
        int max_profit = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i - 1]) max_profit += prices[i] - prices[i - 1];
        }
        return max_profit;
    }
}
