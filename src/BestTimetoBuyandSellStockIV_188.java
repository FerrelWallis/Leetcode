import java.util.Arrays;

public class BestTimetoBuyandSellStockIV_188 {

    //4
    //You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
    //Design an algorithm to find the maximum profit. You may complete at most k transactions.
    //Notice that you may not engage in multiple transactions simultaneously
    //(i.e., you must sell the stock before you buy again).

    //Example 1:
    //Input: k = 2, prices = [2,4,1]
    //Output: 2
    //Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.

    //Example 2:
    //Input: k = 2, prices = [3,2,6,5,0,3]
    //Output: 7
    //Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

    //Constraints:
    //0 <= k <= 109
    //0 <= prices.length <= 1000
    //0 <= prices[i] <= 1000

    //基础框架
    //把上面的状态转移方程总结一下 base case：
    //dp[-1][k][0] = dp[i][0][0] = 0
    //dp[-1][k][1] = dp[i][0][1] = -infinity

    //状态转移方程：
    //dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
    //dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])

    //dp[i][k][0] i天数 k是已交易数 0是非持有状态 1是持有状态。
    //Best time 4, 最大交易数为 k, dp[i][k][0/1]
    //动态转移方程：dp[i][k][0] = max(dp[i][k][1] + price[i], dp[i][k][0])
    //            dp[i][k][1] = max(dp[i][k - 1][0] - price[i], dp[i][k][1])   买入的时候进行k-1的操作
    //k是指定的，但有个问题，k有可能会很大，导致创建的dp空间溢出，
    //在prices的长度为len的情况下，k的最大次数为多少呢，因为每天只能进行一次买卖操作，所以 k 最大是 len / 2
    //超出该范围 k值失去约束力，问题就变成了best time2, k = +inf

    //basecase: 初始化所有dp[i][k][0] = 0, dp[i][k][1] = Integer.MIN_VALUE
    //1. 三维数组dp 时间复杂度O（nk） 空间复杂度（nk）
    public int maxProfit41(int k, int[] prices) {
        int len = prices.length;
        if(k > len) return maxProfit422(prices);
        int[][][] dp = new int[len][k + 1][2];
        for(int i = 0; i < len; i++) {  //basecase
            for(int j = k; j >= 1; j--) {
                dp[i][k][0] = 0;
                dp[i][k][1] = Integer.MIN_VALUE;
            }
        }
        for(int i = 0; i < len; i++) {
            for(int j = k; j >= 1; j--) {
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[len - 1][k][0];
    }

    //空间优化 同时简化了basecase 只跟前一天有关，所以dp第一维i去掉
    //只有持有和非持有两个状态，所以直接拆成两个一维数组表示持有状态 0 和 1 ，该dp数组仅与购买次数 k 有关
    public int maxProfit42(int k, int[] prices) {
        int len = prices.length;
        if(k > len) return maxProfit422(prices);
        int[] dp_prek0 = new int[k + 1];
        int[] dp_prek1 = new int[k + 1];
        Arrays.fill(dp_prek1, Integer.MIN_VALUE);
        for(int price : prices) {
            for(int j = k; j >= 1; j--) {
                dp_prek0[j] = Math.max(dp_prek0[j], dp_prek1[j] + price);
                dp_prek1[j] = Math.max(dp_prek1[j], dp_prek0[j - 1] - price);
            }
        }
        return dp_prek0[k];
    }


    public int maxProfit422(int[] prices) {
        int nothold = 0, hold = Integer.MIN_VALUE;
        for(int p : prices) {
            int prenothold = nothold;
            nothold = Math.max(nothold, hold + p);
            hold = Math.max(hold, prenothold - p);
        }
        return nothold;
    }
}
