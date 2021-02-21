public class CountingBits_338 {
    //Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
    //
    //Example 1:
    //
    //Input: 2
    //Output: [0,1,1]
    //Example 2:
    //
    //Input: 5
    //Output: [0,1,1,2,1,2]
    //Follow up:
    //
    //It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
    //Space complexity should be O(n).
    //Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.

    //1. 使用bitcount 虽然时间复杂度为O(N) 但是题目不建议使用语言中类似bitcount已经写好的计算1的方式
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        for(int i = 0; i <= num; i++) {
            ans[i] = Integer.bitCount(i);
        }
        return ans;
    }

    //2. 写个循环遍历32位数1 时间复杂度O(n*sizeof(integer))
    public int[] countBits2(int num) {
        int[] ans = new int[num + 1];
        for(int i = 0; i <= num; i++) {
            int count = 0;
            int cur = i;
            for(int j = 0; j < 32; j++) {
                if((cur & 1) == 1) count++;
                cur >>= 1;
            }
            ans[i] = count;
        }
        return ans;
    }

    //3.dp方程 + 位运算：（利用奇偶数的二进制特性）
    // 子问题：偶数最后一位肯定是0，奇数最后一位必定是1。当前数字x与x/2之间的关系
    // 状态转移：进行>>1（相当于除2）操作时，偶数的1的数量不变，即x中1的数量 = x/2中1的数量，
    //          奇数会丢失1个1，即x中1的数量 = x/2中1的数量 + 1
    // dp方程：dp[x] = dp[x/2] + 1 （x为奇数）
    //        dp[x] = dp[x/2]  (x为偶数)
    //        初始状态 dp[0] = 0
    // 根据奇偶性是否加一直接用位运算可以实现 x & 1 看最后一位是否为1来判断奇偶性，顺便 + 1
    // 时间复杂度O(n)
    public int[] countBits3(int num) {
        int[] dp = new int[num + 1];
        for(int i = 1; i <= num; i++) {
            dp[i] = dp[(i >> 1)] + (i & 1); //奇数二进制第0位有1，右移操作后会丢失一个1，判断奇偶性的同时进行补1操作
        }
        return dp;
    }

    //4. dp方程 ： 找规律
    //子问题：(0~1) 与（2~3）的关系是（2~3）比（0~1）在前面多加了一个1，
    //      （0~3）与（4~7）的关系是（4~7）比（0~3）在前面多了一个1，同理往下（0~7）->（8~15）,（0~15）->（16~31）
    //状态转移：由上面的枚举可以得出从（0~1）开始每2^x次方一个周期，
    public int[] countBits4(int num) {
        int[] dp = new int[num + 1];
        for(int i = 2; i <= num; i++) {
            dp[i] = dp[(i >> 1)] + (i & 1); //奇数二进制第0位有1，右移操作后会丢失一个1，判断奇偶性的同时进行补1操作
        }
        return dp;
    }


}
