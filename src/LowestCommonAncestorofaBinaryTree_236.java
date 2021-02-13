import sun.reflect.generics.tree.Tree;

public class LowestCommonAncestorofaBinaryTree_236 {

    //返回值： 根据 leftleft 和 rightright ，可展开为四种情况；
    //当 leftleft 和 rightright 同时为空 ：说明 rootroot 的左 / 右子树中都不包含 p,qp,q ，返回 nullnull ；
    //当 leftleft 和 rightright 同时不为空 ：说明 p, qp,q 分列在 rootroot 的 异侧 （分别在 左 / 右子树），因此 rootroot 为最近公共祖先，返回 rootroot ；
    //当 leftleft 为空 ，rightright 不为空 ：p,qp,q 都不在 rootroot 的左子树中，直接返回 rightright 。具体可分为两种情况：
    //p,qp,q 其中一个在 rootroot 的 右子树 中，此时 rightright 指向 pp（假设为 pp ）；
    //p,qp,q 两节点都在 rootroot 的 右子树 中，此时的 rightright 指向 最近公共祖先节点 ；
    //当 leftleft 不为空 ， rightright 为空 ：与情况 3. 同理；
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null||root.val==p.val||root.val==q.val) return root;
        TreeNode left=lowestCommonAncestor2(root.left,p,q);
        TreeNode right=lowestCommonAncestor2(root.right,p,q);
        if(left==null&&right==null) return null;
        else if(left==null) return right;
        else if(right==null) return left;
        else return root;
    }


    //1.递归 从叶子节点出发，后序遍历，先检查左右子树再核实根节点
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        postoder(root,p,q);
        return ans;
    }

    public TreeNode ans;
    private Boolean postoder(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return false;
        boolean left=postoder(root.left,p,q);
        boolean right=postoder(root.right,p,q);
        //当前根节点的左子树且右子树为true 或者 根节点为true且左子树右子树之一为true
        if((left&&right) || ((root.val==p.val||root.val==q.val) && (left||right)))
            ans=root;
        return left||right||(root.val==p.val || root.val==q.val);
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
