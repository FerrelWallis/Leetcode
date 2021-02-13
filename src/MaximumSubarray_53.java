public class MaximumSubarray_53 {
    //1.动态规划 思路一 时间复杂度O(n^2)
    //用一个二维数组 dp[ i ] [ len ] 表示从下标 i 开始，长度为 len 的子数组的元素和。
    //这样长度是 len + 1 的子数组就可以通过长度是 len 的子数组去求，
    //dp [ i ] [ len + 1 ] = dp[ i ] [ len ] + nums [ i + len - 1 ]。
    //编写中，可以直接使用一维dp，无需存储每个len情况下的值，因为在len++过程中，每次dp[i]保存的都是len-1的值
    //用完直接将当前len的值保存到dp[i]，供len+1使用
    public int maxSubArray(int[] nums) {
        int[] dp=new int[nums.length];
        int max=Integer.MIN_VALUE;
        for(int len=1;len<=nums.length;len++){
            for(int i=0;i<nums.length-len;i++){
                dp[i]=dp[i]+nums[i+len-1];
                if(max<dp[i]) max=dp[i];
            }
        }
        return max;
    }


    //2. 动态规划 思路二 时间复杂度O(n)
    //用一个一维数组 dp [ i ] 表示以下标 i 结尾的子数组的元素的最大的和，也就是这个子数组最后一个元素是下边为 i 的元素，并且这个子数组是所有以 i 结尾的子数组中，和最大的。
    //这样的话就有两种情况，
    //如果 dp [ i - 1 ] < 0，那么 dp [ i ] = nums [ i ]。
    //如果 dp [ i - 1 ] >= 0，那么 dp [ i ] = dp [ i - 1 ] + nums [ i ]。
    public int maxSubArray2(int[] nums) {
        int max=nums[0];
        int dp=nums[0];
        for(int i=1;i<nums.length;i++){
            if(dp<0) dp=nums[i];
            else dp+=nums[i];
            max=Math.max(dp,max);
        }
        return max;
    }

}
