public class BestTimetoBuyandSellStockwithCooldown_309 {

    //cool down
    //Say you have an array for which the ith element is the price of a given stock on day i.
    //Design an algorithm to find the maximum profit. You may complete as many transactions as you like
    //(ie, buy one and sell one share of the stock multiple times) with the following restrictions:
    //You may not engage in multiple transactions at the same time
    //(ie, you must sell the stock before you buy again).
    //After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)

    //Example:
    //Input: [1,2,3,0,2]
    //Output: 3
    //Explanation: transactions = [buy, sell, cooldown, buy, sell]

    //这题比best time 2多了一个冷冻期, 卖掉的第二天不能买，所以会影响， best time2的动态转移方程为：
    //dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + price[i])
    //dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - price[i])
    //冷冻期，即卖掉的第二天不能买，所以会影响第二个方程 ,即买入的状态，不是前一天买入的延续，就是两天前的利润里扣除今天买入的价格
    //dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-2][0] - price[i])
    public int maxProfit51(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
        dp[1][1] = Math.max(dp[0][1], -prices[1]);
        for(int i = 2; i < len; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i]);
        }
        return dp[len-1][0];
    }

    //定义三个变量，前两天非持有0，前一天持有1， 前一天非持有0
    public int maxProfit52(int[] prices) {
        int dp_ppre0 = 0, dp_pre0 = 0, dp_pre1 = Integer.MIN_VALUE;
        for(int price : prices) {
            int temp = dp_pre0;
            dp_pre0 = Math.max(dp_pre0, dp_pre1 + price);
            dp_pre1 = Math.max(dp_pre1, dp_ppre0 - price);
            dp_ppre0 = temp;
        }
        return dp_pre0;
    }
}
