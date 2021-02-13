public class EditDistance_72 {
    //Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
    //
    //You have the following three operations permitted on a word:
    //
    //Insert a character
    //Delete a character
    //Replace a character
    // 
    //
    //Example 1:
    //
    //Input: word1 = "horse", word2 = "ros"
    //Output: 3
    //Explanation:
    //horse -> rorse (replace 'h' with 'r')
    //rorse -> rose (remove 'r')
    //rose -> ros (remove 'e')
    //Example 2:
    //
    //Input: word1 = "intention", word2 = "execution"
    //Output: 5
    //Explanation:
    //intention -> inention (remove 't')
    //inention -> enention (replace 'i' with 'e')
    //enention -> exention (replace 'n' with 'x')
    //exention -> exection (replace 'n' with 'c')
    //exection -> execution (insert 'u')

    //动态规划 字符串动规常用操作 x轴 s1 y轴 s2
    //    # o r e (s2)
    //  # 0 1 2 3                    insert操作（横向）
    //  h 1
    //  o 2
    //  r 3
    //  s 4
    //  e 5
    //     delete(纵向)

    //1.动态规划 自顶向下 就是从右下角出发，需要判断边界，dfs + memo 避免重复计算

    //2.动态规划 自底向上
    // 子问题 假设我们要求 horse => ore的操作数 ，当前我们已经知道理 horse => or的操作数，
    //       那么(horse => ore的操作数) = (horse => or的操作数 + 1) || (hors => ore的操作数 + 1) || (hors => or的操作数 + 1)
    //状态定义： 子问题有三种情况（s1中insert,往s1中insert(等价于),change操作）
    //dp方程          1. insert dp[i][j] = dp[i][j-1] (+ 1)
    //               2. delete dp[i][j] = dp[i - 1][j] (+ 1)
    //               3. change dp[i][j] = 如果最后一个字母不同dp[i - 1][j - 1] (+ 1) 如果相同 dp[i - 1][j - 1]
    // dp[i][j] = min(insert,delete,change) + 1
    public int minDistance(String word1, String word2) {
        if(word1.length() == 0) return word2.length();
        if(word2.length() == 0) return word1.length();
        int s1 = word1.length();
        int s2 = word2.length();
        int[][] dp = new int[s1+1][s2+1];
        for(int i = 0; i < s1 + 1; i++) dp[i][0] = i;
        for(int i = 0; i < s2 + 1; i++) dp[0][i] = i;
        for(int i = 1; i < s1 + 1; i++) {
            for(int j = 1; j < s2 + 1; j++) {
                int change = 0;
                //这里减一是因为后面统一操作+1了，不要加一前面先减去跟后面抵消
                if(word1.charAt(i-1) == word2.charAt(j-1)) change = dp[i-1][j-1] - 1;
                else change = dp[i-1][j-1];
                dp[i][j] = Math.min(dp[i][j-1],Math.min(dp[i-1][j], change)) + 1;
            }
        }
        return dp[s1][s2];
    }
}
