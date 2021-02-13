import java.util.List;

public class Triangle_120 {

    //120. Triangle
    //Given a triangle, find the minimum path sum from top to bottom.
    //Each step you may move to adjacent numbers on the row below.
    //For example, given the following triangle
    //[
    //     [2],
    //    [3,4],
    //   [6,5,7],
    //  [4,1,8,3]
    //]
    //The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
    //Note: Bonus point if you are able to do this using only O(n) extra space,
    //where n is the total number of rows in the triangle.

    //1.暴力递归 （分治，动态规划自顶向下）
    public int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size();
        return recur(0,0, n, triangle);
    }

    private int recur(int level, int index, int n, List<List<Integer>> triangle) {
        int cur = triangle.get(level).get(index);
        if(level == n-1) return cur;
        return Math.min(recur(level+1, index, n, triangle),recur(level + 1, index + 1, n, triangle)) + cur;
    }


    //2. 动态规划（自顶向下） + 记忆化搜索
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        return recur2(0,0, n, triangle, new int[n][n]);
    }

    private int recur2(int level, int index, int n, List<List<Integer>> triangle, int[][] dp) {
        int cur = triangle.get(level).get(index);
        if(level == n-1) {
            dp[level][index] = cur;
            return cur;
        }
        if (dp[level][index] != 0) return dp[level][index];
        else {
            dp[level][index] = Math.min(recur(level+1, index, n, triangle),recur(level + 1, index + 1, n, triangle)) + cur;
            return dp[level][index];
        }
    }

    //3. 动态规划（自底向上）
    public int minimumTotal3(List<List<Integer>> triangle) {
        int row = triangle.size();
        int[][] dp = new int[row][row];
        for(int level = row - 1; level >= 0; level--) {
            for(int j = 0; j < level; j++) {
                if(level == row - 1) dp[level][j] = triangle.get(level).get(j);
                else dp[level][j] = Math.min(dp[level - 1][j],dp[level - 1][j + 1]) + triangle.get(level).get(j);
            }
        }
        return dp[0][0];
    }


    //4. 动态规划（自底向上） + 空间优化
    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int dp[] = new int[row+1];
        for(int level = row-1; level >= 0; level--) {
            for(int j = 0; j <= level; j++) {
                dp[j] = Math.min(dp[j],dp[j+1]) + triangle.get(level).get(j);
            }
        }
        return dp[0];
    }
}
