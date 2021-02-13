import java.util.HashMap;

public class ConstructBinaryTreefromInorderandPostorderTraversal_106 {
    //中序+后续
    //递归

    public int p_cur;
    public HashMap<Integer,Integer> imap=new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len=inorder.length;
        p_cur=len-1;
        for(int i=0;i<len;i++){
            imap.put(inorder[i],i);
        }
        return helper(inorder,postorder,0,len-1);
    }

    private TreeNode helper(int inorder[],int postorder[],int i_start, int i_end) {
        if (i_start > i_end) return null;
        TreeNode root=new TreeNode(postorder[p_cur]);
        int i_cur=imap.get(postorder[p_cur]);
        int rightnum=i_start-i_cur;
        p_cur--;
        root.right=helper(inorder,postorder,i_cur+1,i_end);
        root.left=helper(inorder,postorder,i_start,i_cur-1);
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

