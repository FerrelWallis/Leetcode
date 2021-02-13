public class SearchinRotatedSortedArray_33 {
    //You are given an integer array nums sorted in ascending order, and an integer target.
    //
    //Suppose that nums is rotated at some pivot unknown to you beforehand (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
    //
    //If target is found in the array return its index, otherwise, return -1.
    //
    //
    //
    //Example 1:
    //
    //Input: nums = [4,5,6,7,0,1,2], target = 0
    //Output: 4
    //
    //Example 2:
    //
    //Input: nums = [4,5,6,7,0,1,2], target = 3
    //Output: -1
    //
    //Example 3:
    //
    //Input: nums = [1], target = 0
    //Output: -1
    //
    //
    //
    //Constraints:
    //
    //    1 <= nums.length <= 5000
    //    -10^4 <= nums[i] <= 10^4
    //    All values of nums are unique.
    //    nums is guranteed to be rotated at some pivot.
    //    -10^4 <= target <= 10^4

    //暴力遍历 O(n)
    public int search(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == target) return i;
        }
        return -1;
    }

    //二分查找 0(logn)
    public int search2(int[] nums, int target) {
        if(nums.length == 0) return -1;
        if(nums.length == 1) return (target == nums[0])? 0 : -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            //当nums仅有两个数的时候需要特别注意，必定是降序的，3，2
            //这个时候 target若是3，直接在target == nums[mid]判断输出结果
            //如果target是2，判断nums[mid] 》= nums[0]很重要
            //如果将nums[mid] 《= nums[0] 放在一起判断，则右边不符合单调递增（3，2）
            if(target == nums[mid]) return mid;
            if(nums[mid] >= nums[0]) { //说明左边是有序递升，转折点在右侧
                if(target >= nums[0] && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            } else { //说明右边是有序递升，转折点在左侧
                if(target > nums[mid] && target <= nums[nums.length - 1]) left = mid + 1;
                else right = mid - 1;
            }
        }
        return -1;
    }
}
