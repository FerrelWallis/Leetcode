import javafx.util.Pair;

import java.util.*;

public class NumberofIslandsII_305 {
    //深
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new LinkedList<>();
        int[][] grid=new int[m][n];
        for(int[] pos : positions) {
            int r = pos[0], c = pos[1];
            grid[r][c]=1;
            ans.add(numIslands(grid,m,n));
        }
        return ans;
    }

    private int numIslands(int[][] grid, int m, int n) {
        int count=0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    count++;
                    dfs(grid, i, j, m, n);
                }
            }
        }
        return count;
    }

    private void dfs(int[][] grid, int i, int j, int m, int n) {
        if(i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == 1) {
            grid[i][j] = 0;
            dfs(grid, i-1, j, m, n);
            dfs(grid, i+1, j, m, n);
            dfs(grid, i, j-1, m, n);
            dfs(grid, i, j+1, m, n);
        }
    }


    //广
    public List<Integer> numIslands2_2(int m, int n, int[][] positions) {
        List<Integer> ans = new LinkedList<>();
        int[][] grid = new int[m][n];
        for(int[] pos : positions) {
            int r = pos[0], c = pos[1];
            grid[r][c] = 1;
            ans.add(bfs(grid, m, n));
        }
        return ans;
    }

    private int bfs(int[][] grid, int m, int n) {
        int count = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    count++;
                    Queue<Pair<Integer,Integer>> queue=new LinkedList<>();
                    queue.add(new Pair<>(i,j));
                    while (!queue.isEmpty()) {
                        Pair<Integer,Integer> cur = queue.remove();
                        int r = cur.getKey(), c = cur.getValue();
                        if(r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1) {
                            grid[r][c] = 0;
                            queue.add(new Pair<>(r + 1, c));
                            queue.add(new Pair<>(r - 1, c));
                            queue.add(new Pair<>(r, c + 1));
                            queue.add(new Pair<>(r, c - 1));
                        }
                    }
                }
            }
        }
        return count;
    }

    //并查集
    class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        //构造
        public UnionFind(int N) {
            count = 0;
            parent = new int[N];
            rank = new int[N];
            for(int i = 0; i < N; i++) {
                parent[i] = -1;
                rank[i] = 0;
            }
        }

        //find
        public int find(int x) {
            if(parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        //union
        public void union(int x,int y) {
            int root_x = find(x);
            int root_y = find(y);
            if(root_x!=root_y) {
                if(rank[root_x] > rank[root_y]) parent[root_y] = root_x;
                else if(rank[root_x] < rank[root_y]) parent[root_x] = root_y;
                else {
                    parent[root_x] = root_y;
                    rank[root_y] += 1;
                }
                count--;
            }
        }

        //isValid
        public boolean isValid(int x) {
            return parent[x]!=-1;
        }

        //setParent
        public void setParent(int x) {
            parent[x] = x;
            count++;
        }

        //getCount
        public int getCount() {
            return count;
        }

    }

    public List<Integer> numIslands2_3(int m, int n, int[][] positions) {
        List<Integer> ans = new LinkedList<>();
        UnionFind uf = new UnionFind(m * n);
        for(int[] pos : positions) {
            int r = pos[0], c = pos[1];
            uf.setParent(r * n + c);
            if(r < m && uf.isValid((r + 1) * n + c)) uf.union(r * n + c, (r + 1) * n + c);
            if(r >= 0 && uf.isValid((r - 1) * n + c)) uf.union(r * n + c, (r - 1) * n + c);
            if(c < n && uf.isValid(r * n + c + 1)) uf.union(r * n + c, r * n + c + 1);
            if(c >= 0 && uf.isValid(r * n + c - 1)) uf.union(r * n + c, r * n + c - 1);
            ans.add(uf.getCount());
        }
        return ans;
    }
}
