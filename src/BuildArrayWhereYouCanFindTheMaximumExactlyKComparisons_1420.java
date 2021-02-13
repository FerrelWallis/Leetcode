public class BuildArrayWhereYouCanFindTheMaximumExactlyKComparisons_1420 {
    //1420. Build Array Where You Can Find The Maximum Exactly K Comparisons
    //Given three integers n, m and k. Consider the following algorithm to find the maximum element of an
    //array of positive integers:
    //You should build the array arr which has the following properties:
    //arr has exactly n integers.
    //1 <= arr[i] <= m where (0 <= i < n).
    //After applying the mentioned algorithm to arr, the value search_cost is equal to k.
    //Return the number of ways to build the array arr under the mentioned conditions. As the answer may grow
    //large, the answer must be computed modulo 10^9 + 7.

    //Example 1:
    //Input: n = 2, m = 3, k = 1
    //Output: 6
    //Explanation: The possible arrays are [1, 1], [2, 1], [2, 2], [3, 1], [3, 2] [3, 3]

    //Example 2:
    //Input: n = 5, m = 2, k = 3
    //Output: 0
    //Explanation: There are no possible arrays that satisify the mentioned conditions.

    //Example 3:
    //Input: n = 9, m = 1, k = 1
    //Output: 1
    //Explanation: The only possible array is [1, 1, 1, 1, 1, 1, 1, 1, 1]

    //Example 4:
    //Input: n = 50, m = 100, k = 25
    //Output: 34549172
    //Explanation: Don't forget to compute the answer modulo 1000000007

    //Example 5:
    //Input: n = 37, m = 17, k = 7
    //Output: 418930126
    //Constraints:
    //1 <= n <= 50
    //1 <= m <= 100
    //0 <= k <= n

    //条件：1. 整个arr遍历的时候，更换最大值的次数为k （如果 k 必须 <= m）
    //     2. 1 <= arr[i] <= m

    //dfs
    public int ans = 0;
    public int numOfArrays(int n, int m, int k) {
        if(k > m) return 0;
        int[] arr = new int[n];
        dfs(arr, 0, 0, 0, m, k);
        return 0;
    }

    private void dfs(int[] arr, int cur_index, int cur_k, int cur_max, int m, int k) {
        if(cur_k > k) return;
        if(cur_index == arr.length && cur_k == k) {
            ans++;
            return;
        }
        for(int i = 1; i <= m; i++) {
            arr[cur_index] = i;
            if(i > cur_max) dfs(arr, cur_index + 1, cur_k + 1, i, m, k);
            else dfs(arr, cur_index + 1, cur_k, cur_max, m, k);
        }
    }

    //bfs同dfs用queue实现

    //动态规划 状态框架
    //子问题：条件：1. 整个arr遍历的时候，更换最大值的次数为k （如果 k 必须 <= m）
    //            2. 1 <= arr[i] <= m
    //            从长度为 n-1的情况 =》 长度为 n 的情况
    //状态定义： i 长度限制 n，s search_cost限制 k, j 最大数字限制 m ，，
    //          定义 df[i][s][j] 代表长度为i的数组，当前searchcost为 s, 当前最大数字为j的数组的数量
    //          df[i-1][?][?] => df[i][s][j] 有两种情况 ：
    //          1. 从df[i-1][s][j]过来的，说明新加的数字小于等于j，所以s和j都不变。
    //             由于新加的数字有1~j个可能性，数量为 df[i-1][s][j] * j
    //          2. 从df[i-1][s-1][j']过来的，其中j' < j, 说明新加的数字大于j'，所以s-1会加一，而j'变成j。
    //             新加的数字为j, 而j'有 1~j-1 个可能性，数量为 sum（df[i-1][s-1][j']   (1 <= j' <= j-1)）
    //dp方程：dp[i][s][j] = dp[i-1][s][j] * j + sum(dp[i-1][s-1][j']   (1 <= j' <= j-1))
    //       初始值  dp[1][1][1~m] = 1
    //时间复杂度O(NM^2K) 空间复杂度O(NMK)
    final int MOD = 1000000007;
    public int numOfArrays2(int n, int m, int k) {
        int[][][] dp = new int[n + 1][k + 1][m + 1];
        for(int i = 1; i <= m; i++) dp[1][1][i] = 1;
        for(int i = 2; i <= n; i++) {
            for(int s = 1; s <= i && s <= k; s++) {
                for(int j = 1; j <= m; j++) {
                    dp[i][s][j] = (dp[i-1][s][j] * j) % MOD;
                    for(int j1 = 1; j1 < j; j1++) {
                        dp[i][s][j] = (dp[i][s][j] + dp[i-1][s-1][j1]) % MOD;
                    }
                }
            }
        }
        int ans = 0;
        for(int i = 1; i <= m; i++) {
            ans += dp[n][k][i];
            ans %= MOD;
        }
        return ans;
    }


    //动态规划优化 前缀和，可以在for(int j = 1; j <= m; j++)前定义一个变量，进行前缀累加，这样就可以少一个for循环
    //时间复杂度O(NMK) 空间复杂度O(NMK)
    public int numOfArrays3(int n, int m, int k) {
        int[][][] dp = new int[n + 1][k + 1][m + 1];
        for(int i = 1; i <= m; i++) dp[1][1][i] = 1;
        for(int i = 2; i <= n; i++) {
            for(int s = 1; s <= i && s <= k; s++) {
                int presum = 0;
                for(int j = 1; j <= m; j++) {
                    dp[i][s][j] = (dp[i-1][s][j] * j) % MOD;
                    dp[i][s][j] = (dp[i][s][j] + presum) % MOD;
                    presum = (presum + dp[i-1][s-1][j]) % MOD;
                }
            }
        }
        int ans = 0;
        for(int i = 1; i <= m; i++) {
            ans += dp[n][k][i];
            ans %= MOD;
        }
        return ans;
    }
}
