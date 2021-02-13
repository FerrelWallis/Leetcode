import java.util.*;
import java.util.stream.Collectors;

public class ConstructBinaryTreefromPreorderandInorderTraversal_105 {

    //递归实现 map优化
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<inorder.length;i++) map.put(inorder[i],i);
        return buildHelper(preorder,inorder,0,preorder.length-1,0,inorder.length-1,map);
    }

    private TreeNode buildHelper(int[] preorder, int[] inorder, int p_start, int p_end,int i_start,int i_end,HashMap<Integer,Integer> map) {
        if(p_start-p_end==0) return null;
        int rootval=preorder[p_start];
        TreeNode root=new TreeNode(preorder[p_start]);
        int root_inorder_index=map.get(root.val);
        int leftnum=root_inorder_index-i_start;
        root.left=buildHelper(preorder,inorder,p_start+1,p_start+leftnum,i_start,root_inorder_index-1,map);
        root.right=buildHelper(preorder,inorder,p_start+leftnum+1,p_end,root_inorder_index+1,i_end,map);
        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
