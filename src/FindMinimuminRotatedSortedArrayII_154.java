import java.util.Arrays;

public class FindMinimuminRotatedSortedArrayII_154 {
    //154
//Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
//Find the minimum element. The array may contain duplicates.

//Example 1:
//Input: [1,3,5]
//Output: 1

//Example 2:
//Input: [2,2,2,0,1]
//Output: 0

//Note:
    //    This is a follow up problem to Find Minimum in Rotated Sorted Array.
//    Would allow duplicates affect the run-time complexity? How and why?

    //暴力
    public int findMin2(int[] nums) {
        for(int i=1;i<nums.length;i++) {
            if(nums[i] < nums[i-1]) return nums[i];
        }
        return nums[0];
    }

    //排序
    public int findMin3(int[] nums) {
        Arrays.sort(nums);
        return nums[0];
    }

    //二分
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] < nums[right]) {
                right = mid; //mid有可能是pivot，所以不减一
            } else if(nums[mid] > nums[right]) { //mid必定不是pivot,所以加一
                left = mid + 1;
            } else {
                right--;
            }
        }
        return nums[left];
    }
}
