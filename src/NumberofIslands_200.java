import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

import static javafx.scene.input.KeyCode.Q;

public class NumberofIslands_200 {

    //深度优先遍历 时间复杂度O(mn)
    public int numIslands(char[][] grid) {
        if(grid==null || grid.length==0) return 0;
        int sum=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='1'){
                    sum++;
                    fload(grid,i,j);
                }
            }
        }
        return sum;
    }

    //dfs
    private void fload(char[][] grid, int row, int col) {
        if(row<0 || col<0 || row>=grid.length || col>=grid[0].length || grid[row][col]=='0') return;
        grid[row][col]='0';
        fload(grid,row-1,col);
        fload(grid,row+1,col);
        fload(grid,row,col-1);
        fload(grid,row,col+1);
    }


    //广度优先遍历 时间复杂度O(mn) 空间复杂度O(min(m,n))
    public int numIslands2(char[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1'){
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    private void bfs(char[][] grid, int i, int j){
        Queue<int[]> list = new LinkedList<>();
        list.add(new int[] { i, j });
        while(!list.isEmpty()){
            int[] cur = list.remove();
            i = cur[0]; j = cur[1];
            if(0 <= i && i < grid.length && 0 <= j && j < grid[0].length && grid[i][j] == '1') {
                grid[i][j] = '0';
                list.add(new int[] { i + 1, j });
                list.add(new int[] { i - 1, j });
                list.add(new int[] { i, j + 1 });
                list.add(new int[] { i, j - 1 });
            }
        }
    }

    //深
    public int numIslands_2(char[][] grid) {
        int r = grid.length, c = grid[0].length, count = 0;
        if(grid==null || grid.length==0) return count;
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                int cur=grid[i][j];
                if(cur=='1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int row, int col) {
        int r=grid.length;
        int c=grid[0].length;
        if(row < 0 || row >= r || col < 0 || col >= c || grid[row][col]=='0') return;
        grid[row][col]='0';
        dfs(grid,row-1,col);
        dfs(grid,row+1,col);
        dfs(grid,row,col+1);
        dfs(grid,row,col-1);
    }


    //广
    public int numIslands2_2(char[][] grid) {
        int r = grid.length, c = grid[0].length, count = 0;
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(grid[i][j]=='1') {
                    count++;
                    Queue<Pair<Integer,Integer>> queue = new LinkedList<>();
                    queue.add(new Pair<>(i,j));
                    while (!queue.isEmpty()) {
                        Pair<Integer,Integer> cur = queue.remove();
                        int curr=cur.getKey();
                        int curc=cur.getValue();
                        grid[curr][curc]='0';
                        if(curr - 1 >= 0 && grid[curr - 1][curc] == '1') queue.add(new Pair<>(curr - 1,curc));
                        if(curr + 1 < r && grid[curr + 1][curc] == '1') queue.add(new Pair<>(curr + 1,curc));
                        if(curc + 1 < c && grid[curr][curc + 1] == '1') queue.add(new Pair<>(curr,curc + 1));
                        if(curc - 1 >= 0 && grid[curr][curc - 1] == '1') queue.add(new Pair<>(curr,curc - 1));
                    }
                }
            }
        }
        return count;
    }


    //UnionFind
    class UnionFind{
        int count=0;
        int[] parent;
        int[] rank;
        //构造
        public UnionFind(char[][] grid) {
            count=0;
            int r = grid.length, c = grid[0].length;
            parent = new int[r * c];
            rank = new int[r * c];
            for(int i = 0; i < r; i++) {
                for(int j = 0; j < c; j++) {
                    if(grid[i][j] == '1') {
                        parent[i * c + j] = i * c + j;
                        rank[i * c + j] = 0;
                        count++;
                    }
                }
            }
        }

        //find 路径压缩
        public int find(int i) {
            if(parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }

        //union
        public void union(int x, int y) {
            int root_x = find(x);
            int root_y = find(y);
            if(root_x != root_y) {
                if(rank[root_x] < rank[root_y]) {
                    parent[root_x] = root_y;
                } else if(rank[root_x] > rank[root_y]) {
                    parent[root_y] = root_x;
                } else {
                    parent[root_y] = root_x;
                    rank[root_x] += 1;
                }
                count--;
            }
        }

        //getCount
        public int getCount() {
            return count;
        }
    }

    public int numIslands3(char[][] grid) {
        int r = grid.length, c = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(grid[i][j] == '1') {
                    if(i - 1 >= 0 && grid[i - 1][j] == '1') uf.union(i * c + j, (i - 1) * c + j);
                    if(i + 1 < r && grid[i + 1][j] == '1') uf.union(i * c + j, (i + 1) * c + j);
                    if(j - 1 >= 0 && grid[i][j - 1] == '1') uf.union(i * c + j, i * c + j - 1);
                    if(j + 1 < c && grid[i][j + 1] == '1') uf.union(i * c + j, i * c + j + 1);
                }
            }
        }
        return uf.getCount();
    }
}
