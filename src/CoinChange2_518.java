import java.util.Arrays;

public class CoinChange2_518 {

    //贪心 + dfs 超时
    public int count = 0;
    public int change(int amount, int[] coins) {
        Arrays.sort(coins);
        dfs(coins, coins.length - 1, amount);
        return count;
    }

    private void dfs(int[] coins, int index, int amount) {
        if (amount == 0) {
            count += 1;
            return;
        }
        if (amount < 0) return;
        for (int i = amount / coins[index]; i >= 0; i--) {
            dfs(coins, index - 1, amount - coins[index] * i);
        }
    }


    //2. 动态规划 假设coins[c1,c2,c3] 自底向上 df[i] = df[i-c1] + df[i-c2]+df[i-c3]
    public int change2(int amount, int[] coins) {
        int[] df = new int[amount + 1];
        df[0] = 1;
        for(int c : coins) {
            for (int i = c; i <= amount; i++) {
                df[i] += df[i - c];
            }
        }
        return df[amount];
    }
}
