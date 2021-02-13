public class MaximumProductSubarray_152 {
    //152. Maximum Product Subarray
    //Given an integer array nums, find the contiguous subarray within an array
    //(containing at least one number) which has the largest product.

    //Example 1:
    //Input: [2,3,-2,4]
    //Output: 6
    //Explanation: [2,3] has the largest product 6.

    //Example 2:
    //Input: [-2,0,-1]
    //Output: 0
    //Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
    //动态规划
    public int maxProduct(int[] nums) {
        int len = nums.length;
        int[] dpmin=new int[len];
        int[] dpmax=new int[len];
        int max = nums[0];
        dpmax[0] = nums[0];
        dpmin[0] = nums[0];
        for (int i = 1; i < len; i++) {
            dpmax[i] = Math.max(nums[i], Math.max(dpmax[i - 1] * nums[i], dpmin[i - 1] * nums[i]));
            dpmin[i] = Math.min(nums[i], Math.min(dpmax[i - 1] * nums[i], dpmin[i - 1] * nums[i]));
            max = Math.max(max, dpmax[i]);
        }
        return max;
    }

    //动态规划 空间优化
    public int maxProduct2(int[] nums) {
        int len = nums.length;
        int dpmin = nums[0], dpmax = nums[0], max = nums[0];
        for(int i = 1; i < len; i++) {
            int mx = dpmax, mn = dpmin;
            //这里要注意先把上一轮max,min保存一下，后面dpmax会直接改变，判断dpmin时再使用dpmax会出错
            dpmax = Math.max(nums[i], Math.max(mx * nums[i], mn * nums[i]));
            dpmin = Math.min(nums[i], Math.min(mx * nums[i], mn * nums[i]));
            max = Math.max(max, dpmax);
        }
        return max;
    }
}
