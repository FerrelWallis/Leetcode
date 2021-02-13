
/*
* Given a string S and a string T, count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

It's guaranteed the answer fits on a 32-bit signed integer.

Example 1:

Input: S = "rabbbit", T = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from S.
(The caret symbol ^ means the chosen letters)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^
Example 2:

Input: S = "babgbag", T = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from S.
(The caret symbol ^ means the chosen letters)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^
* */
public class DistinctSubsequences_115 {

    /*
    * 就是给定序列 AA 和 BB，问 BB 在 AA 中出现多少次，可以不连续。
    * 相当于 AA 和 BB 的 LCS 是B，但这的侧重点是 BB。
    * 从最后一步出发，就是 BB 的最后一个字符，设 AA 的长度为 nn ，BB 的长度为 mm，有两种情况：
    * B[m-1] != A[n-1]B[m−1]!=A[n−1]，也就是 BB 的最后一个和 AA 的最后一个不等，
    * 那 AA 的最后一个字符就废了，考虑 A[0..n-2]A[0..n−2] 与 B[0..m-1]B[0..m−1]
    * B[m-1] = A[n-1]B[m−1]=A[n−1]，相等的情况，只需考虑 A[0..n-2]A[0..n−2] 与
    * B[0..m-2]B[0..m−2]
    * 注意：问次数，就是考虑加法，无重复无遗漏
    *
    * 转移方程
    * 就是把我们上面两种情况加起来即可，dp[i][j]dp[i][j] 就代表 AA 的前 ii 个和 BB 的前 jj 个
    * dp[i][j]=dp[i−1][j]+dp[i−1][j−1] && A[i−1]=B[i−1]
    *
    * 初始条件
    * 需要考虑空串的情况
    * 若 AA 是空串，BB 不是空串，BB 在 AA 中出现次数为 00，dp[0][j] = 0dp[0][j]=0
    * 若 BB 是空串，BB 在 AA 中出现次数是 11（因为 AA 可以是空串），
    * 就是把 AA 中的字符都删掉dp[i][0] = 1
    * 计算顺序
    * f[0] [0], f[0] [1], …, f[0] [m]f[0][0],f[0][1],…,f[0][m]
    * f[1] [0], f[1] [1], …, f[1] [m]f[1][0],f[1][1],…,f[1][m]
    * f[n] [0], f[n] [1], …, f[n] [m]f[n][0],f[n][1],…,f[n][m]
    * 时间复杂度O(n∗m)，空间复杂度O(n∗m)，可以用滚动数组优化成O(n)
    */
    //优化动态规划6ms
    public int numDistinct2(String s, String t) {
        int[] dp = new int[t.length()+1];
        dp[0] = 1;
        for(int i=0; i < s.length(); i++) {
            for(int j=t.length(); j > 0; j--) {
                if(s.charAt(i) == t.charAt(j-1)) {
                    dp[j] += dp[j-1];
                }
            }
        }
        return dp[t.length()];
    }


    //动态规划14ms
    public int numDistinct(String s, String t) {
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int j = 0; j < s.length() + 1; j++) dp[0][j] = 1;
        for (int i = 1; i < t.length() + 1; i++) {
            for (int j = 1; j < s.length() + 1; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                else dp[i][j] = dp[i][j - 1];
            }
        }
        return dp[t.length()][s.length()];
    }

}
