import sun.reflect.generics.tree.Tree;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal_94 {

    //1.递归
    public List<Integer> out=new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return out;
    }

    public void inorder(TreeNode root){
        if(root!=null){
            inorder(root.left);
            out.add(root.val);
            inorder(root.right);
        }
    }


    //2.迭代1 维护一个栈，思想同递归 时间复杂度O(n)
    public List<Integer> inorderTraversal2(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        List<Integer> out=new ArrayList<>();
        TreeNode cur=root;
        while(cur!=null || !stack.empty()){
            while (cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            cur=stack.pop();
            out.add(cur.val);
            cur=cur.right;
        }
        return out;
    }

    //3.莫里斯遍历
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> out=new ArrayList<>();
        TreeNode cur=root;
        while(cur!=null){
            if(cur.left==null){
                out.add(cur.val);
                cur=cur.right;
            }else{
                TreeNode pre=cur.left;
                while (pre.right!=null){
                    pre=pre.right;
                }
                pre.right=cur;
                TreeNode temp=cur;
                cur=cur.left; //指向当前最高节点
                temp.left=null;
            }
        }
        return out;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


}
