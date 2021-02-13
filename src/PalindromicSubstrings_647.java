
/*
Given a string, your task is to count how many palindromic substrings in this string.
The substrings with different start indexes or end indexes are counted as different substrings
even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

Note:The input string length won't exceed 1000.

* */
public class PalindromicSubstrings_647 {
    public static void main(String[] args) {
        PalindromicSubstrings_647 test=new PalindromicSubstrings_647();
        System.out.println(test.countSubstrings2("aaaaa"));
    }

    /*
    * 假设一个回文串中心为 center，该中心对应的最大回文串右边界为 right。存在一个 i 为当前回文串中心，满足 i > center，那么也存在一个 j 与 i 关于 center 对称，可以根据 Z[i] 快速计算出 Z[j]。

当 i < right 时，找出 i 关于 center 的对称点 j = 2 * center - i。此时以 i 为中心，半径为 right - i 的区间内存在的最大回文串的半径 Z[i] 等于 Z[j]。

例如，对于字符串 A = '@#A#B#A#A#B#A#＄'，当 center = 7, right = 13, i = 10 时，center 为两个字母 A 中间的 #，最大回文串右边界为最后一个 #，i 是最后一个 B，j 是第一个 B。

在 [center - (right - center), right] 中，区间中心为 center，右边界为 right，i 和 j 关于 center 对称，且 Z[j] = 3，可以快速计算出 Z[i] = min(right - i, Z[j]) = 3。

在 while 循环中，只有当 Z[i] 超过 right - i 时，才需要逐个比较字符。这种情况下，Z[i] 每增加 1，right 也会增加 1，且最多能够增加 2*N+2 次。因此这个过程是线性的。

最后，对 Z 中每一项 v 计算 (v+1) / 2，然后求和。假设给定最大回文串中心为 C，半径为 R，那么以 C 为中心，半径为 R-1, R-2, ..., 0 的子串也都是回文串。例如 abcdedcba 是以 e 为中心，半径为 4 的回文串，那么 e，ded，cdedc，bcdedcb 和 abcdedcba 也都是回文串。

除以 2 是因为实际回文串的半径为 v 的一半。例如回文串 a#b#c#d#e#d#c#b#a 的半径为实际原回文串半径的 2 倍。
*/

    //manacher算法
    public int countSubstrings3(String S) {
        char[] A = new char[2 * S.length() + 3];
        A[0] = '@';
        A[1] = '#';
        A[A.length - 1] = '$';
        int t = 2;
        for (char c: S.toCharArray()) {
            A[t++] = c;
            A[t++] = '#';
        }

        int[] Z = new int[A.length];
        int center = 0, right = 0;
        for (int i = 1; i < Z.length - 1; ++i) {
            if (i < right)
                Z[i] = Math.min(right - i, Z[2 * center - i]);
            while (A[i + Z[i] + 1] == A[i - Z[i] - 1])
                Z[i]++;
            if (i + Z[i] > right) {
                center = i;
                right = i + Z[i];
            }
        }
        int ans = 0;
        for (int v: Z) ans += (v + 1) / 2;
        return ans;
    }



    //中心扩散法3ms
    public int countSubstrings2(String s) {
        int count=0;
        for(int i=0;i<s.length();i++){
            count=centerspread(s,i,i,count);
            count=centerspread(s,i,i+1,count);
        }
        return count;
    }

    public int centerspread(String s,int left,int right,int count){
        //找最大扩散的时候，每往外扩散一层就+1，因为每次扩散都可以是独立的字子串
        while(left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    //动态规划
    public int countSubstrings(String s) {
        int count=s.length();
        boolean[][] dp=new boolean[count][count];
        for(int j=0;j<s.length();j++){
            for(int i=0;i<j;i++){
                if(s.charAt(i)==s.charAt(j)){
                    //i,j中小于等于一个字符时j-i+1<=3
                    if(j-i+1<=3||i+1==j-1||dp[i+1][j-1]==true){
                        dp[i][j]=true;
                        count++;
                    }else dp[i][j]=false;
                }else dp[i][j]=false;
            }
        }
        return count;
    }
}
