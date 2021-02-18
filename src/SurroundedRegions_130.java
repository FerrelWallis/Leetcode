import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class SurroundedRegions_130 {
    //130. Surrounded Regions
    //Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
    //A region is captured by flipping all 'O's into 'X's in that surrounded region.

    //Example:
    //X X X X
    //X O O X
    //X X O X
    //X O X X
    //After running your function, the board should be:

    //X X X X
    //X X X X
    //X X X X
    //X O X X
    //Explanation:
    //Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board
    //are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the
    //border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected
    //horizontally or vertically.

    //翻转O的条件：1. 不处于边缘   2. 不与处于边缘的O相连接


    //dfs
    public void solve(char[][] board) {
        int r = board.length, c = board[0].length;
        for(int i = 0; i < r; i++) {
            if(board[i][0] == 'O') dfs2(i, 0, board);
            if(board[i][c - 1] == 'O') dfs2(i, c - 1, board);
        }
        for(int j = 0; j < c; j++) {
            if(board[0][j] == 'O') dfs2(0, j, board);
            if(board[r - 1][j] == 'O') dfs2(r - 1, j, board);
        }
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(board[i][j] == '#') board[i][j] = 'O';
                if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }

    private void dfs2(int i, int j, char[][] board) {
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') return;
        board[i][j] = '#';
        int[][] ways = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for(int w = 0; w < 4; w++) {
            dfs2(i + ways[w][0], j + ways[w][1], board);
        }
    }


    //bfs
    public void solve2(char[][] board) {
        int r = board.length, c = board[0].length;
        for(int i = 0; i < r; i++) {
            if(board[i][0] == 'O') bfs(i, 0, board);
            if(board[i][c - 1] == 'O') bfs(i, c - 1, board);
        }
        for(int j = 0; j < c; j++) {
            if(board[0][j] == 'O') bfs(0, j, board);
            if(board[r - 1][j] == 'O') bfs(r - 1, j, board);
        }
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(board[i][j] == '#') board[i][j] = 'O';
                if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }

    private void bfs(int i, int j, char[][] board) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        int[][] ways = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            board[cur[0]][cur[1]] = '#';
            for(int w = 0; w < 4; w++) {
                int newi = i + ways[w][0], newj = j + ways[w][1];
                if(newi >= 0 && newi < board.length && newj >= 0 && newj < board[0].length && board[newi][newj] == 'O')
                    queue.add(new int[]{newi, newj});
            }
        }
    }


    //并查集 使用一个dummy node连接所有边界上的O，再将所有与边界O相邻的O连接上，
    //最后遍历所有，查找没有链接到dummy node的O，转变为X
    public void solve4(char[][] board) {
        int r = board.length, c = board[0].length;
        UnionFind uf = new UnionFind(r * c + 1);  //多创一个parent[r*c]多为dummy node
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(i == 0 || i == r - 1 || j == 0 || j == c - 1) uf.union(i * c + j, r * c);
                else if(board[i][j] == 'O'){
                    int[][] ways = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
                    for(int w = 0; w < 4; w++) {
                        int newi = i + ways[w][0], newj = j + ways[w][1];
                        if(board[newi][newj] == 'O') uf.union(i * c + j, newi * c + newj);
                    }
                }
            }
        }

        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(!uf.connected(r*c, i * c + j)) board[i][j] = 'X';
            }
        }

    }

    class UnionFind {
        private int[] parent;
        private int[] rank;

        public UnionFind(int nm) {
            parent = new int[nm];
            rank = new int[nm];
            for(int i = 0; i < nm; i++) {
                parent[i] = i;
            }
        }

        public int find_root(int x) {
            while (parent[x] != x) {
                x = parent[x];
            }
            return x;
        }

        public boolean union(int x, int y) {
            int xroot = find_root(x);
            int yroot = find_root(y);
            if(xroot == yroot) return false;
            else {
                if(rank[xroot] > rank[yroot]) parent[yroot] = xroot;
                else if(rank[yroot] > rank[xroot]) parent[xroot] = yroot;
                else {
                    parent[xroot] = yroot;
                    rank[yroot]++;
                }
                return true;
            }
        }

        public boolean connected(int x, int y) {
            return find_root(x) == find_root(y);
        }

    }
}
