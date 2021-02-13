import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Subsets_78 {
    //跟全排列一样47 1. 回溯算法(递归)
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        helper(ans,new ArrayList<>(),nums,0);
        return ans;
    }

    private void helper(List<List<Integer>> ans, List<Integer> cur, int[] nums, int level) {
        ans.add(new ArrayList<>(cur));
        for(int i=level;i<nums.length;i++) {
            cur.add(nums[i]);
            helper(ans,cur,nums,i+1);
            cur.remove(cur.size()-1);
        }
    }

    //2.迭代实现
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        ans.add(new ArrayList<>());
        for(int num:nums){
            int len=ans.size();
            for(int j=0;j<len;j++){
                List<Integer> newlist=new ArrayList<>(ans.get(j));
                newlist.add(num);
                ans.add(newlist);
            }
        }
        return ans;
    }


}
