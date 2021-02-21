import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class N_Queens_51 {

    //1. 回溯 三个数组记录cols, diag1, diag2
    //时间复杂度分析：每个递归里有一个循环，每个循环循环n次，所以时间复杂度是n个n相乘，即O(n!)
    List<List<String>> ans=new ArrayList<>();
    public List<List<String>> solveNQueens0(int n) {
        backtrace(n, 0, new int[n], new boolean[n], new boolean[2*n], new boolean[2*n]);
        return ans;
    }

    private void backtrace(int n, int row, int[] board, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if(row == n) {
            ans.add(generate2(board, n));
            return;
        }
        for(int i = 0; i < n; i++) {
            int cur_col = i;
            int cur_dia1 = i + row;
            int cur_dia2 = i - row + n;
            if(cols[cur_col] || diag1[cur_dia1] || diag2[cur_dia2]) continue;
            cols[cur_col] = true;
            diag1[cur_dia1] = true;
            diag2[cur_dia2] = true;
            board[row] = i;
            backtrace(n, row + 1, board, cols, diag1, diag2);
            cols[cur_col] = false;
            diag1[cur_dia1] = false;
            diag2[cur_dia2] = false;
        }
    }

    public List<String> generate2(int[] board, int n) {
        List<String> cur = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row,'.');
            row[board[i]] = 'Q';
            cur.add(new String(row));
        }
        return cur;
    }

    //位运算
    public List<List<String>> solveNQueens1(int n) {
        backtrace(n, 0, new int[n], 0, 0, 0);
        return ans;
    }

    // 这里注意传参特性，基础类型例如int a,传参时都是传的a的值的拷贝，所以函数里面改变参数不改变原值
    // 而引用类型例如数组，传参时传的都是对象，即引用地址，所以函数里面改变参数内容，会改变原引用内容，因此要进行回溯来维护环境
    private void backtrace(int n, int row, int[] board, int cols, int diag1, int diag2) {
        if(row == n) {
            ans.add(generate2(board, n));
            return;
        }
        int avail_pos = (~(cols | diag1 | diag2)) & ((1 << n) - 1);
        while (avail_pos != 0) {
            int pos = avail_pos & (-avail_pos); //获取最低位的1
            avail_pos = avail_pos & (avail_pos - 1); //删除最低位的1
            int column = Integer.bitCount(pos - 1);
            board[row] = column;
            backtrace(n, row + 1, board, (cols | pos), ((diag1 | pos) << 1), ((diag2 | pos) >> 1));
        }
    }



}
