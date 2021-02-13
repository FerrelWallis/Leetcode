
/*
Given a string s, find the longest palindromic substring in s.
You may assume that the maximum length of s is 1000.

Example 1:
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Example 2:
Input: "cbbd"
Output: "bb"
* */
public class LongestPalindromicSubstring_5 {

    public static void main(String[] args) {
        LongestPalindromicSubstring_5 test=new LongestPalindromicSubstring_5();
        String s = "acccddd";
        System.out.println(s.substring(1,3));
    }


    class solution {
        //中心扩散法1
        //子问题：当前index作为对称中心，向左右扩散，查看扩散长度  对称回文有两种情况：1. aba 2. abba 奇数对称和偶数对称
        //观察偶数对称和技术对称的区别就是偶数对称中心b存在连续多个，这样在扩散之前只要确保找出所有中心b两边一致的b
        //再将连续的b作为中心，开始向两边扩散即可。 1. b为中心  2. bb为中心
        //时间复杂度O(N^2)
        public String longestPalindrome(String s) {
            String ans = "";
            int maxlen = 0;
            for(int i = 0; i < s.length(); i++) {
                int left = i, right = i;
                while (left >= 0 && s.charAt(left) == s.charAt(i)) left--;
                while (right < s.length() && s.charAt(right) == s.charAt(i)) right++;
                while (left >= 0 && right < s.length()) {
                    if(s.charAt(left) == s.charAt(right)) {
                        left--;
                        right++;
                    } else break;
                }
                left += 1;
                if(right - left > ans.length()) ans = s.substring(left, right);
            }
            return ans;
        }
    }



    /*
    * 中心扩散法2
    * 子问题：当前index作为对称中心，向左右扩散，查看扩散长度  对称回文有两种情况：1. aba 2. abba 奇数对称和偶数对称
    * 所以spread的中心 为 （i，i） 假设是奇数对称 ， 中心为(i,i+1) 假设偶数对称，两个都spread一次取较大的
    * */
    public String longestPalindrome3(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        String res = s.substring(0, 1);
        for (int i = 0; i < len - 1; i++) {
            String oddStr = centerSpread(s, i, i);
            String evenStr = centerSpread(s, i, i + 1);
            String maxLenStr = oddStr.length() > evenStr.length() ? oddStr : evenStr;
            if (maxLenStr.length() > maxLen) {
                maxLen = maxLenStr.length();
                res = maxLenStr;
            }
        }
        return res;
    }

    private String centerSpread(String s, int left, int right) {
        int len = s.length();
        int i = left;
        int j = right;
        while (i >= 0 && j < len) {
            if (s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            } else {
                break;
            }
        }
        return s.substring(i + 1, j);
    }


    /*
    动态规划
    自问题：
    * */

    public String longestPalindrome2(String s) {
        int len=s.length();
        if(len<2) return s;
        int maxlen=1;
        int start=0;

        boolean[][] chart=new boolean[len][len];
        char[] ss=s.toCharArray();

        for(int j=1;j<len;j++){
            for(int i=0;i<j;i++){
                if(ss[i]==ss[j]){
                    if(j-i<3||i+1==j-1) chart[i][j]=true;
                    else {
                        chart[i][j]=chart[i+1][j-1];
                    }
                }else chart[i][j]=false;
                if(chart[i][j] && j-i+1>maxlen){
                    System.out.println(111);
                    maxlen=j-i+1;
                    start=i;
                }
            }
        }
        System.out.println(start+" "+maxlen);
        return s.substring(start,start+maxlen);

    }




    /*
    * 暴力法，枚举所有子串
    * */
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
        char[] charArray = s.toCharArray();

        // 枚举所有长度大于 1 的子串 charArray[i..j]
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && validPalindromic(charArray, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 验证子串 s[left..right] 是否为回文串
     */
    private boolean validPalindromic(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
