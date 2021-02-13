public class FibonacciNumber_509 {

    //1. 暴力递归  重复操作很多 20对19，18  而19对18，17  18对17，16 其中18，17就重复了两次
    public int fib(int N) {
        if(N==1||N==2) return N;
        else return fib(N-1)+fib(N-2);
    }

    //2.动态规划 自底向上
    public int fib2(int N) {
        if(N==0) return 0;
        int[] dp=new int[N+1];
        dp[0]=0;dp[1]=1;
        for(int i=2;i<=N;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[N];
    }
}
