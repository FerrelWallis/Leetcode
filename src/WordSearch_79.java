public class WordSearch_79 {
    //79. Word Search
    //Given an m x n board and a word, find if the word exists in the grid.
    //The word can be constructed from letters of sequentially adjacent cells, where "adjacent" cells
    //are horizontally or vertically neighboring. The same letter cell may not be used more than once.

    //Example 1:
    //Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
    //Output: true

    //Example 2:
    //Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
    //Output: true

    //Example 3:
    //Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
    //Output: false

    //Constraints:
    //m == board.length
    //n = board[i].length
    //1 <= m, n <= 200
    //1 <= word.length <= 103
    //board and word consists only of lowercase and uppercase English letters.

    //dfs
    public boolean exist(char[][] board, String word) {
        int row = board.length, col = board[0].length;
        boolean ans = false;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(board[i][j] == word.charAt(0))
                    ans = ans || dfs(i, j, 0, word, board);
            }
        }
        return ans;
    }

    //四向扩散
    private boolean dfs(int row, int col, int index, String word, char[][] board) {
        if(index == word.length() - 1) {
            if(board[row][col] == word.charAt(index)) return true;
            else return false;
        }
        boolean ans = false;
        if(board[row][col] == word.charAt(index)) {
            char temp = board[row][col];
            board[row][col] = '#';
            if(row - 1 >= 0 && board[row - 1][col] != '#') ans = ans || dfs(row - 1, col, index + 1, word, board);
            if(row + 1 < board.length && board[row + 1][col] != '#') ans = ans || dfs(row + 1, col, index + 1, word, board);
            if(col - 1 >= 0 && board[row][col - 1] != '#') ans = ans || dfs(row, col - 1, index + 1, word, board);
            if(col + 1 < board[0].length && board[row][col + 1] != '#') ans = ans || dfs(row, col + 1, index + 1, word, board);
            board[row][col] = temp;
        }
        return ans;
    }
}
