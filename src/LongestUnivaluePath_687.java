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
