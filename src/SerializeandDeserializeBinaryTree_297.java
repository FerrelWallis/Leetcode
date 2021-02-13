import sun.reflect.generics.tree.Tree;

import java.util.*;

public class SerializeandDeserializeBinaryTree_297 {

    // 这里普通二叉树序列化，前序中序后序遍历的时候要插入null才能确定一颗二叉树，因为在不插入null的情况下，
    // 只是前序中序后序遍历无法确定一棵二叉树，必须要前序+中序或者后序+中序才能确定一棵二叉树
    // 前序+后序也无法确定一颗二叉树

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        return rserialize(root,sb).toString();
    }

    //序列化，深度优先遍历：前序遍历，递归 时间复杂度O(n)
    private StringBuilder rserialize(TreeNode root, StringBuilder sb) {
        if(root==null) sb.append("null,");
        else{
            sb.append(root.val);
            sb.append(",");
            rserialize(root.left,sb);
            rserialize(root.right,sb);
        }
        return sb;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> list=new LinkedList<>(Arrays.asList(data.split(",")));
        return rdeserialize(list);
    }

    private TreeNode rdeserialize(Deque<String> list) {
        String cur=list.pollFirst();
        if(cur.equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(cur));
        root.left=rdeserialize(list);
        root.right=rdeserialize(list);
        return root;
    }



    //广度优先遍历
    public String serialize2(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        Deque<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode cur=queue.pollFirst();
            if(cur==null) sb.append("null,");
            else{
                sb.append(cur.val);
                sb.append(",");
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        return sb.substring(0,sb.length()-1);
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        if(data.equals("null")) return null;
        String[] nodes=data.split(",");
        Deque<TreeNode> queue=new LinkedList<>();
        TreeNode root=new TreeNode(Integer.valueOf(nodes[0]));
        queue.add(root);
        int index=1;
        while (!queue.isEmpty()){
            TreeNode cur=queue.pollFirst();
            if(nodes[index].equals("null")) cur.left=null;
            else {
                cur.left=new TreeNode(Integer.parseInt(nodes[index]));
                queue.add(cur.left);
            }
            index++;
            if(nodes[index].equals("null")) cur.right=null;
            else {
                cur.right=new TreeNode(Integer.parseInt(nodes[index]));
                queue.add(cur.right);
            }
            index++;
        }
        return root;
    }




    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
