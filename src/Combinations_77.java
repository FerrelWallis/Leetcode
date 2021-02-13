import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Combinations_77 {
    public List<List<Integer>> ans=new LinkedList<>();
    public int n;
    public int k;
    public List<List<Integer>> combine(int n, int k) {
        this.n=n;
        this.k=k;
        helper(new LinkedList<>(),1);
        return ans;
    }

    public void helper(LinkedList<Integer> cur,int first){
        if(cur.size()==k) {
            //这里new 一个linkedlist原因是在这一层公用一个cur，
            // 直接把cur加入后面进行的操作也会对加入list的cur进行改变
            ans.add(new LinkedList<>(cur));
            return;
        }
        for(int i=first;i<n+1;i++){
            cur.add(i);
            helper(cur,i+1);
            //回溯关键
            cur.removeLast();
        }
    }

    //剪枝优化
    public void helper2(LinkedList<Integer> cur,int first){
        if(cur.size()==k) {
            ans.add(new LinkedList<>(cur));
            return;
        }
        //剪枝操作
        for(int i=first;i<n-(k-cur.size())+1;i++){
            cur.add(i);
            helper(cur,i+1);
            //回溯关键
            cur.removeLast();
        }
    }
}
