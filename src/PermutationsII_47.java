import java.util.*;

public class PermutationsII_47 {
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        int[] visited = new int [nums.length];  //标记已有,默认为0
        List<Integer> path = new ArrayList<>();
        //和46差不多，需先排序，然后同层去重
        Arrays.sort(nums);
        fun(path,visited,nums);

        return result;
    }

    private void fun(List<Integer> path,int [] visited,int []  nums)
    {
        if(path.size()==nums.length)
        {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0;i<nums.length;i++ )
        {
            if( i>0 && nums[i]==nums[i-1])  //同层去重
            {
                if(visited[i-1]==1);        //判断上次是否用过，上次没用过，则这次这个可以用
                else continue;
            }
            if(visited[i]==1)continue;     //判断这次是否用过
            path.add(nums[i]);           //添加path并是标记visited标志置为1
            visited[i]=1;
            fun(path,visited,nums);
            //回溯
            visited[i]=0;
            path.remove(path.size()-1);
        }

    }




    public List<List<Integer>> ans=new LinkedList<>();
    public List<List<Integer>> permuteUnique2(int[] nums) {
        helper(new ArrayList<>(),0,nums);
        return ans;
    }

    private void helper2(List<Integer> cur, int level,int[] nums) {
        if(level==nums.length) {
            ans.add(new LinkedList<>(cur));
            return;
        }
        HashSet<Integer> set=new HashSet<>();
        for(int i=level;i<nums.length;i++){
            if(set.contains(nums[i])) continue;
            else {
                set.add(nums[i]);
                Collections.swap(cur,level,i);
                helper(cur,level+1,nums);
                Collections.swap(cur,level,i);
            }
        }
    }

    private void helper(List<Integer> cur, int level,int[] nums) {
        if(level==nums.length) {
            ans.add(new LinkedList<>(cur));
            return;
        }
        HashSet<Integer> set=new HashSet<>();
        for(int i=level;i<nums.length;i++){
            if(set.contains(nums[i])) continue;
            else {
                set.add(nums[i]);
                swap(nums, i, level);
                cur.add(nums[level]);
                helper(cur,level + 1, nums);
                cur.remove(cur.size() - 1);
                swap(nums, i, level);
            }
        }
    }

    public void swap(int[] nums,int i1,int i2){
        int temp=nums[i1];
        nums[i1]=nums[i2];
        nums[i2]=temp;
    }
}
