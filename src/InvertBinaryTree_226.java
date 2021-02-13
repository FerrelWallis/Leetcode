import java.util.LinkedList;

public class InvertBinaryTree_226 {

    //1.递归 时间复杂度O(n)
    public TreeNode invertTree(TreeNode root) {
        if(root==null) return null;
        TreeNode temp=root.left;
        root.left=root.right;
        root.right=temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }


    //2.迭代
    public TreeNode invertTree2(TreeNode root) {
        if(root==null) return root;
        LinkedList<TreeNode> stack=new LinkedList<>();
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode cur=stack.poll();
            TreeNode temp=cur.left;
            cur.left=cur.right;
            cur.right=temp;
            if(cur.left!=null) stack.add(cur.left);
            if(cur.right!=null) stack.add(cur.right);
        }
        return root;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
