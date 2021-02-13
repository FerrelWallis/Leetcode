public class UniquePathsII_63 {
    //A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
    //
    //The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
    //
    //Now consider if some obstacles are added to the grids. How many unique paths would there be?
    //
    //An obstacle and space is marked as 1 and 0 respectively in the grid.
    //
    //
    //
    //Example 1:
    //
    //Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
    //Output: 2
    //Explanation: There is one obstacle in the middle of the 3x3 grid above.
    //There are two ways to reach the bottom-right corner:
    //1. Right -> Right -> Down -> Down
    //2. Down -> Down -> Right -> Right
    //
    //Example 2:
    //
    //Input: obstacleGrid = [[0,1],[0,0]]
    //Output: 1
    //同62一样

    //1.暴力递归
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        return recur2(0,0,row,col);
    }

    private int recur2(int i, int j, int row, int col) {
        if(i == row - 1 || j == col - 1) return 1;
        else return recur2(i - 1, j, row, col) + recur2(i, j - 1, row, col);
    }

    //2.动态规划（自顶向下） + 记忆化搜索
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        return recur(0,0,row,col,dp);
    }

    private int recur(int i, int j, int row, int col, int[][] dp) {
        if(dp[i][j] != 0) return dp[i][j];
        else if(i == row - 1 || j == col - 1) {
            dp[i][j] = 1;
            return 1;
        } else return recur(i - 1, j, row, col, dp) + recur(i, j - 1, row, col, dp);
    }

    //3. 动态规划（自底向上）
    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[] dp = new int[col];
        dp[0] = (obstacleGrid[0][0] == 1)? 0 : 1;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                //dp[0]这个位置，只需要判断obstaclegrid上是否为1，为一初始化为0.
                //否则直接继承上层dp[0]的值. 因为dp[0]是最左，只需要上面的值就行
                if(obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                if (j>=1) dp[j] += dp[j-1];
            }
        }
        return dp[col-1];
    }
}
