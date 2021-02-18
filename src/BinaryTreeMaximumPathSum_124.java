public class BinaryTreeMaximumPathSum_124 {
    //124. Binary Tree Maximum Path Sum
    //A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the
    //sequence has an edge connecting them. A node can only appear in the sequence at most once.
    //Note that the path does not need to pass through the root.
    //The path sum of a path is the sum of the node's values in the path.
    //Given the root of a binary tree, return the maximum path sum of any path.

    //Example 1:
    //Input: root = [1,2,3]
    //Output: 6
    //Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

    //Example 2:
    //Input: root = [-10,9,20,null,null,15,7]
    //Output: 42
    //Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

    //Constraints:
    //    The number of nodes in the tree is in the range [1, 3 * 104].
    //    -1000 <= Node.val <= 1000
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

    //dfs  计算当前节点作为最高点，向左右延伸的最大值路径和，存放入ans
    //     dfs返回当前节点左右路径之间的最大值，作为其父节点的延伸路径
    //相同思路的题：leetcode687
    class Solution {
        public int maxPathSum(TreeNode root) {

            return 0;
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
}
