public class BurstBalloons_312 {
    //BurstBalloons_312
    //You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it
    //represented by an array nums. You are asked to burst all the balloons.
    //If you burst the ith balloon, you will get nums[left] * nums[i] * nums[right] coins. Here left and
    //right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
    //Return the maximum coins you can collect by bursting the balloons wisely.

    //Example 1:
    //Input: nums = [3,1,5,8]
    //Output: 167
    //Explanation:
    //nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
    //coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167

    //Example 2:
    //Input: nums = [1,5]
    //Output: 10
    //动态规划 子问题：遍历范围内所有的气球，将当前气球作为最后一个气球戳爆时，能获取的最大值。 最后求所有作为最后一个气球求出来的值中的最大值
    //               例如 [1,2,3,4,5] 假设遍历到 3 所求的就是 当3作为最后一个戳爆的气球，能获取的最大值。
    //               最后求就是所有气球里面最作为后一个戳爆时能获取到的最大值中最大的数
    //状态定义： left right边界范围内，
    //气球作为最后一个被戳破 = max(气球左侧所有气球能获取到的最大值) + max(气球右侧所有气球能获取到的最大值) + 当前nums当前气球 * nums（左边界 - 1） * nums（右边界 + 1）
    //dp方程： left ~ right范围内： dp[left][right] = max(val[i])   i : left ~ right
    //        val[i] = max(left ~ i-1) + max(i+1 ~ right) + nums[i] * nums[left-1] * nums[right+1]
    //时间复杂度：O(n^3)，其中 n 是气球数量。区间数为 n^2，区间迭代复杂度为 O(n)，最终复杂度为 O(n^2×n)=O(n^3)
    //空间复杂度：O(n^2)，其中 n 是气球数量。缓存大小为区间的个数。
    public int maxCoins(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        return maxCoins(nums, 0, nums.length - 1, dp);
    }

    private int maxCoins(int[] nums, int left, int right, int[][] dp) {
        if(left > right) return 0;
        if(dp[left][right] != 0) return dp[left][right];
        for(int i = left; i <= right; i++) {
            int maxleft = maxCoins(nums, left, i - 1, dp);
            int maxright = maxCoins(nums, i + 1, right, dp);
            int leftedge = 1, rightedge = 1;
            if(left - 1 >= 0) leftedge = nums[left - 1];
            if(right + 1 < nums.length) rightedge = nums[right + 1];
            int lastblast = nums[i] * leftedge * rightedge;
            dp[left][right] = Math.max(dp[left][right], maxleft + maxright + lastblast);
        }
        return dp[left][right];
    }


    //动态规划 依旧是自顶向下 转变思考方式(实际与上一个一样) 从气球序列里戳气球，改成往两边界为1的区间里填充气球，获取气球的累加值
    //状态定义： addBalloon(i,j)表示充满ij区间能获取到的最大值 枚举 ij边界中间的空隙mid  addBalloon(i,j) = max(addBalloon(i,mid) + addBalloon(mid,j) + i * j * nums[mid])
    //dp方程：addBalloon(i,j) = max(addBalloon(i, mid) + addBalloon(mid, j) + i * j * nums[mid]) mid: i+1 ~ j-1 if(i < j-1) 表明ij之间有空隙填充气球的情况下
    //       addBalloon(i,j) = 0  if(i >= j-1) 表面ij之间不能添加气球
    //时间复杂度：O(n^3)，其中 n 是气球数量。区间数为 n^2，区间迭代复杂度为 O(n)，最终复杂度为 O(n^2×n)=O(n^3)
    //空间复杂度：O(n^2)，其中 n 是气球数量。缓存大小为区间的个数。
    public int maxCoins2(int[] nums) {
        int[][] dp = new int[nums.length + 2][nums.length + 2];
        return addBalloon(nums,-1, nums.length, dp);
    }

    private int addBalloon(int[] nums, int i, int j, int[][] dp) {
        if(dp[i+1][j+1] != 0) return dp[i+1][j+1];
        if(i >= j - 1) return 0;
        int left = i, right = j;
        if(i == -1) left = 1;
        if(j == nums.length) right = 1;
        for(int mid = i + 1; mid < j; mid++) {
            int sum = left * right * nums[mid] + addBalloon(nums, i, mid, dp) + addBalloon(nums, mid, j, dp);
            dp[i+1][j+1] = Math.max(sum, dp[i+1][j+1]);
        }
        return dp[i+1][j+1];
    }


    //动态规划 自底向上 改变dp访问的顺序
    //因为  addBalloon(i,j) = max(addBalloon(i, mid) + addBalloon(mid, j) + i * j * nums[mid])
    //也就是 dp[i][j] = max(dp[i][mid] + dp[mid][j] + i * j * nums[mid]) mid: i ~ j  if(i > j - 1)
    //时间复杂度：O(n^3)，其中 n 是气球数量。区间数为 n^2，区间迭代复杂度为 O(n)，最终复杂度为 O(n^2×n)=O(n^3)
    //空间复杂度：O(n^2)，其中 n 是气球数量。缓存大小为区间的个数。
    public int maxCoins3(int[] nums) {
        int[] val = new int[nums.length + 2];
        val[0] = 1; val[nums.length + 1] = 1;
        for(int i = 0; i < nums.length; i++) val[i+1] = nums[i];
        int[][] dp = new int[nums.length + 2][nums.length + 2];
        for(int i = val.length - 3; i >= 0; i--) {
            for(int j = i + 2; j < val.length; j++) {
                for(int mid = i + 1; mid < j; mid++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][mid] + dp[mid][j] + val[i] * val[j] * val[mid]);
                }
            }
        }
        return dp[0][nums.length + 1];
    }
}
