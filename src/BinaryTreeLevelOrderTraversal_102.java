import java.util.*;

public class BinaryTreeLevelOrderTraversal_102 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    //广度优先遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans=new LinkedList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            List<Integer> cur=new ArrayList<>();
            int num=queue.size();
            for(int i=0;i<num;i++){
                TreeNode temp=queue.remove();
                cur.add(temp.val);
                if(temp.left!=null) queue.offer(temp.left);
                if(temp.right!=null) queue.offer(temp.right);
            }
            ans.add(cur);
        }
        return ans;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans=new LinkedList<>();
        if(root==null) return ans;
        helper(root,0,ans);
        return ans;
    }

    private void helper(TreeNode cur, int level, List<List<Integer>> ans) {
        if(ans.size()<=level) ans.add(new ArrayList<>());
        ans.get(level).add(cur.val);
        if (cur.left!=null) helper(cur.left,level+1,ans);
        if (cur.right!=null) helper(cur.right,level+1,ans);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
