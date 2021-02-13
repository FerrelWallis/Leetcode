import java.util.TreeSet;

public class MaxSumofRectangleNoLargerThanK_363 {
    //MaxSumofRectangleNoLargerThanK_363
    //Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that
    //its sum is no larger than k.

    //Example:
    //Input: matrix = [[1,0,1],[0,-2,3]], k = 2
    //Output: 2
    //Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
    //             and 2 is the max number no larger than k (k = 2).
    //Note:
    //The rectangle inside the matrix must have an area > 0.
    //What if the number of rows is much larger than the number of columns?

    //固定边界（枚举左右边界） + 动态规划（上下边界） 动归思路可以从 53MaximumSubarray找到启发（连续子序的最大值通用方法）
    //固定左右边界，算边界内每一行的和，这样矩形宽度固定，只需要考虑连续行，也就是rowsum数组的连续子序和问题了
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length, max = Integer.MIN_VALUE;
        //枚举边界
        for(int l = 0; l < col; l++) {
            int[] rowsum = new int[row];
            for(int r = l; r < col; r++) {
                for(int i = 0; i < row; i++) {
                    rowsum[i] += matrix[i][r];
                }
                max = Math.max(max, maxdp(rowsum, k)); //列中连续sum中不大于K 的最大值
                if(max == k) return k;
            }
        }
        return max;
    }


    //动态规划剪枝（参考63题） + 暴力 总体时间复杂度 O(N^4) 空间复杂度O（N）
    private int maxdp(int[] rowsum, int k) {
        int pre = 0, max = 0;
        for(int i = 0; i < rowsum.length; i++) {
            pre = Math.max(rowsum[i], pre + rowsum[i]);
            if(pre == k) return k;
            else if(pre < k) max = Math.max(max, pre);
        }
       if(max <= k) return max;
        // O(rows ^ 2) 暴力
        max = Integer.MIN_VALUE;
        for (int l = 0; l < rowsum.length; l++) {
            int sum = 0;
            for (int r = l; r < rowsum.length; r++) {
                sum += rowsum[r];
                if (sum > max && sum <= k) max = sum;
                if (max == k) return k; // 尽量提前
            }
        }
        return max;
    }

    //这里动归的时间复杂度O(n) 可以用二分查找 TreeSet排序实现 时间复杂度是O(logn)
    //总体时间复杂度 min(m,n)^2*max(m,n)*log(max(m,n))
    private int maxdp2(int[] rowsum, int k) {
        int result = Integer.MIN_VALUE;
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int current = 0;
        for(int i = 0; i < rowsum.length; i++) {
            current += rowsum[i]; //这里current是不断累加的，没有进行中间剪切某一段的操作
            //比如 当前area的index(3~8), 那么area = current(0~8) - current(0~3)
            //假设 current(0~x2)是我们当前遍历到的某个值，current(0~x1)是之前已经遍历过且存入treeset的某个值
            //我们希望 求一个 area（x1~x2） <= k 且 这个area尽可能的大  可推出  current(0~x2) - current(0~x1) <= k
            //整理等式 =>  current(0~x1) >= current(0~x2) - k 且 current(0~x1)尽可能小
            Integer target = set.ceiling(current - k);
            //ceeling是获取treeset中 所有 >= current - k 的值中最小的那个， 取得的target就是我们想要的current(0~x1)
            if(target != null) {
                result = Math.max(result, current - target); //current - target就是以current为矩形下边界的矩形中最大的area值
            }
            set.add(current);
        }
        return result;
    }

}
