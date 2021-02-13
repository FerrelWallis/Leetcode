import java.util.Arrays;
import java.util.HashMap;

public class ConstructBinaryTreefromPreorderandPostorderTraversal_889 {


    //递归
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        int N = pre.length;
        if (N == 0) return null;
        TreeNode root = new TreeNode(pre[0]);
        if (N == 1) return root;

        int L = 0;
        for (int i = 0; i < N; ++i)
            if (post[i] == pre[1])
                L = i+1;

        root.left = constructFromPrePost(Arrays.copyOfRange(pre, 1, L+1),
                Arrays.copyOfRange(post, 0, L));
        root.right = constructFromPrePost(Arrays.copyOfRange(pre, L+1, N),
                Arrays.copyOfRange(post, L, N-1));
        return root;
    }

    //2.空间优化
    public int[] pre;
    public int[] post;
    public TreeNode constructFromPrePost2(int[] pre, int[] post) {
        this.pre=pre;
        this.post=post;
        return helper(0,0,pre.length);
    }

    private TreeNode helper(int pre_index, int post_index, int length) {
        if(length==0) return null;
        TreeNode root=new TreeNode(pre[pre_index]);
        if(length==1) return root;
        int leftlen=1;
        while(pre[pre_index+1]!=post[post_index+leftlen-1]) leftlen++;
        root.left=helper(pre_index+1,post_index,leftlen);
        root.right=helper(pre_index+leftlen+1,post_index+leftlen,length-leftlen-1);
        return root;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
