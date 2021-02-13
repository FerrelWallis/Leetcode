import java.util.LinkedList;

public class MinimumDepthofBinaryTree_111 {

    //1.迭代 广度优先遍历  时间复杂度O(k) k取决于第一个叶子节点的深度
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        LinkedList<TreeNode> stack=new LinkedList<>();
        stack.add(root);
        boolean flag=true;
        int level=0;
        while(!stack.isEmpty()&&flag){
            level++;
            int size=stack.size();
            for(int i=0;i<size;i++){
                TreeNode cur=stack.poll();
                if(cur.left==null&&cur.right==null) flag=false;
                else {
                    if(cur.left!=null) stack.add(cur.left);
                    if(cur.right!=null) stack.add(cur.right);
                }
            }
        }
        return level;
    }

    //2.递归 深度优先遍历
    public int minDepth2(TreeNode root) {
        if(root==null) return 0;
        if(root.left==null&&root.right==null) return 1;
        int minleft=Integer.MAX_VALUE,minright=Integer.MAX_VALUE;
        if(root.left!=null) minleft=minDepth(root.left);
        if(root.right!=null) minright=minDepth(root.right);
        return Math.min(minleft,minright)+1;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
