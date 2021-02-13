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
    public void solve3(char[][] board) {
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
    public void solve3(char[][] board) {
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


    //687. Longest Univalue Path
    //Given the root of a binary tree, return the length of the longest path, where each node in the
    //path has the same value. This path may or may not pass through the root.
    //The length of the path between two nodes is represented by the number of edges between them.

    //Example 1:
    //Input: root = [5,4,5,1,1,5]
    //Output: 2

    //Example 2:
    //Input: root = [1,4,5,4,4,5]
    //Output: 2

    //Constraints:
    //The number of nodes in the tree is in the range [0, 104].
    //-1000 <= Node.val <= 1000
    //The depth of the tree will not exceed 1000.

    //dfs  计算当前节点作为最高点，向左右延伸的最大值路径和，存放入ans
    //     dfs返回当前节点左右路径之间的最大值，作为其父节点的延伸路径
    //相同思路的题：leetcode124
    private int ans = 0;
    public int longestUnivaluePath(TreeNode root) {
        max_length(root);
        return ans;
    }

    private int max_length(TreeNode root) {
        if (root == null) return 0;
        int leftlen = max_length(root.left);
        int rightlen = max_length(root.right);
        int curleft = 0, curright = 0;
        if (root.left != null && root.left.val == root.val) curleft = leftlen + 1;
        if (root.right != null && root.right.val == root.val) curright = rightlen + 1;
        ans = Math.max(ans, curleft + curright);
        return Math.max(leftlen, rightlen);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    //124. Binary Tree Maximum Path Sum
    //A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the
    //sequence has an edge connecting them. A node can only appear in the sequence at most once.
    //Note that the path does not need to pass through the root.
    //The path sum of a path is the sum of the node's values in the path.
    //Given the root of a binary tree, return the maximum path sum of any path.

    //Example 1:
    //Input: root = [1,2,3]
    //Output: 6
    //Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

    //Example 2:
    //Input: root = [-10,9,20,null,null,15,7]
    //Output: 42
    //Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.

    //Constraints:
    //    The number of nodes in the tree is in the range [1, 3 * 104].
    //    -1000 <= Node.val <= 1000
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */

    //dfs  计算当前节点作为最高点，向左右延伸的最大值路径和，存放入ans
    //     dfs返回当前节点左右路径之间的最大值，作为其父节点的延伸路径
    //相同思路的题：leetcode687
    class Solution {
        public int maxPathSum(TreeNode root) {

            return 0;
        }

        public class TreeNode {
            int val;
            leet.TreeNode left;
            leet.TreeNode right;
            TreeNode() {}
            TreeNode(int val) { this.val = val; }
            TreeNode(int val, leet.TreeNode left, leet.TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }
    }




    //TaskScheduler_621
    //Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents
    //a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit
    //of time, the CPU could complete either one task or just be idle.
    //However, there is a non-negative integer n that represents the cooldown period between two same tasks 
    //(the same letter in the array), that is that there must be at least n units of time between any two same
    //tasks. Return the least number of units of times that the CPU will take to finish all the given tasks.

    //Example 1:
    //Input: tasks = ["A","A","A","B","B","B"], n = 2
    //Output: 8
    //Explanation:
    //A -> B -> idle -> A -> B -> idle -> A -> B
    //There is at least 2 units of time between any two same tasks.

    //Example 2:
    //Input: tasks = ["A","A","A","B","B","B"], n = 0
    //Output: 6
    //Explanation: On this case any permutation of size 6 would work since n = 0.
    //["A","A","A","B","B","B"]
    //["A","B","A","B","A","B"]
    //["B","B","B","A","A","A"]
    //...
    //And so on.

    //Example 3:
    //Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
    //Output: 16
    //Explanation:
    //One possible solution is
    //A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A

    //只需考虑idle数量，答案就是tasks数量+idel数量，如果idel数量 <= 0 说明任务完全可以填满所有的空闲时间，idel直接取0
    //最大重复的char数量max , maxcount为字符数量为max的字符数量   比如： AAABBBCD  max = 3  maxcount = 2
    // 初始idel数 = (max -1) * (n - (maxcount - 1))
    // 可用来填充idel的任务数量 available = task.length - max * maxcount
    //时间复杂度O(N) 空间复杂度O(26)
    public int leastInterval(char[] tasks, int n) {
        int[] counter = new int[26];
        int max = 0, maxcount = 0, len = tasks.length;
        for(int i = 0; i < tasks.length; i++) {
            counter[tasks[i] - 'A']++;
            if(counter[tasks[i] - 'A'] == max) maxcount++;
            else if(counter[tasks[i] - 'A'] > max) {
                max = counter[tasks[i] - 'A'];
                maxcount = 1;
            }
        }
        int originidle = (max - 1) * (n - (maxcount - 1));
        int available = len - max * maxcount;
        int idle = Math.max(0, originidle - available);
        return len + idle;
    }

    //解法2：priority queue + hashmap
    public int leastInterval2(char[] tasks, int n) {
        Queue<Character> queue = new LinkedList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++) {

        }
        return 0;
    }

}
