import java.util.*;

public class NaryTreeLevelOrderTraversal_429 {

    //1迭代，使用队列实现广度优先搜索
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> out=new ArrayList<>();
        if (root == null) return out;
        Queue<Node> queue=new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> level=new ArrayList<>();
            //先将获取到的size放入变量，循环直接使用queue.size因为queue一直在变化，所以会影响
            int size=queue.size();
            for(int i=0;i<size;i++){
                Node cur=queue.remove();
                level.add(cur.val);
                queue.addAll(cur.children);
            }
            out.add(level);
        }
        return out;
    }

    //2简化迭代
    public List<List<Integer>> levelOrder2(Node root) {
        List<List<Integer>> out=new ArrayList<>();
        if (root == null) return out;
        List<Node> previouslayer= Arrays.asList(root);
        while (!previouslayer.isEmpty()){
            List<Node> currentlayer=new ArrayList<>();
            List<Integer> level=new ArrayList<>();
            for(Node cur:previouslayer){
                level.add(cur.val);
                currentlayer.addAll(cur.children);
            }
            previouslayer=currentlayer;
            out.add(level);
        }
        return out;
    }

    //3.递归 通常广度优先遍历不适合使用递归，因为广度优先遍历基于队列，而递归基于栈。
    //但这题不关注遍历过程，只需要直到每个元素所在层数，并输出正确位置即可
    public List<List<Integer>> out=new ArrayList<>();
    public List<List<Integer>> levelOrder3(Node root) {
        if(root==null) return out;
        traverse(root,0);
        return out;
    }

    public void traverse(Node cur,int level){
        if(out.size()<=level){
            out.add(new ArrayList<>());
        }
        out.get(level).add(cur.val);
        for(Node child:cur.children){
            traverse(child,level+1);
        }
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
