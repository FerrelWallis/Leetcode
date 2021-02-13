import java.util.*;

public class Permutations_46 {
    //1.用数组记录访问
    public int[] visited;
    public List<List<Integer>> ans2=new LinkedList<>();
    public List<List<Integer>> permute2(int[] nums) {
        visited=new int[nums.length];
        helper2(new LinkedList<>(),visited,nums);
        return ans2;
    }

    private void helper2(List<Integer> cur, int[] visited,int[] nums) {
        if(cur.size()==nums.length) ans2.add(new LinkedList<>(cur));
        for(int i=0;i<nums.length;i++){
            if(visited[i]==1) continue;
            cur.add(nums[i]);
            visited[i]=1;
            helper2(cur,visited,nums);
            //回溯
            cur.remove(cur.size()-1);
            visited[i]=0;
        }
    }



    //2.直接list交换swap
    public int[] nums;
    public List<List<Integer>> ans=new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        this.nums=nums;
        List<Integer> cur=new LinkedList<>();
        for(int num:nums) cur.add(num);
        helper(cur,0);
        return ans;
    }

    private void helper(List<Integer> cur, int first) {
        if(first==nums.length) ans.add(new LinkedList<>(cur));
        for(int i=first;i<nums.length;i++){
            Collections.swap(cur,first,i);
            helper(cur,first+1);
            Collections.swap(cur,first,i);
        }
    }
}
