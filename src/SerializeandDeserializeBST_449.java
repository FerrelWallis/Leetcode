import java.util.*;

public class SerializeandDeserializeBST_449 {

    // 搜索二叉树，遍历的时候不插入null，仅仅是前序中序后序就可以确定一棵二叉树，
    // 因为搜索二叉树的中序是升序的，前序或者后序进行sort排序即可获得中序，
    // 满足左节点小于根节点，右节点大于根节点的原则

    //二叉树不插入null，后序遍历
    public String serialize3(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        return postorder(root,sb).toString();
    }

    private StringBuilder postorder(TreeNode root, StringBuilder sb) {
        if(root==null) return sb;
        postorder(root.left,sb);
        postorder(root.right,sb);
        sb.append(root.val);
        sb.append(",");
        return sb;
    }

    public TreeNode deserialize3(String data) {
        if(data.isEmpty()) return null;
        Deque<String> queue= new LinkedList<>(Arrays.asList(data.split(",")));
        return buildTree(Integer.MIN_VALUE,Integer.MAX_VALUE,queue);
    }

    private TreeNode buildTree(int low,int high,Deque<String> queue) {
        if(queue.isEmpty()) return null;
        int cur=Integer.valueOf(queue.getLast());
        if(cur<low||cur>high) return null;
        queue.pollLast();
        TreeNode node=new TreeNode(cur);
        node.right=buildTree(cur,high,queue);
        node.left=buildTree(low,cur,queue);
        return node;
    }


    //普通二叉树通用方法
    //1.深度优先遍历
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        return preorder(root,sb).toString();
    }

    private StringBuilder preorder(TreeNode root, StringBuilder sb) {
        if(root==null) sb.append("null,");
        else {
            sb.append(root.val);
            sb.append(",");
            preorder(root.left,sb);
            preorder(root.right,sb);
        }
        return sb;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> list=new LinkedList<>(Arrays.asList(data.split(",")));
        return depreorder(list);
    }

    private TreeNode depreorder(Deque<String> list) {
        String cur=list.pollFirst();
        if(cur.equals("null")) return null;
        TreeNode node=new TreeNode(Integer.valueOf(cur));
        node.left=depreorder(list);
        node.right=depreorder(list);
        return node;
    }


    //2.广度优先遍历
    public String serialize2(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        Deque<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                TreeNode cur= queue.pollFirst();
                if (cur==null) sb.append("null,");
                else{
                    sb.append(cur.val);
                    sb.append(",");
                    queue.add(cur.left);
                    queue.add(cur.right);
                }
            }
        }
        return sb.substring(0,sb.length()-1);
    }

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
            else{
                cur.left=new TreeNode(Integer.valueOf(nodes[index]));
                queue.add(cur.left);
            }
            index++;
            if(nodes[index].equals("null")) cur.right=null;
            else{
                cur.right=new TreeNode(Integer.valueOf(nodes[index]));
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
