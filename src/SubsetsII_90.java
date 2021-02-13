import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII_90 {

    //回溯递归 visited数组实现
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans=new ArrayList<>();
        helper(nums,ans,new ArrayList<>(),0);
        return ans;
    }

    private void helper(int[] nums, List<List<Integer>> ans, ArrayList<Integer> cur, int level) {
        ans.add(new ArrayList<>(cur));
        for(int i=level;i<nums.length;i++){
            if(i>level&&nums[i]==nums[i-1]) continue;
            cur.add(nums[i]);
            helper(nums,ans,cur,i+1);
            cur.remove(cur.size()-1);
        }
    }


}
