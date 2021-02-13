import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class N_QueensII_52 {
    //回溯
    public int sum=0;
    public int totalNQueens(int n) {
        int[] pre=new int[n];
        backtrace(n,0,pre);
        return sum;
    }

    private void backtrace(int n, int row, int[] pre) {
        if(row==n) {
            sum++;
            return;
        }
        for(int col=0;col<n;col++){
            boolean flag=true;
            for(int j=0;j<row;j++){
                if(row+col==j+pre[j] || row-col==j-pre[j] || col==pre[j]){
                    flag=false;
                    break;
                }
            }
            if(flag){
                pre[row]=col;
                backtrace(n,row+1,pre);
            }
        }
    }


    //2.位运算
    public int totalNQueens2(int n) {
        int[] queens = new int[n];
        List<List<String>> solutions = new ArrayList<List<String>>();
        solve(solutions, queens, n, 0, 0, 0, 0);
        return sum;
    }

    public void solve(List<List<String>> solutions, int[] queens, int n, int row, int columns, int diagonals1, int diagonals2) {
        if (row == n) sum++;
        else {
            //(1 << n) - 1) =>是n位1 的二进制数
            int availablePositions = ((1 << n) - 1) & (~(columns | diagonals1 | diagonals2)); //0110 1101
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

}

