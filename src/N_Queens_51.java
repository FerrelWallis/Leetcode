import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class N_Queens_51 {

    //1. 回溯
    // 时间复杂度：O(N!)，其中 N 是皇后数量。
    //空间复杂度：O(N)，其中 N 是皇后数量。空间复杂度主要取决于递归调用层数、记录每行放置的皇后的列下标的数组。

    List<List<String>> ans=new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        int[] pre = new int[n];
        Arrays.fill(pre,-1);
        helper(n,0,pre);
        return ans;
    }

    private void helper(int n, int row, int[] pre) {
        if(row==n) {
            List<String> cur=generate(pre,n);
            ans.add(new ArrayList<>(cur));
            return;
        }
        for(int col=0;col<n;col++){//检查之前几行的皇后是否冲突
            boolean flag=true;
            for(int j=0;j<row;j++){
                //行列下标相加相等说明是左斜线上 ，行减去列下标相等说明是右斜线上，
                if(row+col==pre[j]+j || row-col==j-pre[j] || col==pre[j]) {
                    flag=false;
                    break;
                }
            }
            if(flag){  //有两种情况curcol==col 1.当前位置在之前皇后的攻击范围内  2.row==0
                pre[row]=col;
                helper(n,row+1,pre);
            }
        }
    }


    private List<String> generate(int[] pre, int n) {
        List<String> currow=new ArrayList<>();
        for(int i=0;i<n;i++){
            char[] a=new char[n];
            Arrays.fill(a,'.');
            a[pre[i]]='Q';
            currow.add(new String(a));
        }
        return currow;
    }

    //2. 回溯 先判断可能性，再进行递归
    private void helper2(int n, int row, int[] pre) {
        if(row==n) {
            List<String> cur=generate(pre,n);
            ans.add(new ArrayList<>(cur));
            return;
        }
        int[] possible=new int[n];  //1的位置表示不能放皇后
        for(int j=0;j<row;j++){
            int index1=pre[j]+row-j; //从第j行pre[j]列开始，到当前row右斜线上的点的下标，要确认下标是否<n
            int index2=pre[j]-row+j; //从第j行pre[j]列开始，到当前row左斜线上的点的下标，要确认下标是否>=0
            int index3=pre[j];
            if(index1<n) possible[index1]=1;
            if(index2>=0) possible[index2]=1;
            possible[index3]=1;
        }
        for(int i=0;i<possible.length;i++){
            if(possible[i]!=1) {
                pre[row]=i;
                helper2(n,row+1,pre);
            }
        }
    }




    //3. 位运算
    public List<List<String>> solveNQueens2(int n) {
        int[] queens = new int[n];
        List<List<String>> solutions = new ArrayList<List<String>>();
        solve(solutions, queens, n, 0, 0, 0, 0);
        return solutions;
    }

    public void solve(List<List<String>> solutions, int[] queens, int n, int row, int columns, int diagonals1, int diagonals2) {
        if (row == n) {
//            System.out.println(check(queens,n));
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            //(1 << n) - 1) =>是n位1 的二进制数
            //需要(1 << n) - 1) &这一步是因为做完非运算获得的二进制是最大二进制的数量，而不是8位
            //比如10 ~10 -> 11111111111111111111111111111101 而不是 11111101
            int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2)); //0110 1101
            System.out.println(Integer.toBinaryString(availablePositions));
            while (availablePositions != 0) {
                //负数->取反码,再加1    1001 0011 再与availableposition与运算  获取二进制中最小的1的位置 => 0000 0001
                int position = availablePositions & (-availablePositions);
                availablePositions = availablePositions - position;  //将二进制中最小的1去掉
                //Integer.bitCount()  用法是计算int,long类型的数值在二进制下“1”的数量
                //这里可以用来获取1所在下标  即 col
                int column = Integer.bitCount(position - 1);
                queens[row] = column;
                //新的column=columns | position 把最新的1加入
                //很神奇的操作，加入新的1后，直接左位移1就可以 (diagonals1 | position) << 1
                //加入新的1后，直接右位移1就可以 (diagonals2 | position) >> 1
                solve(solutions, queens, n, row + 1, columns | position, (diagonals1 | position) << 1, (diagonals2 | position) >> 1);
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
