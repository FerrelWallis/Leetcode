import java.util.ArrayList;
import java.util.List;

public class MajorityElementII_229 {

    /*如果至多选一个代表，那他的票数至少要超过一半（⌊ 1/2 ⌋）的票数；
    如果至多选两个代表，那他们的票数至少要超过 ⌊ 1/3 ⌋ 的票数；
    如果至多选m个代表，那他们的票数至少要超过 ⌊ 1/(m+1) ⌋ 的票数。
    所以以后碰到这样的问题，而且要求达到线性的时间复杂度以及常量级的空间复杂度，直接套上摩尔投票法

    任意多候选人问题，都可以用摩尔投票法
    */

    //这题的潜藏条件就是最多选两个，因为满足至少超过1/3的票数，再所有后选中最多只有两个
    //第一步：投票选出最可能的两个 第二步：查看是否大于1/3
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans=new ArrayList<>();
        if(nums==null || nums.length==0) return ans;
        int can1=nums[0],count1=0;
        int can2=nums[0],count2=0;
        //投票阶段
        for(int num:nums) {
            if(num==can1) {
                count1++;
                continue;
            }else if(num==can2) {
                count2++;
                continue;
            }else if(count1==0) {
                can1=num;
                count1++;
                continue;
            }else if(count2==0) {
                can2=num;
                count2++;
                continue;
            }else {
                count1--;
                count2--;
            }
        }

        count1=0;
        count2=0;
        //确认候选人合法性
        for(int num:nums){
            if(num==can1) count1++;
            else if(num==can2) count2++;
        }

        if(count1>nums.length/3) ans.add(can1);
        if(count2>nums.length/3) ans.add(can2);
        return ans;
    }


}