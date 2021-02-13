import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GenerateParentheses_22 {
    //1.深度优先遍历 递归实现 时间复杂度O(2^n) 但大大加速，去掉了不必要的分支
    public List<String> generateParenthesis(int n) {
        List<String> out=new ArrayList<>();
        generate("",0,0,out,n);
        return out;
    }

    private void generate(String cur, int left, int right, List<String> out,int n) {
        if(left==n&&right==n) {
            out.add(cur);
            return;
        }
        //判断合法性两点:
        // 1.left随时加，只要不要超过括号数（即n）即可
        // 2.right必须出现在左括号之后，也就是，加right时，必须小于等于左括号
        if(left<n) {
            String newcur=cur+"(";
            generate(newcur,left+1,right,out,n);
        }
        if(right<=left && right<n) {
            String newcur=cur+")";
            generate(newcur,left,right+1,out,n);
        }
    }


    //2.广度优先遍历 最后一层是结果
    public List<String> generateParenthesis2(int n) {
        List<String> out=new ArrayList<>();
        Queue<Node> queue=new LinkedList<>();
        queue.add(new Node(n,n,""));
        while (!queue.isEmpty()){
            Node node=queue.poll();
            if(node.left==0&&node.right==0) out.add(node.cur);
            else if(node.left<=node.right){
                if(node.left>0) queue.add(new Node(node.left-1,node.right,node.cur+"("));
                if(node.right>0) queue.add(new Node(node.left,node.right-1,node.cur+")"));
            }
        }
        return out;
    }

    class Node{
        private int left;
        private int right;
        private String cur;

        public Node(int l,int r,String c){
            left=l;
            right=r;
            cur=c;
        }
    }


    //3. 按括号长度的序列递归
    //任何一个括号序列都一定是由 ( 开头，并且第一个 ( 一定有一个唯一与之对应的 )。这样一来，每一个括号序列可以用 (a)b 来表示，其中 a 与 b 分别是一个合法的括号序列（可以为空）。
    //
    //那么，要生成所有长度为 2 * n 的括号序列，我们定义一个函数 generate(n) 来返回所有可能的括号序列。那么在函数 generate(n) 的过程中：
    //
    //我们需要枚举与第一个 ( 对应的 ) 的位置 2 * i + 1；
    //递归调用 generate(i) 即可计算 a 的所有可能性；
    //递归调用 generate(n - i - 1) 即可计算 b 的所有可能性；
    //遍历 a 与 b 的所有可能性并拼接，即可得到所有长度为 2 * n 的括号序列。
    //为了节省计算时间，我们在每次 generate(i) 函数返回之前，把返回值存储起来，下次再调用 generate(i) 时可以直接返回，不需要再递归计算。

        ArrayList[] cache = new ArrayList[100];
        public List<String> generate(int n) {
            if (cache[n] != null) {
                return cache[n];
            }
            ArrayList<String> ans = new ArrayList();
            if (n == 0) {
                ans.add("");
            } else {
                for (int c = 0; c < n; ++c)
                    for (String left: generate(c))
                        for (String right: generate(n - 1 - c))
                            ans.add("(" + left + ")" + right);
            }
            cache[n] = ans;
            return ans;
        }
        public List<String> generateParenthesis3(int n) {
            return generate(n);
        }


}
