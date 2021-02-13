import java.util.*;

public class PerfectSquares_279 {
    //279. Perfect Squares
    //Given a positive integer n, find the least number of perfect square numbers
    //(for example, 1, 4, 9, 16, ...) which sum to n.

    //Example 1:
    //Input: n = 12
    //Output: 3
    //Explanation: 12 = 4 + 4 + 4.

    //Example 2:
    //Input: n = 13
    //Output: 2
    //Explanation: 13 = 4 + 9.

    //dfs
    public int ans = Integer.MAX_VALUE;
    public int numSquares(int n) {
        dfs(n, 0);
        return ans;
    }

    public void dfs(int left, int num) {
        int pow = (int) Math.sqrt(left);
        if(pow * pow == left) {
            ans = Math.max(ans, num + 1);
            return;
        }
        for(int i = pow; i >= 1; i++) {
            dfs(left - pow * pow, num + 1);
        }
    }


    //动归  子问题： 枚举最后一个可能的平方数，n的最小数量 = min( （n-枚举的平方数）的最小数 +1)
    //      状态定义： n的最小平方数数量 = min(（n - 某个平方数）的最小数量 + 1 )
    //      状态转移方程： dp[n] = min(dp[x]+1) x: 1 ~ (n-i >= 0) 的平方数 eg:1、4、9、16、25
    //自底向上
    public int numSquares2(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= n; i++) {
            for(int j = (int) Math.sqrt(i); j >= 1; j--) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }


    //bfs
    public int numSquares3(int n) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> reviewedSet = new HashSet<>();
        if(n > 0) queue.offer(n);
        int level = 0;
        while(!queue.isEmpty()){
            level++;
            int size = queue.size();
            for(int i=0; i<size; i++) {
                int val = queue.poll();
                if(!reviewedSet.add(val)) continue;
                for(int j=1; j<=Math.sqrt(val); j++){
                    if(val-(j*j) == 0) return level;
                    queue.offer(val-(j*j));
                }
            }
        }
        return level;
    }



    // 数学原理 拉格朗日四平方理论: 所有正整数可以写成最多由四个完全平方数相加的形式，所以答案只可能是1、2、3、4。
// every positive integer can be written as the sum of at most four squares. answer to this question
// can be 1 or 2 or 3 or 4 only.
    public int numSquares4(int n) {
        if(n == 0) return 0;
        // 由 1 个完全平方数组成的情况:
        if(isSqrt(n)) return 1;
        // 由 2 个完全平方数组成的情况：n减去一个完全平方数后，还是一个完全平方数
        for(int i=1; i<=Math.sqrt(n); i++){
            if(isSqrt(n-(i*i))) return 2;
        }
        // 由 3 个完全平方数组成的情况：n不能组成4^k(8m+7)的形式，
        // 因为前面已经判断过1、2，所以之后能够满足4^k(8m+7)的一定是4， 而剩余的就是3
        while(n%4 ==0) n=n/4;
        if(n%8 == 7) return 4;
        // case 3
        return 3;
    }
    private boolean isSqrt(int n){
        int sqrt = (int)Math.sqrt(n);

        return n-(sqrt*sqrt)==0;
    }
}
