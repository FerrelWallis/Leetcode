import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class FindLargestValueinEachTreeRow_515 {

    //DFS深度优先遍历
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans=new LinkedList<>();
        helper(ans,root,0);
        return ans;
    }

    private void helper(List<Integer> ans, TreeNode cur, int level) {
        if(cur==null) return;
        if(ans.size()<=level) ans.add(cur.val);
        else {
            int temp=ans.get(level);
            ans.set(level,Math.max(temp,cur.val));
        }
        helper(ans,cur.left,level+1);
        helper(ans,cur.right,level+1);
    }

    //广度优先遍历
    public List<Integer> largestValues2(TreeNode root) {
        List<Integer> ans=new LinkedList<>();
        if(root==null) return ans;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        int level=0;
        while (!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                TreeNode cur=queue.poll();
                if(ans.size()<=level) ans.add(cur.val);
                else ans.set(level,Math.max(ans.get(level),cur.val));
                if(cur.left!=null) queue.add(cur.left);
                if(cur.right!=null) queue.add(cur.right);
            }
            level++;
        }
        return ans;
    }




    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
