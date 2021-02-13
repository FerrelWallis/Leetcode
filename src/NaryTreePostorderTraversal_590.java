import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NaryTreePostorderTraversal_590 {

    public List<Integer> out=new ArrayList<>();
    public List<Integer> postorder(Node root) {
        posthelper(root);
        return out;
    }

    public void posthelper(Node root){
        if(root!=null){
            for(Node node:root.children){
                posthelper(node);
            }
            out.add(root.val);
        }
    }

    //迭代
    public List<Integer> postorder2(Node root) {
        LinkedList<Node> stack=new LinkedList<>();
        LinkedList<Integer> out=new LinkedList<>();
        if(root==null) return out;

        //左右根
        stack.add(root);
        while (!stack.isEmpty()){
            Node cur=stack.pollLast();
            out.addFirst(cur.val);
            for(Node right2left:cur.children){
                if(right2left!=null) stack.add(right2left);
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
