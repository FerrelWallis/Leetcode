import sun.reflect.generics.tree.Tree;

import java.util.*;

public class BinaryTreeVerticalOrderTraversal_314 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        Map<TreeNode,Integer> map=new HashMap<>();
        //hashmap是无序的，TreeMap是有序的
        Map<Integer, List<Integer>> ans=new TreeMap<>();
        Queue<TreeNode> queue=new LinkedList<>();
        map.put(root,0);
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode cur=queue.poll();
            int index=map.get(cur);
            //ans 的key 是否存在，没存在就先new一个，再add， 或已存在，直接add
            ans.computeIfAbsent(index,i->new LinkedList<Integer>()).add(cur.val);
            if(cur.left!=null){
                queue.add(cur.left);
                map.put(cur.left,index-1);
            }
            if(cur.right!=null){
                queue.add(cur.right);
                map.put(cur.right,index+1);
            }
        }
        return new LinkedList<>(ans.values());
    }

    //不用treemap，思想一致

    public List<List<Integer>> verticalOrder2(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;

        // 层次遍历，初始化根节点为第0列（参照列）
        Queue<TreeNode> queue = new LinkedList<>(); // 记录结点
        Queue<Integer> colnum = new LinkedList<>();// 记录相对列
        queue.add(root);
        colnum.add(0);

        // 记录偏移值
        int offset = 0;
        Set<Integer> set = new HashSet<>();
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int col = colnum.poll();
            // 第一次访问该列
            if (!set.contains(col)) {
                // 正数列插入后面，偏移值不变
                if (col >= 0) {
                    res.add(new LinkedList<>());
                }
                // 负数列插入前面，偏移值加一
                else {
                    //linkedlist指定位置插入元素，原元素及后面的元素后移
                    res.add(0, new LinkedList<>());
                    offset++;
                }
                // 已访问集合加入列
                set.add(col);
            }
            // 根据偏移和相对列值获取数组，并拼接到后面
            res.get(offset + col).add(cur.val);
            // 加入下一层结点
            if (cur.left != null) {
                queue.add(cur.left);
                colnum.add(col - 1);
            }
            if (cur.right != null) {
                queue.add(cur.right);
                colnum.add(col + 1);
            }
        }
        return res;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
