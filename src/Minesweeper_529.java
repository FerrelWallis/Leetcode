import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper_529 {
    //深度
    int[] x_dir={1, 0, -1, 0, 1, -1, 1, -1};
    int[] y_dir={0, 1, 0, -1, 1, 1, -1, -1};

    public char[][] updateBoard(char[][] board, int[] click) {
        int r = click[0], c= click[1];
        if(board[r][c] == 'M') board[r][c] = 'X';
        else dfs(board, r, c);
        return board;
    }

    private void dfs(char[][] board, int r, int c) {
        int tnt = 0;
        for(int i = 0; i < 8; i++) {
            int mv_r = r + x_dir[i], mv_c = c + y_dir[i];
            if(mv_r < 0 || mv_r >= board.length || mv_c < 0 || mv_c >= board[0].length) continue;
            if(board[mv_r][mv_c] == 'M') tnt++;
        }
        if(tnt > 0) {
            board[r][c] = (char) ('0' + tnt);
        } else {
            board[r][c] = 'B';
            for(int i = 0; i < 8; i++) {
                int mv_r = r + x_dir[i], mv_c = c + y_dir[i];
                if(mv_r < 0 || mv_r >= board.length || mv_c < 0 || mv_c >= board[0].length || board[mv_r][mv_c] != 'E')
                    continue;
                dfs(board, mv_r, mv_c);
            }
        }
    }


    //广度
    public char[][] updateBoard2(char[][] board, int[] click) {
        int r = click[0], c= click[1];
        if(board[r][c] == 'M') board[r][c] = 'X';
        else bfs(board, r, c);
        return board;
    }

    private void bfs(char[][] board, int r, int c) {
        Queue<Pair<Integer,Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(r, c));
        while (!queue.isEmpty()) {
            Pair<Integer,Integer> cur = queue.remove();
            int cur_r = cur.getKey(), cur_c = cur.getValue(), tnt = 0;
            for(int i = 0; i < 8; i++) {
                int new_r = cur_r + x_dir[i], new_c = cur_c + y_dir[i];
                if(new_r < 0 || new_r >= board.length || new_c < 0 || new_c >= board[0].length) continue;
                if(board[new_r][new_c] == 'M') tnt++;
            }
            if(tnt > 0) {
                board[cur_r][cur_c] = (char) ('0' + tnt);
            } else {
                board[cur_r][cur_c] = 'B';
                for(int i = 0; i < 8; i++) {
                    int new_r = cur_r + x_dir[i], new_c = cur_c + y_dir[i];
                    if(new_r < 0 || new_r >= board.length || new_c < 0 || new_c >= board[0].length || board[new_r][new_c] != 'E')
                        continue;
                    queue.add(new Pair<>(new_r, new_c));
                }
            }
        }
    }

}
