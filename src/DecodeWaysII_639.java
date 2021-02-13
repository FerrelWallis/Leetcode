public class DecodeWaysII_639 {
    //DecodeWaysII_639
    //A message containing letters from A-Z is being encoded to numbers using the following mapping way:
    //'A' -> 1
    //'B' -> 2
    //...
    //'Z' -> 26
    //Beyond that, now the encoded string can also contain the character '*', which can be treated as one of
    //the numbers from 1 to 9.
    //Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
    //Also, since the answer may be very large, you should return the output mod 109 + 7.

    //Example 1:
    //Input: "*"
    //Output: 9
    //Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".

    //Example 2:
    //Input: "1*"
    //Output: 9 + 9 = 18
    //Note:
    //The length of the input string will fit in range [1, 105].
    //The input string will only contain the character '*' and digits '0' - '9'.

    //动态规划 子问题：依旧是数字的处理，多了一个 *（表示1-9） 宗旨不变 明确一点 数字digit范围是 0~9  *号范围是 1~9
    //状态定义：当前 = （0 or 前一个的数量） + （0 or 前两个的数量）
    //dp方程： dp[i] = dp[i-1] * numOneDigit + dp[i-2] * numTwoDigits
    //          dp[i-1] 成立，即单个数字成立，有两种情况： 1. 新的digit >=1 且 <= 9 ，numOneDigit = 1
    //                                                 2. 新的digit =='*' ，numOneDigit = 9
    //          dp[i-2] 成立，即两位数成立的情况，前一位digit + 新的digit 有4种情况：
    //                              1. 前后都是数字  需要 >=10 且 <=26，numTwoDigits = 1
    //                              2. * + 数字   如果数字 >=0 且 <=6，*可以是1或2，numTwoDigits = 2
    //                                            如果数字 >=7 且<=9，*只能是1，numTwoDigits = 1
    //                              3. 数字 + *   如果数字 == 1，numTwoDigits = 9  （11~19）
    //                                            如果数字 == 2，numTwoDigits = 6 （21~26）
    //                              4. * + *  只能是（11~19, 21~26   20记得要排除！！！被坑），numTwoDigits = 15
    //时间复杂度O(n) 空间复杂度O(n)

    public static void main(String[] args) {
        String s = "1*";
        DecodeWaysII_639 test = new DecodeWaysII_639();
        test.numDecodings(s);
    }

    public int numDecodings(String s) {
        if(s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1; dp[1] = (s.charAt(0) == '*') ? 9 : 1;
        for(int i = 2; i <= len; i++) {
            char cur =s.charAt(i - 1), pre = s.charAt(i - 2);
            int numOneDigit = 0, numTwoDigit = 0;
            String twodigi = pre + "" + cur;
            if(cur == '*') numOneDigit = 9;
            else if(cur - '0' > 0) numOneDigit = 1;

            if(pre == '*' && cur == '*') numTwoDigit = 15;
            if(pre != '*' && cur != '*' && Integer.parseInt(twodigi) >= 10 && Integer.parseInt(twodigi) <= 26)  numTwoDigit = 1;
            if(pre == '*' && cur != '*') {
                if(cur - '0' >= 0 && cur - '0' <= 6) numTwoDigit = 2;
                else numTwoDigit = 1;
            }
            if(pre != '*' && cur == '*') {
                if(pre - '0' == 1) numTwoDigit = 9;
                else if(pre - '0' == 2) numTwoDigit = 6;
            }
            dp[i] = (dp[i-1] * numOneDigit + dp[i-2] * numTwoDigit) % 1000000007;
            if(dp[i] == 0) return 0;
        }
        return dp[len];
    }


    //dp空间优化 只与前两个有关系 所以不需要dp数组来存，只需要两个变量
    //时间复杂度 O(n) 空间复杂度O(1)
    public int numDecodings2(String s) {
        char[] ss = s.toCharArray();
        if(ss[0] == '0') return 0;
        int dp_prepre = 1, dp_pre = 1;
        for(int i = 2; i <= ss.length; i++) {
            int cur = ss[i - 1], pre = ss[i - 2], numOneDigit = 0, numTwoDigit = 0;
            String twodigi = pre + "" + cur;
            if(cur >= '1' && cur <= '9') numOneDigit = 1;
            if(cur == '*') numOneDigit = 9;

            if(pre == '*' && cur == '*') numTwoDigit = 16;
            if(pre != '*' && cur != '*' && Integer.parseInt(twodigi) >= 10 && Integer.parseInt(twodigi) <= 26)  numTwoDigit = 1;
            if(pre == '*' && cur != '*') {
                if(cur >= '0' && cur <= '6') numTwoDigit = 2;
                else numTwoDigit = 1;
            }
            if(pre != '*' && cur == '*') {
                if(cur == '1') numTwoDigit = 9;
                else if(cur == '2') numTwoDigit = 6;
            }
            int temp = dp_pre;
            dp_pre = (dp_pre * numOneDigit + dp_prepre * numTwoDigit) % 1000000007;
            dp_prepre = temp;
            if(dp_pre == 0) return 0;
        }
        return dp_pre;
    }
}
