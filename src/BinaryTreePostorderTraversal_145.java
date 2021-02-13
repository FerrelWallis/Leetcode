import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePostorderTraversal_145 {

    //1.递归
    public List<Integer> out=new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        postorder(root);
        return out;
    }

    public void postorder(TreeNode root){
        if(root!=null){
            postorder(root.left);
            postorder(root.right);
            out.add(root.val);
        }
    }


    //2.迭代1 维护栈
    public List<Integer> postorderTraversal2(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        List<Integer> out=new ArrayList<>();

        TreeNode cur=root;
        TreeNode last=null;
        while(cur!=null || !stack.empty()){
            while (cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            cur=stack.peek();
            if(cur.right==null || cur.right==last){ //右子树已访问过，或者右子树不存在，
                out.add(stack.pop().val);
                last=cur;
                cur=null;
            }else{
                cur=cur.right;
                stack.push(cur);
            }
        }
        return out;
    }


    //2.迭代2 维护栈优化 后序遍历是左右根，可以通过根右左的顺序遍历，并将节点放到链表最前面，以反转顺序，这样就不需要记忆右节点是否访问过
    public List<Integer> postorderTraversal3(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.addFirst(node.val);  //将节点加入最前面
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return output;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
