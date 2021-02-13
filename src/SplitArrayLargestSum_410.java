public class SplitArrayLargestSum_410 {
    //SplitArrayLargestSum_410
    //Given an array nums which consists of non-negative integers and an integer m, you can split the array into m
    //non-empty continuous subarrays.
    //Write an algorithm to minimize the largest sum among these m subarrays.

    //Example 1:
    //Input: nums = [7,2,5,10,8], m = 2
    //Output: 18
    //Explanation:
    //There are four ways to split nums into two subarrays.
    //The best way is to split it into [7,2,5] and [10,8],
    //where the largest sum among the two subarrays is only 18.

    //Example 2:
    //Input: nums = [1,2,3,4,5], m = 2
    //Output: 9

    //Example 3:
    //Input: nums = [1,4,4], m = 3
    //Output: 4

    //动态规划： 自底向上
    //子问题：分隔问题，假设求在（0~i）区间内分割k个的最大值的最小值，它与（0~j）区间内分割k-1个之间的关系. k-1 <= j <= i-1
    //状态定义：定义dp[i][k] i表示nums区间（0，i）   k表示分割数  rangeSum(x1,x2)表示 nums区间（x1,x2）总和
    //   在（0~i）区间内分割k个的最大值的最小值 dp[i][k]
    //   sub(j) = Max（其子串（0~j）区间内分隔k-1的最大值的最小值 dp[j][k-1] ，剩余的部分组成的第k个分割部分总和 rangeSum(j + 1,i) ）
    //   sub(j) 表示在子串（0~j）区间内分隔k-1基础上 形成的（0~i）区间内分割k 的最大值
    //   然后我们要求出 k <= j <= i-1范围内的所有子串（0~j）的最大值，取里面的最小
    //   所以 dp[i][k] = min{  sub(j)  (k-1 <= j <= i-1) }
    //dp方程：dp[i][k] = min{  max( dp[j][k-1], rangeSum（j,i）) (k-1 <= j <= i-1) }
    //       因为k只与上一个k-1有关，所以dp可以降为一维
    public int splitArray(int[] nums, int m) {
        int len = nums.length;
        //这是为了后面求rangeSum做准备  rangeSum（j+1, i） = rangeSum[i] - rangeSum[j]
        int[] rangeSum = new int[len];
        rangeSum[0] = nums[0];
        for(int i = 1; i < len; i++) rangeSum[i] = rangeSum[i-1] + nums[i];
        int[] dp = new int[len];
        //k == 1的时候 dp就是rangeSum
        System.arraycopy(rangeSum, 0, dp, 0, len);
        for(int k = 2; k <= m; k++) {
            for(int i = k-1; i < len; i++) { //区间（0，i）是从个数最小为k个开始，即下标k-1
                for(int j = k-2; j < i; j++) {
                    int rightpart = rangeSum[i] - rangeSum[j];
                    if(rightpart > dp[i]) break;
                    dp[i] = Math.min(dp[i], Math.max(dp[j], rightpart));
                }
            }
        }
        return dp[len-1];
    }


    //动态规划 优化
    public int splitArray2(int[] nums, int m) {
        int len = nums.length;
        //这是为了后面求rangeSum做准备  rangeSum（j+1, i） = rangeSum[i] - rangeSum[j]
        int[] rangeSum = new int[len];
        rangeSum[0] = nums[0];
        for(int i = 1; i < len; i++) rangeSum[i] = rangeSum[i-1] + nums[i];
        int[] dp = new int[len];
        //k == 1的时候 dp就是rangeSum
        System.arraycopy(rangeSum, 0, dp, 0, len);
        for(int k = 2; k <= m; k++) {
            //区间（0，i）是从个数最小为k个，即下标k-1,因为dp[i]只与上一层dp[j]有关系，所以 i，j 从大到小或从小到大没有影响
            //从大到小的好处是最后k==m的那一层，只需要dp[len-1]就可以，所以直接获得最后一层的dp[len-1] 然后break
            for(int i = len - 1; i >= k - 1; i--) {
                for(int j = i - 1; j >= k - 2; j--) {
                    int rightpart = rangeSum[i] - rangeSum[j];
                    if(rightpart > dp[i]) break;  //因为dp[i]要获取最小的最大值，rightpart已经比上一个dp[i]大的时候，后面的只会更大，没必要继续直接break
                    dp[i] = Math.min(dp[i], Math.max(dp[j], rightpart));
                }
                if(k == m) break;
            }
        }
        return dp[len-1];
    }



    //二分查找 关键词非负整数 连续 可以看一下如何打造单调性，可以进行二分，类似题目：leetcode875、12、1482、1011、1552
    //明确一点就是subarray各自和的最大值的最小值范围: 大于等于nums中的最大数，且小于等于所有值总和 min(nums) <= ans <= sum(nums)
    //说明ans是单调递增的，那么可以换一个角度，二分查找ans
    //假设当前left,right为ans边界， mid = left + (right - left) / 2，
    //将mid作为subarray各自和的最大值，尝试分割，确保每一割都小于mid，查看分割次数k
    //如果 k > m 割多了，说明 mid太小了， 取区间（mid +1, right)
    //如果 k <= m 正好或者割少了，说明mid太大，取区间（left, mid）
    //保留mid是因为，当k割出来正好为m的时候，我们尝试继续收缩ans，因为要取最小值
    public int splitArray3(int[] nums, int m) {
        int len = nums.length, left = 0, right = 0;
        for(int i = 0; i < len; i++) {
            right += nums[i];
            left = Math.max(left, nums[i]);
        }
        while (left < right) {
            int mid = left + (right - left) / 2;
            int k = split(nums, mid);
            if(k > m) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    //思路参考63最大子序和题目如果累加小于maxsum，继续累加。如果大于maxsum，另起炉灶，并且k++
    private int split(int[] nums, int maxsum) {
        int k = 1;
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            if(sum + nums[i] <= maxsum) sum += nums[i];
            else {
                sum = nums[i];
                k++;
            }
        }
        return k;
    }
}
