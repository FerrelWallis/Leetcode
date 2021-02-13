public class JumpGame_55 {
    //Given an array of non-negative integers, you are initially positioned at the first index
    //of the array.

    //Each element in the array represents your maximum jump length at that position.
    //Determine if you are able to reach the last index.

    //Example 1:
    //Input: nums = [2,3,1,1,4]
    //Output: true
    //Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

    //Example 2:
    //Input: nums = [3,2,1,0,4]
    //Output: false
    //Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0,
    //which makes it impossible to reach the last index.

    //贪心算法 依次遍历数组中的每一个位置，并实时维护 最远可以到达的位置。
    //对于当前遍历到的位置 xx，如果它在 最远可以到达的位置 的范围内，那么我们就可以从起点通过若干次跳跃到达该位置，
    //因此我们可以用 x + \textit{nums}[x]x+nums[x] 更新 最远可以到达的位置

    public boolean canJump2(int[] nums) {
        int n = nums.length;
        int most=0;
        for(int i = 0; i < n; i++) {
            if(i <= most) {
                most = Math.max(i+nums[i], most);
                if(most>=n-1) return true;
            }
        }
        return false;
    }



    //1.思路：双指针（从后往前遍历）
    //从后往前遍历，判断当前位置的元素end，是否能被之前的元素start跳跃到；跳跃到则更新end = start；
    //继续对新的end元素进行判断，直到到达数组一个元素。

    //1）从数组最后一个元素nums[end]开始访问，判断之前的元素start能否达到end位置的元素;
    //2）如果能到达，则更新end为start，当前end继续进行上述操作；如果达不到end表明之前的元素都无法到达当前元素,返回false
    //3）遍历完所有元素，表明存在路径能到达末尾，返回true
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int end = len - 1;
        int start = end -1;
        while(start >= 0){
            if((nums[start]+start) >= end){
                end = start;
                start = end-1;
            }
            // 依次访问前面的元素，直到数组越界表明都找不到
            else if(--start == -1) return false;
        }
        return true;
    }



}
