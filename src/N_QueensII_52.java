import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class N_QueensII_52 {
    //52. N QueensII
    //回溯 三个数组记录
    //时间复杂度分析：每个递归里有一个循环，每个循环循环n次，所以时间复杂度是n个n相乘，即O(n!)
    public int num = 0;
    public int totalNQueens(int n) {
        backtrace(n, 0, new boolean[n], new boolean[2 * n], new boolean[2 * n]);
        return num;
    }

    public void backtrace(int n, int row, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if(row == n) {
            num++;
            return;
        }
        for(int i = 0; i < n; i++) {
            int cur_col = i;
            int cur_dia1 = i + row;
            int cur_dia2 = i - row + n; //i - row的范围是-n~n，所以加上n让范围保持在0~2n
            if(cols[cur_col] || diag1[cur_dia1] || diag2[cur_dia2]) continue;
            cols[cur_col] = true;
            diag1[cur_dia1] = true;
            diag2[cur_dia2] = true;
            backtrace(n, row + 1, cols, diag1, diag2);
            cols[cur_col] = false;
            diag1[cur_dia1] = false;
            diag2[cur_dia2] = false;
        }
    }

    //位运算
    // 由于cols、diag1、diag2三个数组存储的信息只是true和false，且需要的空间分别是n,n,n 而题目规定 1<=n<=9
    // 上面数组存储斜线信息的时候，是利用了两条斜线row + col相等和row - col的特性，因此diag1、diag2空间为2n
    // 位运算中，两斜线存储的信息是，在当前行中，哪些位置处于之前设置的Queen的两条斜线上，因此空间只需要n
    // 因此，可以使用三个int类型的32位二进制的位运算来实现，这样只需要三个int来存储信息，不能放的位置设为1

    // 这里注意传参特性，基础类型例如int a,传参时都是传的a的值的拷贝，所以函数里面改变参数不改变原值
    // 而引用类型例如数组，传参时传的都是对象，即引用地址，所以函数里面改变参数内容，会改变原引用内容，因此要进行回溯来维护环境
    public int totalNQueens1(int n) {
        backtrace(n, 0, 0, 0, 0);
        return num;
    }

    public void backtrace(int n, int row, int cols, int diag1, int diag2) {
        if(row == n) {
            num++;
            return;
        }
        //~(cols | diag1 | diag2)将当前可以存放的位置设为1, & ((1 << n) - 1)是确保二进制后n位之前的所有位为0，以防负数影响
        int avail_pos = (~(cols | diag1 | diag2)) & ((1 << n) - 1);
        while (avail_pos != 0) {
            //获取最低位的1，（确保最低位的1维持1，其余取反或为0）=> 补码特性 负数二进制，即补码，先取反，再加1
            int pos = avail_pos & (-avail_pos);
            //清除最低位的1
            avail_pos = avail_pos & (avail_pos - 1);
            //将pos该位置设为Q，因此将pos影响到下一层的cols、diag1、diag2不能放的位置设为1
            //先分别将pos的1加入cols、diag1、diag2, 表示这一行pos位置放入了Q，当前这一行不能放置的位置，即(pos | cols)
            //而下一层对于下一层的cols、diag1、diag2来说，cols不变，下一层diag1是当前层diag1中所有不能放的位置左移1，因此(pos | diag1) << 1
            //同理可得diag2
            backtrace(n, row + 1, (pos | cols), ((pos | diag1) << 1), ((pos | diag2) >> 1));
        }
    }


    //回溯 空间优化 用一个数组记录每行皇后位置信息
    public int sum=0;
    public int totalNQueens3(int n) {
        int[] pre=new int[n];
        backtrace(n,0,pre);
        return sum;
    }

    //pre数组同时记录了之前皇后的行列信息，pre的下标表示第几行，存的值表示列，pre[i]  表示第i行第pre[i]列放皇后
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

}

