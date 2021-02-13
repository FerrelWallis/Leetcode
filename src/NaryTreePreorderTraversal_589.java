import java.util.*;

public class NaryTreePreorderTraversal_589 {

    //递归
    public List<Integer> out=new ArrayList<>();
    public List<Integer> preorder(Node root) {
        prehelper(root);
        return out;
    }

    public void prehelper(Node root){
        if(root!=null){
            out.add(root.val);
            for(Node node:root.children){
                prehelper(node);
            }
        }
    }

    //迭代
    public List<Integer> preorder2(Node root) {
        List<Integer> out=new ArrayList<>();
        LinkedList<Node> stack=new LinkedList<>();
        if(root==null) return out;
        stack.push(root);
        while(!stack.isEmpty()){
            Node cur=stack.pollLast();
            out.add(cur.val);
            //出栈是先入后出，所以要从左到右的出栈话，需要从右到左入栈
            Collections.reverse(cur.children);
            for(Node left2right: cur.children){
                stack.push(left2right);
            }
        }
        return out;
    }


    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
