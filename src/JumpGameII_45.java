public class JumpGameII_45 {
    //Given an array of non-negative integers nums, you are initially positioned at the first
    //index of the array.

    //Each element in the array represents your maximum jump length at that position.
    //Your goal is to reach the last index in the minimum number of jumps.
    //You can assume that you can always reach the last index.

    //Example 1:
    //Input: nums = [2,3,1,1,4]
    //Output: 2
    //Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.

    //Example 2:
    //Input: nums = [2,3,0,1,4]
    //Output: 2

    //Constraints:
    //1 <= nums.length <= 3 * 104
    //0 <= nums[i] <= 105

    //反向贪心 双指针 找距离end最远的可以到达的end的点,将之作为end,重复一直到end=0 返回count
    public int jump(int[] nums) {
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        int count = 0;
        while (end > 0) {
            if(nums[start] + start >= end) {
                count++;
                end = start;
                start = 0;
            }else start++;
        }
        return count;
    }

    //维护当前能够到达的最大下标位置，记为边界。我们从左到右遍历数组，到达边界时，更新边界并将跳跃次数增加 1。
    //在遍历数组时，我们不访问最后一个元素，这是因为在访问最后一个元素之前，我们的边界一定大于等于最后一个位置，
    //否则就无法跳到最后一个位置了。如果访问最后一个元素，在边界正好为最后一个位置的情况下，我们会增加一次「不必要的跳跃次数」，
    //因此我们不必访问最后一个元素。

    //正向贪心: 线性时间复杂度内完成,依次遍历,维护most,在most范围内寻找里面最大的比most大的,更新most和跳跃位置
    public int jump2(int[] nums) {
        int len = nums.length;
        int most = 0;
        int end = 0;
        int count = 0;
        for(int i = 0; i < len-1; i++) {
            most = Math.max(most, i + nums[i]);
            if(i == end) {
                end = most;
                count++;
            }
        }
        return count;
    }
}
