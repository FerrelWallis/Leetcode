public class LongestUnivaluePath_687 {
    //687. Longest Univalue Path
    //Given the root of a binary tree, return the length of the longest path, where each node in the path has the same value. This path may or may not pass through the root.
    //
    //The length of the path between two nodes is represented by the number of edges between them.
    //
    //
    //
    //Example 1:
    //
    //
    //Input: root = [5,4,5,1,1,5]
    //Output: 2
    //Example 2:
    //
    //
    //Input: root = [1,4,5,4,4,5]
    //Output: 2
    //
    //
    //Constraints:
    //
    //The number of nodes in the tree is in the range [0, 104].
    //-1000 <= Node.val <= 1000
    //The depth of the tree will not exceed 1000.
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
    //递归
    int ans;
    public int longestUnivaluePath(TreeNode root) {
        ans = 0;
        arrowLength(root);
        return ans;
    }
    public int arrowLength(TreeNode node) {
        if (node == null) return 0;
        int left = arrowLength(node.left);
        int right = arrowLength(node.right);
        int arrowLeft = 0, arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft += left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight += right + 1;
        }
        ans = Math.max(ans, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }

    //dfs  计算当前节点作为最高点，向左右延伸的最大值路径和，存放入ans
    //     dfs返回当前节点左右路径之间的最大值，作为其父节点的延伸路径
    //相同思路的题：leetcode124
    public int longestUnivaluePath2(TreeNode root) {
        max_length(root);
        return ans;
    }

    private int max_length(TreeNode root) {
        if (root == null) return 0;
        int leftlen = max_length(root.left);
        int rightlen = max_length(root.right);
        int curleft = 0, curright = 0;
        if (root.left != null && root.left.val == root.val) curleft = leftlen + 1;
        if (root.right != null && root.right.val == root.val) curright = rightlen + 1;
        ans = Math.max(ans, curleft + curright);
        return Math.max(leftlen, rightlen);
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
