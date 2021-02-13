import java.util.HashSet;

public class ValidSudoku_36 {
    //36. Valid Sudoku
    //Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according
    //to the following rules:
    //Each row must contain the digits 1-9 without repetition.
    //Each column must contain the digits 1-9 without repetition.
    //Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
    //Note:
    //A Sudoku board (partially filled) could be valid but is not necessarily solvable.
    //Only the filled cells need to be validated according to the mentioned rules.

    //Example 1:
    //Input: board =
    //[["5","3",".",".","7",".",".",".","."]
    //,["6",".",".","1","9","5",".",".","."]
    //,[".","9","8",".",".",".",".","6","."]
    //,["8",".",".",".","6",".",".",".","3"]
    //,["4",".",".","8",".","3",".",".","1"]
    //,["7",".",".",".","2",".",".",".","6"]
    //,[".","6",".",".",".",".","2","8","."]
    //,[".",".",".","4","1","9",".",".","5"]
    //,[".",".",".",".","8",".",".","7","9"]]
    //Output: true

    //Example 2:
    //Input: board =
    //[["8","3",".",".","7",".",".",".","."]
    //,["6",".",".","1","9","5",".",".","."]
    //,[".","9","8",".",".",".",".","6","."]
    //,["8",".",".",".","6",".",".",".","3"]
    //,["4",".",".","8",".","3",".",".","1"]
    //,["7",".",".",".","2",".",".",".","6"]
    //,[".","6",".",".",".",".","2","8","."]
    //,[".",".",".","4","1","9",".",".","5"]
    //,[".",".",".",".","8",".",".","7","9"]]
    //Output: false
    //Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.

    //Constraints:
    //board.length == 9
    //board[i].length == 9
    //board[i][j] is a digit or '.'.


    //数独valid前提 9x9的矩阵：每行 1-9不能有重复，每列1-9不能重复，每个3x3的box中1-9不能有重复
    //重点：如何将i,j下标抽象成在其所在3x3矩阵中所在位置。 3x3表格每行第一个index是0，3，6，即0*3，1*3，2*3
    // box_index = (i / 3) * 3 + j / 3,重点注意，因为是int类型，所以除法后直接取整，比如 2/3 = 0
    //i / 3表示在3x3表格中在第几行，(i / 3) * 3表示当前行第一个下标是多少，j / 3表示在3x3表格中在第几列
    //因为 行 0-8，列0-8，boxindex 0-8中不能重复，所以可以进行依次遍历完成检查
    //时间复杂度 O(9*9) = O(81) = O(1)  空间复杂度 O(9*9) = O(81) = O(1)
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowcheck = new boolean[9][9];  //第一维表示第i行， 第二维表示第cur个数字是否已存在
        boolean[][] colcheck = new boolean[9][9];  //第一维表示第j列， 第二维表示第cur个数字是否已存在
        boolean[][] boxcheck = new boolean[9][9];  //第一维表示第i个box， 第二维表示第cur个数字是否已存在
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                char cur = board[i][j];
                if(cur != '.') {
                    if(rowcheck[i][cur - '1']) return false;
                    else rowcheck[i][cur - '1'] = true;
                    if(colcheck[j][cur - '1']) return false;
                    else colcheck[j][cur - '1'] = true;
                    int boxindex = (i / 3) * 3 + j / 3;
                    if(boxcheck[boxindex][cur - '1']) return false;
                    else boxcheck[boxindex][cur - '1'] = true;
                }
            }
        }
        return true;
    }

    //一个hashset 定义行的字符串形式（i）cur，表示第i行cur这个数字是否出现过，同理定义列 cur(j)，定义box(boxindex)(cur)
    public boolean isValidSudoku2(char[][] board) {
        HashSet<String> check = new HashSet<>();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                char cur = board[i][j];
                if(cur != '.') {
                    if(!check.add("(" + i + ")" + cur)) return false;
                    if(!check.add(cur + "(" + j + ")")) return false;
                    int boxindex = (i / 3) * 3 + j / 3;
                    if(!check.add("(" + boxindex + ")" + "(" + cur + ")")) return false;
                }
            }
        }
        return true;
    }
}
