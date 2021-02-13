import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreorderTraversal_144 {

    //递归
    public List<Integer> out=new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        preorder(root);
        return out;
    }

    public void preorder(TreeNode root){
        if(root!=null){
            out.add(root.val);
            preorder(root.left);
            preorder(root.right);
        }
    }

    //迭代 1 维护一个栈
    public List<Integer> preorderTraversal2(TreeNode root){
        if(root==null) return new ArrayList<>();
        List<Integer> out=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();
        stack.push(root);
        while (!stack.empty()){
            TreeNode temp=stack.pop();
            out.add(temp.val);
            //先放right，再放left是因为，前序遍历出栈顺序是中左右，栈是先进后出
            if(temp.right!=null) stack.push(temp.right);
            if(temp.left!=null) stack.push(temp.left);
        }
        return out;
    }

    //迭代2 莫里斯遍历
    public List<Integer> preorderTraversal3(TreeNode root){
        List<Integer> out=new ArrayList<>();
        TreeNode curr=root;
        while (curr!=null){
            if(curr.left!=null){
                //根节点的右节点接到，根节点的左子树最右的节点上
                TreeNode temp=curr.left;
                while(temp.right!=null){
                    temp=temp.right;
                }
                temp.right=curr.right;
                out.add(curr.val);
                curr=curr.left;
            }else{
                out.add(curr.val);
                curr=curr.right;
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
