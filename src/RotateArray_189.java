public class RotateArray_189 {

    public static void main(String[] args) {
        int[] nums=new int[]{1,2,3,4,5,6,7,8,9};
        RotateArray_189 test=new RotateArray_189();
        test.rotate2(nums,3);
        for(int i:nums){
            System.out.println(i);
        }

    }


    //循环替换 时间复杂度O(0) 空间复杂度O(1)
    public void rotate(int[] nums, int k) {
        k=k%nums.length;
        int count=0;
        for(int start=0;count<nums.length;start++){
            int cur=start;
            int curcon=nums[cur];
            do{
                int des=(cur+k)%nums.length;
                int temp=nums[des];
                nums[des]=curcon;
                curcon=temp;
                cur=des;
                count++;
            }while(cur!=start);
        }
    }


    //反转法 1.整个反转 2. 反转前k个字符  3. 反转后num.length-k个字符
    // 时间复杂度O(n) 空间复杂度O(1)
    public void rotate2(int[] nums, int k) {
        k=k%nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }

    private void reverse(int[] nums, int start, int end) {
        int left=start;
        int right=end;
        while (left<right){
            int temp=nums[left];
            nums[left++]=nums[right];
            nums[right--]=temp;
        }
    }

    //3. 新建数组保存 时间复杂度O(n) 空间复杂度O(n)
}
