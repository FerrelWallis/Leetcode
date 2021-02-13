public class LongestCommonSubsequence_1143 {
    //1143. Longest Common Subsequence

    //Given two strings text1 and text2, return the length of their longest common subsequence.
    //A subsequence of a string is a new string generated from the original string with some characters
    //(can be none) deleted without changing the relative order of the remaining characters.
    //(eg, "ace" is a subsequence of "abcde" while "aec" is not).
    //A common subsequence of two strings is a subsequence that is common to both strings.
    //If there is no common subsequence, return 0.

    //Example 1:
    //Input: text1 = "abcde", text2 = "ace"
    //Output: 3
    //Explanation: The longest common subsequence is "ace" and its length is 3.

    //Example 2:
    //Input: text1 = "abc", text2 = "abc"
    //Output: 3
    //Explanation: The longest common subsequence is "abc" and its length is 3.

    //Example 3:
    //Input: text1 = "abc", text2 = "def"
    //Output: 0
    //Explanation: There is no such common subsequence, so the result is 0.

    //动态转移方程 如果text1[i]!=text2[j]  dp[i][j] = Integer.Max(dp[i-1][j], dp[i][j-1])
    //           如果text1[i] == text2[j]  dp[i][j] = dp[i-1][j-1] + 1
    public int longestCommonSubsequence(String text1, String text2) {
        char[] txt1 = text1.toCharArray();
        char[] txt2 = text2.toCharArray();
        int[][] dp = new int[txt1.length + 1][txt2.length + 1];

        for(int i = 1; i < txt1.length + 1; i++) {
            for(int j = 1; j < txt2.length + 1; j++) {
                if(txt1[i - 1] == txt2[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Integer.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[txt1.length][txt2.length];
    }
}
