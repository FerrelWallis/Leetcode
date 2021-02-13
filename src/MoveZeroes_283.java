public class MoveZeroes_283 {
    public static void main(String[] args) {
        MoveZeroes_283 test=new MoveZeroes_283();
        int[] nums=new int[]{0,0,1,2,0,3,4,5,0,6};
        test.moveZeroes(nums);
        for(int i:nums)
            System.out.println(i);

    }

    //O(n),双指针，直接赋值0
    public void moveZeroes2(int[] nums) {
        int cur=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0) {
                nums[cur]=nums[i];
                if(i!=cur) nums[i]=0;
                cur++;
            }
        }
    }

    //循环O(n)，靠循环补齐剩余0
    public void moveZeroes(int[] nums) {
        int cur=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                nums[cur++]=nums[i];
            }
        }
        while(cur<nums.length){
            nums[cur++]=0;
        }
    }
}
