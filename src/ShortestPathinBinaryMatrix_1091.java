import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ShortestPathinBinaryMatrix_1091 {
    //1091. Shortest Path in Binary Matrix
    //In an N by N square grid, each cell is either empty (0) or blocked (1).
    //
    //A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
    //
    //Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
    //C_1 is at location (0, 0) (ie. has value grid[0][0])
    //C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
    //If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
    //Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
    //
    //
    //
    //Example 1:
    //
    //Input: [[0,1],[1,0]]
    //
    //
    //Output: 2
    //
    //Example 2:
    //
    //Input: [[0,0,0],[1,1,0],[1,1,0]]
    //
    //
    //Output: 4
    //
    //
    //
    //Note:
    //
    //1 <= grid.length == grid[0].length <= 100
    //grid[r][c] is 0 or 1

    //bfs
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        if (grid[0][0] == 1 || grid[n - 1][m - 1] == 1) return -1;
        if (n == 1 && m == 1) return 1;
        int[] dx = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
        grid[0][0] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int[] data = queue.poll();
                if (data[0] == n - 1 && data[1] == m - 1) return count;
                for (int j = 0; j < 8; j++) {
                    int x = data[0] + dx[j];
                    int y = data[1] + dy[j];
                    if (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == 0 ) {
                        queue.add(new int[]{x, y});
                        grid[x][y] = 1;
                    }
                }
            }
        }
        return -1;
    }

    //A*

        int n;
        public int shortestPathBinaryMatrix2(int[][] grid) {
            n = grid.length;
            if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;
            if (n == 1) return 1;
            int[][] dir = {
                    {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                    {-1, 1}, {-1, -1}, {1, -1}, {1, 1}
            };
            Node start = new Node(0, 0, grid[0][0] = 1);
            Queue<Node> queue = new PriorityQueue<>();
            queue.offer(start);
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                int step = grid[node.x][node.y];
                for (int[] d : dir) {
                    int x = node.x + d[0];
                    int y = node.y + d[1];
                    if (x == n - 1 && y == n - 1) return step + 1;
                    if (x < 0 || x >= n || y < 0 || y >= n) continue;
                    if (grid[x][y] != 0 && grid[x][y] <= step + 1) continue;
                    Node next = new Node(x, y, grid[x][y] = step + 1);
                    queue.offer(next);
                }
            }
            return -1;
        }

        class Node implements Comparable<Node> {
            int x;
            int y;
            int f;

            public Node(int x, int y, int step) {
                this.x = x;
                this.y = y;
                int distance = Math.max(n - 1 - x, n - 1 - y);
                this.f = distance + step;
            }

            @Override
            public int compareTo(Node o) {
                return this.f - o.f;
            }
        }


}
