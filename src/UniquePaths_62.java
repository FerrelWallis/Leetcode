public class UniquePaths_62 {

    //A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
    //
    //The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
    //
    //How many possible unique paths are there?

    //Example 1:
    //
    //Input: m = 3, n = 7
    //Output: 28
    //
    //Example 2:
    //
    //Input: m = 3, n = 2
    //Output: 3
    //Explanation:
    //From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
    //1. Right -> Down -> Down
    //2. Down -> Down -> Right
    //3. Down -> Right -> Down
    //
    //Example 3:
    //
    //Input: m = 7, n = 3
    //Output: 28
    //
    //Example 4:
    //
    //Input: m = 3, n = 3
    //Output: 6


    //1. 分治 动态规划（自顶向下）
    public int uniquePaths1(int m, int n) {
        return recur(0, 0, m, n);
    }
    public int recur(int row, int col, int m, int n){
        if(row == n - 1) return 1;
        else if(col == m-1) return 1;
        else return recur(row + 1, col, m, n) + recur(row, col + 1, m, n);
    }


    //3. 动态规划（自底向上）
    public int uniquePaths2(int m, int n) {
        int [][]dp =   new int[m][n];
        for(int i=1; i<m; i++) dp[i][0] = 1;
        for(int i=1; i<n; i++) dp[0][i] = 1;
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[i][j] = dp[i+1][j] + dp[i][j+1];
            }
        }
        return dp[m-1][n-1];
    }

    //4. 动态规划 自底向上优化 节省空间
    //因为只用到了上一层和左侧数据，因此只需保留上一层数据即可
    public int uniquePaths3(int m, int n) {
        int[] dp=new int[n];
        for(int i=0; i<n; i++) dp[i] = 1;
        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[j] = dp[j] + dp[j-1];
            }
        }
        return dp[n-1];
    }

    //5. 数学公式 组合
    //m*n的矩阵 从左上角走到左下角，向右走的步数一定为n-1，向下走的步数一定为m-1，走路总步数一定为m+n-2。
    //问题就是m+n-2的步数里，哪几步为n-1的问题。C（n-1）（m+n-2） = (m+n-2)! / ((m-1)!(n-1)!)
    public int uniquePaths5(int m, int n) {
        int N = n + m - 2;
        double res = 1;
        for (int i = 1; i < m; i++)
            res = res * (N - (m - 1) + i) / i;
        return (int) res;
    }
}
