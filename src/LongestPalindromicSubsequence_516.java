
/*
Given a string s, find the longest palindromic subsequence's length in s.
You may assume that the maximum length of s is 1000.

Example 1:
Input:"bbbab"
Output:3
One possible longest palindromic subsequence is "bbb".

Example 2:
Input:"cbbd"
Output:2
One possible longest palindromic subsequence is "bb".
* */
public class LongestPalindromicSubsequence_516 {
    public static void main(String[] args) {
        LongestPalindromicSubsequence_516 test=new LongestPalindromicSubsequence_516();
        System.out.println(test.longestPalindromeSubseq("abcabca"));
    }

    //动态规划，自下而上
    public int longestPalindromeSubseq2(String s) {
        int len=s.length();
        int[][] check=new int[len][len];
        for(int j=0;j<len;j++){
            check[j][j]=1;
            for (int i=j-1;i>=0;i--){
                if(s.charAt(i)==s.charAt(j)) check[i][j]=check[i+1][j-1]+2;
                else check[i][j]=Math.max(check[i][j-1],check[i+1][j]);
            }
        }
        return check[0][len-1];
    }



    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            f[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
            }
        }
        return f[0][n - 1];
    }
}
