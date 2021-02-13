import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;

public class MaximumDepthofBinaryTree_104 {

    //1.递归，深度优先遍历
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

    //2.迭代，广度优先遍历
    public int maxDepth2(TreeNode root) {
        if(root==null) return 0;
        LinkedList<TreeNode> stack=new LinkedList<>();
        stack.add(root);
        int level=0;
        while(!stack.isEmpty()){
            int size=stack.size();
            for(int i=0;i<size;i++){
                TreeNode cur=stack.poll();
                if(cur.left!=null) stack.add(cur.left);
                if(cur.right!=null) stack.add(cur.right);
            }
            level++;
        }
        return level;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
