public class ValidateBinarySearchTree_98 {

    //1. 递归
    public boolean isValidBST(TreeNode root) {
        //0为left 1为right
        return checknode(root,null,null);

    }

    //注意：右节点的所有左子节点，必须大于右节点的父节点，同时小于右节点
    //      左节点的所有右子节点，必须小于左节点的父节点，同时大于左节点
    public boolean checknode(TreeNode root,Integer lower,Integer upper){
        if (root==null) return true;
        if (upper!=null && root.val>=upper) return false;
        if (lower!=null && root.val<=lower) return false;
        if (!checknode(root.left,lower,root.val)) return false;
        if (!checknode(root.right,root.val,upper)) return false;
        return true;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
