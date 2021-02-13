public class AAA_Model_BinaryDivision {
    //条件:1. 单调性 2. 上下界 bounded 3. 能使用索引 index accessible
    //二分查找模板
    public int twosidedivision(int[] nums,int target) {
        int left = 0, right = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if(target == mid) return mid;
            else if(target < mid) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }
}
