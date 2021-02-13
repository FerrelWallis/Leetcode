public class DecodeWays_91 {
    //DecodeWays_91
    //A message containing letters from A-Z can be encoded into numbers using the following mapping:
    //
    //'A' -> "1"
    //'B' -> "2"
    //...
    //'Z' -> "26"
    //To decode an encoded message, all the digits must be mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "111" can have each of its "1"s be mapped into 'A's to make "AAA", or it could be mapped to "11" and "1" ('K' and 'A' respectively) to make "KA". Note that "06" cannot be mapped into 'F' since "6" is different from "06".
    //
    //Given a non-empty string num containing only digits, return the number of ways to decode it.
    //
    //The answer is guaranteed to fit in a 32-bit integer.
    //
    // 
    //
    //Example 1:
    //
    //Input: s = "12"
    //Output: 2
    //Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
    //Example 2:
    //
    //Input: s = "226"
    //Output: 3
    //Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
    //Example 3:
    //
    //Input: s = "0"
    //Output: 0
    //Explanation: There is no character that is mapped to a number starting with 0. The only valid mappings with 0 are 'J' -> "10" and 'T' -> "20".
    //Since there is no character, there are no valid ways to decode this since all digits need to be mapped.
    //Example 4:
    //
    //Input: s = "1"
    //Output: 1

    //动态规划 子问题：字母转换其实就是1-26组合的问题，看作数字问题，当前digits与前一个或前两个的关系 例如 1 -> 12 -> 121
    //状态定义：1 -> 12 有两种情况 空+12 和 1 + 2   同理  12 -> 121 有两种情况 1 + 21 和 12 + 1
    //        总结就是，每加一个digit时 当前的数量 = 前一个的数量（前提是当前加入的新digits本身有效） + 前两个的数量（前提是当前加入的新digits与前一个digits组成的两位数有效）
    //dp方程：dp[i] = (0 or dp[i-1]) + (0 or dp[i-2])
    //dp[i-1] 成立的情况，即1位数字成立的情况 新的digits >= 1 且 <= 9
    //dp[i-2] 成立的情况，即2位数字成立的情况 十位不能为 0 且 数字 >= 10 且 <= 26
    //时间复杂度O(n) 空间复杂度O(n)
    public int numDecodings(String s) {
        if(s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1; dp[1] = 1; //dp[0]s是为空的情况算作 1，当情况是 空+数字的时候，若数字成立算作 1
        for(int i = 2; i <= len; i++) {
            int cur = Integer.valueOf(s.substring(i - 1, i));
            int twodigits = Integer.valueOf(s.substring(i - 2, i));
            if(cur >= 1 && cur <= 9) dp[i] += dp[i-1];
            if(twodigits >= 10 && twodigits <= 26) dp[i] += dp[i-2]; //这里会用到dp[0]
            if(dp[i] == 0) return 0; //比如 1200 由 1 -> 12 -> 120 -> 1200, 120还是有成立的情况就是 1 + 20 ，但 1200 没有成立的情况，直接可以返回0
        }
        return dp[len];
    }

    //dp优化 只与前两个dp有关系 所以不需要数组来存dp，只需要两个变量
    //时间复杂度O(n) 空间复杂度O(1)
    public int numDecodings2(String s) {
        if(s == null || s.length() == 0 || s.charAt(0) == '0') return 0;
        int len = s.length();
        int dp_prepre = 1, dp_pre = 1;
        for(int i = 2; i <= len; i++) {
            int cur = Integer.valueOf(s.substring(i - 1, i)), twodigits = Integer.valueOf(s.substring(i - 2, i)), newpre = 0;
            if(cur >= 1 && cur <= 9) newpre += dp_pre;
            if(twodigits >= 10 && twodigits <= 26) newpre += dp_prepre; //这里会用到dp[0]
            dp_prepre = dp_pre;
            dp_pre = newpre;
            if(dp_pre == 0) return 0; //比如 1200 由 1 -> 12 -> 120 -> 1200, 120还是有成立的情况就是 1 + 20 ，但 1200 没有成立的情况，直接可以返回0
        }
        return dp_pre;
    }
}
