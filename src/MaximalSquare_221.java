public class MaximalSquare_221 {
    //MaximalSquare_221
    //Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and
    //return its area.

    //Example 1:
    //Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
    //Output: 4

    //Example 2:
    //Input: matrix = [["0","1"],["1","0"]]
    //Output: 1

    //Example 3:
    //Input: matrix = [["0"]]
    //Output: 0

    //Constraints:
    //m == matrix.length
    //n == matrix[i].length
    //1 <= m, n <= 300
    //matrix[i][j] is '0' or '1'.

    //动态规划：子问题：最大正方形面积，把当前访问到的位置作为正方形右下角（前提是当前为1），能构成的正方形最大面积
    //状态定义：如果前 matrix[i][j]为1，则可以作为正方形右下角，基础面积为1. dp[i][j] 表示以 i,j为右下角能构成的最大正方形面积的边长
    //        如果当前位置（i,j）, matrix[i,j] == 1 假设左上dp = k^2
    //        如果左或上的任意一个dp < 左上的dp 则说明 dp[i, (j-k) ~ (j-1)] 或  dp[(i-k) ~ (i-1), j]中有不是1的 取左或上中较短的一边
    //        否则 就说明 dp[i, (j-k) ~ (j-1)] 或  dp[(i-k) ~ (i-1), j]都为1 则取左上
    //dp方程：满足条件的话 dp[i][j] = (tt + 1)^2
    // 条件：matrix[i][j] == 1  :
    //              如果 dp[i][j-1] < dp[i-1][j-1] || dp[i-1][j] < dp[i-1][j-1]  tt = min(dp[i][j-1], dp[i-1][j])
    //              否则 tt = min(dp[i-1][j-1])
    public static void main(String[] args) {
        MaximalSquare_221 test = new MaximalSquare_221();
        char[][] matrix = new char[2][2];
        matrix[0][0] = '0'; matrix[0][1] = '1'; matrix[1][0] = '1'; matrix[1][1] = '0';
        test.maximalSquare(matrix);
    }

    public int maximalSquare(char[][] matrix) {
        int row = matrix.length, col = matrix[0].length, max = 0;
        int[][] dp = new int[row][col];
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[i][j] = matrix[i][j] - '0';
                System.out.println(dp[i][j]);
                if(dp[i][j] == 1) max = 1;
            }
        }

        for(int i = 1; i < row; i++) {
            for(int j = 1; j < col; j++) {
                if(dp[i][j] != 1) continue;
                double x = 0;
                x = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                dp[i][j] = (int)(x+1);
                max = Math.max(max, dp[i][j]);
            }
        }
        return max*max;
    }


    //代码优化
    public int maximalSquare2(char[][] matrix) {
        int row = matrix.length, col = matrix[0].length, max = 0;
        int[][] dp = new int[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                dp[i][j] = matrix[i][j] - '0';
                max = Math.max(max, dp[i][j]);
                if(dp[i][j] != 1 || i == 0 || j == 0) continue;
                double x = 0;
                x = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                dp[i][j] = (int)(x+1);
                max = Math.max(max, dp[i][j]);
            }
        }
        return max*max;
    }
}
