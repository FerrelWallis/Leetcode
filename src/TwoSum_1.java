import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TooManyListenersException;

public class TwoSum_1 {
    public static void main(String[] args) {
        TwoSum_1 test=new TwoSum_1();
        int[] nums={3,2,4};
        for(int i:test.twoSum(nums,6)) {
            System.out.println(i);
        }

    }

    //哈希表法
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< nums.length; i++) {
            if(map.containsKey(target - nums[i])) {
                return new int[] {map.get(target-nums[i]),i};
            }
            map.put(nums[i], i);
        }
        return new int[] {-1,-1};
    }


    //排序法+双指针可取，但改变了下标顺序，需要多次循环实现，获取下标比较麻烦
    public int[] twoSum(int[] nums, int target) {
        int[] ans=new int[2];
        Arrays.sort(nums);
        int left=0;
        int right=nums.length-1;
        while(left<right){
            System.out.println(nums[0]);
            System.out.println(nums[right]);
            if(nums[left]+nums[right]==target) {
                ans[0]=left;
                ans[1]=right;
                break;
            }else{
                if (nums[left] + nums[right] > target) right--;
                else left++;
            }
        }
        return ans;
    }
}
