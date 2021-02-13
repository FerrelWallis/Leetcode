import java.util.Arrays;

public class HouseRobberII_213 {

    //You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
    //
    //Given a list of non-negative integers nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
    //
    // 
    //
    //Example 1:
    //
    //Input: nums = [2,3,2]
    //Output: 3
    //Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
    //Example 2:
    //
    //Input: nums = [1,2,3,1]
    //Output: 4
    //Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
    //Total amount you can rob = 1 + 3 = 4.
    //Example 3:
    //
    //Input: nums = [0]
    //Output: 0
    // 
    //
    //Constraints:
    //
    //1 <= nums.length <= 100
    //0 <= nums[i] <= 1000


    //动态规划 二维dp 第二维记录偷或不透的状态情况下最多的数量
    //
    //子问题： a[i] : 1 偷了的情况下, 1..(n-1) 的最大值  和 1 没偷的情况下，2..n 的最大值
    //状态定义： 1. 第1栋偷了, 求 1..n-1 的最大值
    //          2. 第1栋没偷，求 2..n 的最大值
    //          最大值计算方式一致 dp[i] = max(dp[i-1], dp[i-2] + nums[i])
    //dp方程： dp[i] = max(dp[i-1], dp[i-2] + nums[i])
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        int len = nums.length;
        int[][] dp = new int[len][2];
        dp[0][0] = nums[0];
        dp[0][1] = Math.max(nums[0],nums[1]);
        dp[1][1] = nums[1];
        dp[1][2] = Math.max(nums[1], nums[2]);
        for(int i1 = 2, i2 = 3; i1 < len - 1; i1++, i2++) {
            dp[0][i1] = Math.max(dp[0][i1 - 1], nums[i1] + dp[0][i1 - 2]);
            dp[1][i2] = Math.max(dp[1][i2 - 1], nums[i2] + dp[1][i2 - 2]);
        }
        return Math.max(dp[0][len - 2], dp[1][len - 1]);
    }


    //动态规划 优化可拆成两个一维dp， 再优化，可以用四个变量存储 prev1, cur1, prev2, cur2
    //
    //子问题： a[i] : 1 偷了的情况下, 1..(n-1) 的最大值  和 1 没偷的情况下，2..n 的最大值
    //状态定义： 1. 第1栋偷了, 求 1..n-1 的最大值
    //          2. 第1栋没偷，求 2..n 的最大值
    //          最大值计算方式一致 dp[i] = max(dp[i-1], dp[i-2] + nums[i])
    //dp方程： dp[i] = max(dp[i-1], dp[i-2] + nums[i])
    public int rob2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        int len = nums.length, prev1 = nums[0], cur1 = Math.max(nums[0], nums[1]), prev2 = nums[1], cur2 = Math.max(nums[1], nums[2]);
        for(int i1 = 2, i2 = 3; i1 < len - 1; i1++, i2++) {
            int temp1 = cur1, temp2 = cur2;
            cur1 = Math.max(cur1, prev1 + nums[i1]);
            cur2 = Math.max(cur2, prev2 + nums[i2]);
            prev1 = temp1;
            prev2 = temp2;
        }
        return Math.max(cur1, cur2);
    }


    //思路一致代码优化
    public int rob3(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        return Math.max(myRob(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                myRob(Arrays.copyOfRange(nums, 1, nums.length)));
    }
    private int myRob(int[] nums) {
        int pre = 0, cur = 0, tmp;
        for(int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }



}
