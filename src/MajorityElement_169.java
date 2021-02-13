import java.util.Arrays;
import java.util.HashMap;

public class MajorityElement_169 {
    //哈希表 时间复杂度O（n）
    public int majorityElement(int[] nums) {
        int ans=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int num:nums){
            int last=map.getOrDefault(num,0);
            map.put(num,last+1);
            if(last+1>nums.length/2) {
                ans=num;
                break;
            }
        }
        return ans;
    }

    //排序 时间复杂度O（nlogn）
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    //投票 原理：数量大于n/2
    public int majorityElement3(int[] nums) {
        int ans=0;
        int count=0;
        for(int num:nums){
            if(count==0) {
                ans=num;
                count+=1;
            }else if(num==ans) count+=1;
            else count-=1;
        }
        return ans;
    }


}