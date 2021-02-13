public class Searcha2DMatrixII_240 {
//Write an efficient algorithm that searches for a value in an m x n matrix.
    //This matrix has the following properties:
    //Integers in each row are sorted in ascending from left to right.
    //Integers in each column are sorted in ascending from top to bottom.

    //Example:Consider the following matrix:
    //[
    //  [1,   4,  7, 11, 15],
    //  [2,   5,  8, 12, 19],
    //  [3,   6,  9, 16, 22],
    //  [10, 13, 14, 17, 24],
    //  [18, 21, 23, 26, 30]
    //]
    //Given target = 5, return true.
    //Given target = 20, return false.

    //1. 暴力 时间复杂度O（n*m）
    public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;
        int row = matrix.length, col = matrix[0].length;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(target == matrix[i][j]) return true;
            }
        }
        return false;
    }

    // 2. 二分查找： 从行列两方同时进行二分查找
    //矩阵已经排过序，就需要使用二分法搜索以加快我们的算法。
    //算法：
    //首先，我们确保矩阵不为空。那么，如果我们迭代矩阵对角线，从当前元素对列和行搜索，
    //我们可以保持从当前 (row,col)对开始的行和列为已排序。因此，我们总是可以二分搜索这些行和列切片。
    //我们以如下逻辑的方式进行:
    //在对角线上迭代，二分搜索行和列，直到对角线的迭代元素用完为止（意味着我们可以返回 false）
    //或者找到目标（意味着我们可以返回 true）。binary search 函数的工作原理和普通的二分搜索一样,
    //但需要同时搜索二维数组的行和列。

    //时间复杂度：O(lg(n!))。
    //这个算法产生的时间复杂度并不是特别明显的是 O(lg(n!))，所以让我们一步一步地分析它。
    //在主循环中执行的工作量逐渐最大，它运行 min(m,n)次迭代，其中m表示行数，n表示列数。
    //在每次迭代中，我们对长度为m−i和n−i的数组片执行两次二分搜索。
    //因此，循环的每一次迭代都以O(lg(m−i)+lg(n−i))时间运行，其中i表示当前迭代。
    //我们可以将其简化为O(2⋅lg(n−i))=O(lg(n−i))在最坏的情况是n≈m。当n≪m时会发生什么（不损失一般性）
    //n将在渐近分析中占主导地位。通过汇总所有迭代的运行时间，我们得到以下表达式：
    //O(lg(n)+lg(n−1)+lg(n−2)+…+lg(1))
    //然后，我们可以利用对数乘法规则（lg(a)+lg(b)=lg(ab)）将复杂度改写为：

    //O(lg(n)+lg(n−1)+lg(n−2)+…+lg(1))
    //=O(lg(n⋅(n−1)⋅(n−2)⋅…⋅1))
    //=O(lg(1⋅…⋅(n−2)⋅(n−1)⋅n))
    //=O(lg(n!))
    //空间复杂度：O(1)，因为我们的二分搜索实现并没有真正地切掉矩阵中的行和列的副本，我们可以避免分配大于常量内存。
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;
        int row = matrix.length, col = matrix[0].length;
        int shortside = Math.min(row,col);
        for(int i = 0; i < shortside; i++) {
            boolean rowsearch = binarySearch(matrix, target, i, false);
            boolean colsearch = binarySearch(matrix, target, i, true);
            if(rowsearch || colsearch) return true;
        }
        return false;
    }

    private boolean binarySearch(int[][] matrix, int target, int index, boolean vertical) {
        int lo = index, hi = vertical? matrix.length - 1 : matrix[0].length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(vertical) { //search vertically
                if(target > matrix[mid][index]) {
                    lo = mid + 1;
                } else if(target < matrix[mid][index]) {
                    hi = mid - 1;
                } else {
                    return true;
                }
            } else { //search horizontally
                if(target > matrix[index][mid]) {
                    lo = mid + 1;
                } else if(target < matrix[index][mid]) {
                    hi = mid - 1;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    //3. 空间收缩
    //空间缩减 ：特性从左下角向上向右压缩。1. 如果target小于cur，则row-1因为cur右边的都大于cur，直接上一层
    //                                 2. 如果target大于cur，则col+1,因为cur上面的都小于cur，直接由一层
    public boolean searchMatrix3(int[][] matrix, int target) {
        int row = matrix.length - 1, col = 0;
        while (row >=0 && col < matrix.length) {
            if(target < matrix[row][col]) row--;
            else if(target > matrix[row][col]) col++;
            else return true;
        }
        return false;
    }


    //4：二分 搜索空间的缩减
    //我们可以将已排序的二维矩阵划分为四个子矩阵，其中两个可能包含目标，其中两个肯定不包含。
    //算法：由于该算法是递归操作的，因此可以通过它的基本情况和递归情况的正确性来判断它的正确性。
    //基本情况 ：
    //对于已排序的二维数组，有两种方法可以确定一个任意元素目标是否可以用常数时间判断。
    //第一，如果数组的区域为零，则它不包含元素，因此不能包含目标。
    //其次，如果目标小于数组的最小值或大于数组的最大值，那么矩阵肯定不包含目标值。
    //递归情况：
    //如果目标值包含在数组内，因此我们沿着索引行的矩阵中间列，matrix[row−1][mid]<target<matrix[row][mid]，
    //（很明显，如果我们找到target，我们立即返回true）。现有的矩阵可以围绕这个索引分为四个子矩阵；
    //左上和右下子矩阵不能包含目标（通过基本情况部分来判断），所以我们可以从搜索空间中删除它们 。
    //另外，左下角和右上角的子矩阵是二维矩阵，因此我们可以递归地将此算法应用于它们。

    //时间复杂度：O(nlgn)
    //空间复杂度：O(lgn)，虽然这种方法从根本上不需要大于常量的附加内存，但是它使用递归意味着它将使用与其递归树高度成比例的内存。
    //因为这种方法丢弃了每一级递归一半的矩阵（并进行了两次递归调用），所以树的高度由lgn限定。

    private int[][] matrix;
    private int target;

    private boolean searchRec(int left, int up, int right, int down) {
        // this submatrix has no height or no width.
        if (left > right || up > down) {
            return false;
            // `target` is already larger than the largest element or smaller
            // than the smallest element in this submatrix.
        } else if (target < matrix[up][left] || target > matrix[down][right]) {
            return false;
        }

        int mid = left + (right-left)/2;

        // Locate `row` such that matrix[row-1][mid] < target < matrix[row][mid]
        int row = up;
        while (row <= down && matrix[row][mid] <= target) {
            if (matrix[row][mid] == target) {
                return true;
            }
            row++;
        }

        return searchRec(left, row, mid-1, down) || searchRec(mid+1, up, right, row-1);
    }

    public boolean searchMatrix4(int[][] mat, int targ) {
        // cache input values in object to avoid passing them unnecessarily
        // to `searchRec`
        matrix = mat;
        target = targ;

        // an empty matrix obviously does not contain `target`
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        return searchRec(0, 0, matrix[0].length-1, matrix.length-1);
    }
}
