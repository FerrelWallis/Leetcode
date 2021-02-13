public class Searcha2DMatrix_74 {
    //单二分 将二维数组看作一维数组，因为作为一维数组依然是单调递增 left, right 进行 i * col + j 这样的运算
    //时间复杂度O(log(nm))
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0) return false;
        int row = matrix.length, col = matrix[0].length, left = 0, right = row * col - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int mid_i = mid / col;
            int mid_j = mid % col;
            if(target == matrix[mid_i][mid_j]) return true;
            else if(target > matrix[mid_i][mid_j]) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }

    //双二分
    public boolean searchMatrix2(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int r_left = 0, r_right = row - 1;
        while (r_left < r_right) {
            int mid = (r_right + r_left) / 2;
            if(target > matrix[mid][col - 1]) r_left = mid + 1;
            else if(target < matrix[mid][col - 1]) r_right = mid;
            else return true;
        }
        int c_left = 0, c_right = col - 1;
        while (c_left <= c_right) {
            int mid = (c_right + c_left) / 2;
            if(target == matrix[r_left][mid]) return true;
            else if(target > matrix[r_left][mid]) c_left = mid + 1;
            else c_right = mid - 1;
        }
        return false;
    }
}
