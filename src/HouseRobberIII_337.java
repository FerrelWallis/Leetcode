import java.util.LinkedList;
import java.util.Queue;

public class HouseRobberIII_337 {

    //The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
    //
    //Determine the maximum amount of money the thief can rob tonight without alerting the police.
    //
    //Example 1:
    //
    //Input: [3,2,3,null,3,null,1]
    //
    //     3
    //    / \
    //   2   3
    //    \   \
    //     3   1
    //
    //Output: 7
    //Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
    //Example 2:
    //
    //Input: [3,4,5,1,3,null,1]
    //
    //     3
    //    / \
    //   4   5
    //  / \   \
    // 1   3   1
    //
    //Output: 9
    //Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    //层序遍历
    public int rob(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0, oddsum = 0, evensum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if(level % 2 == 0) evensum += cur.val;
                else oddsum += cur.val;
                if(cur.left != null) queue.add(cur.left);
                if(cur.right != null) queue.add(cur.right);
            }
            level++;
        }
        return Math.max(oddsum, evensum);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
