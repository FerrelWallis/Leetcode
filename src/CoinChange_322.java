import java.util.Arrays;

public class CoinChange_322 {
    //You are given coins of different denominations and a total amount of money amount.
    //Write a function to compute the fewest number of coins that you need to make up that amount.
    //If that amount of money cannot be made up by any combination of the coins, return -1.
    //You may assume that you have an infinite number of each kind of coin.

    //Example 1:
    //Input: coins = [1,2,5], amount = 11
    //Output: 3
    //Explanation: 11 = 5 + 5 + 1

    //Example 2:
    //Input: coins = [2], amount = 3
    //Output: -1

    //Example 3:
    //Input: coins = [1], amount = 0
    //Output: 0

    //Example 4:
    //Input: coins = [1], amount = 1
    //Output: 1

    //Example 5:
    //Input: coins = [1], amount = 2
    //Output: 2
    public static void main(String[] args) {
        //[1,2,5]
        //11
        int[] coins = new int[]{1,2,5};
        CoinChange_322 test = new CoinChange_322();
        int result=test.coinChange(coins, 11);
    }

    //没法直接用贪心，因为coins之间不存在倍数关系，假设6249用最大数量的最大面值减去之后，剩下的值无法正好被较小的面值
    //1. 使用贪心+dfs+剪枝
    //四个关键：
    //1. 贪心：先丢大硬币，再丢会超过总额时，就可以递归下一层丢的是稍小面值的硬币
    //2. 乘法对加法的加速：优先丢大硬币进去尝试，也没必要一个一个丢，可以用乘法算一下最多能丢几个，
    // 如果因为丢多了导致最后无法凑出总额，再回溯减少大硬币数量
    //3. 最先找到的并不是最优解：考虑到有 [1,7,10] 这种用例，按照贪心思路 10 + 1 + 1 + 1 + 1 会比 7 + 7 更早找，
    // 还是需要把所有情况都递归完
    //4. 疯狂剪枝
    int ans = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        dfs(coins, amount, coins.length-1, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void dfs(int[] coins, int amount, int index, int count) {
        if (amount == 0) {
            ans = Math.min(ans, count);
            return;
        }
        if (index == -1) return;
        //剪枝 k+count已经大于当前ans的话，就没必要继续了
        for (int k = amount / coins[index]; k >= 0 && k + count < ans; k--) {
            dfs(coins, amount - k * coins[index], index - 1, count + k);
        }
    }


    //动态规划 假设coin面值[c1,c2,c3]  amount =i 的情况下
    //转移方程 dp[i] = min(dp[i-c1], dp[i-c2], dp[i-c3])

    //1. 动态规划 自顶向下 + 记忆化搜索
    public int[] dp;
    public int coinChange3(int[] coins, int amount) {
        dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int ans = recur(coins, amount);
        return (ans == Integer.MAX_VALUE)? -1 : ans;
    }

    private int recur(int[] coins, int amount) {
        if(dp[amount] != Integer.MAX_VALUE) return dp[amount];
        if(amount < 0) return Integer.MAX_VALUE;
        if(amount == 0) return 0;
        for(int c : coins) {
            dp[amount] = Math.min(recur(coins, amount - c) + 1, dp[amount]);
        }
        return dp[amount];
    }

    //动态规划 自底向上 转移方程 dp[i] = min(dp[i-c1], dp[i-c2], dp[i-c3])
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++) {
            for(int j = 0; j < coins.length; j++) {
                if(coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }


    //贪心 + dfs + 剪枝
    public int count = 0;
    public int coinChange4(int[] coins, int amount) {
        Arrays.sort(coins);
        dfs2(coins, coins.length - 1, amount, count);
        return count;
    }

    private void dfs2(int[] coins, int index, int amount, int count) {
        if(amount == 0) {
            ans = Math.min(ans, count);
            return;
        }
        if(index < 0) return;
        for(int i = amount / coins[index]; i >=0 && count + i < ans; i++) {
            dfs2(coins, index - 1, amount - coins[index] * i, count + i);
        }
    }

}
