public class HouseRobber_198 {

    //house robber1
    //You are a professional robber planning to rob houses along a street.
    //Each house has a certain amount of money stashed, the only constraint stopping you
    //from robbing each of them is that adjacent houses have security system connected and
    //it will automatically contact the police if two adjacent houses were broken into on the same night.

    //Given a list of non-negative integers representing the amount of money of each house,
    //determine the maximum amount of money you can rob tonight without alerting the police.

    //Example 1:
    //Input: nums = [1,2,3,1]
    //Output: 4
    //Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
    //Total amount you can rob = 1 + 3 = 4.


    //Example 2:
    //Input: nums = [2,7,9,3,1]
    //Output: 12
    //Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
    //Total amount you can rob = 2 + 9 + 1 = 12.

    //动态规划 二维dp 第二维记录偷或不透的状态情况下最多的数量
    //
    //子问题： a[i] : 0..i 偷了或没偷情况下的最大值 a[n - 1]
    //状态定义： 第 i 栋偷或没偷 0表示没偷， 1表示偷了 a[i][0]  a[i][1]
    //dp方程： 这栋不偷 a[i][0] = max(a[i-1][0], a[i - 1][1])
    //        这栋偷了 a[i][1] = a[i-1][0] + nums[i]
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int len = nums.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        for(int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
        }
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }

    // 动态规划 优化 一维dp
    //子问题： a[i] : 0..i 能偷到的最大值 a[n - 1]  ，存的都是到这栋楼的情况下能偷的最大值
    //状态空间定义： 如果 i 栋偷，那么df[i-1]肯定不看，取df[i-2]的最大值 则 df[i] = nums[i] + df[i-2]
    //             如果 i 没偷，那么肯定取df[i-1]的值，因为df[i-1]时i-1楼能偷的最大值，则 df[i] = df[i-1]
    //dp方程： df[i] = max(df[i-1], nums[i] + df[i-2])
    public int rob2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for(int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[len - 1];
    }

    //动态规划 再次空间优化
    //因为每次只用到 df[i-1] 和 df[i-2] 的值， 所以不用一维数组，仅用 prev 和 cur两个值保存信息
    //子问题： a[i] : 0..i 能偷到的最大值 a[n - 1]  ，存的都是到这栋楼的情况下能偷的最大值
    //状态空间定义： 如果 i 栋偷，那么cur肯定不看，取prev的最大值 则 prev = cur, cur = nums[i] + prev
    //             如果 i 没偷，那么肯定取cur的值，cur是i-1楼能偷的最大值，则 prev = cur, cur = cur
    //dp方程： prev = cur , cur = max(cur, nums[i] + prev)
    public int rob3(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int len = nums.length,prev = nums[0], cur = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            int temp = cur;
            cur = Math.max(cur, prev + nums[i]);
            prev = temp;
        }
        return cur;
    }
}
