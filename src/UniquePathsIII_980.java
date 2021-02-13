public class UniquePathsIII_980 {
    //980. Unique Paths III
    //On a 2-dimensional grid, there are 4 types of squares:
    //    1 represents the starting square.  There is exactly one starting square.
    //    2 represents the ending square.  There is exactly one ending square.
    //    0 represents empty squares we can walk over.
    //    -1 represents obstacles that we cannot walk over.
    //Return the number of 4-directional walks from the starting square to the ending square,
    //that walk over every non-obstacle square exactly once.

    //Example 1:
    //Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
    //Output: 2
    //Explanation: We have the following two paths:
    //1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
    //2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)

    //Example 2:
    //Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
    //Output: 4
    //Explanation: We have the following four paths:
    //1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
    //2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
    //3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
    //4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)

    //Example 3:
    //Input: [[0,1],[2,0]]
    //Output: 0
    //Explanation:
    //There is no path that walks over every empty square exactly once.
    //Note that the starting and ending square can be anywhere in the grid.

    //暴力 dfs+回溯
    public int res = 0, startx = 0, starty = 0;
    public int uniquePathsIII(int[][] grid) {
        int empty = 1; //如果由[1,2]这种情况，empty初始化必须为1
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 0) empty++;
                if(grid[i][j] == 1) {
                    startx = i;
                    starty = j;
                }
            }
        }
        recur(grid, startx, starty, empty);
        return res;
    }

    //将已经走过的cell改为-2
    private void recur(int[][] grid, int curx, int cury, int empty) {
        // 小于0 包含(已走过-2和obstacle-1)||
        if(curx < 0 || curx >= grid.length || cury < 0 || cury >= grid[0].length || grid[curx][cury] < 0 ) return;
        if(grid[curx][cury] == 2) {
            if(empty == 0) res++;
            return;
        }
        grid[curx][cury] = -2;
        recur(grid, curx + 1, cury, empty - 1);
        recur(grid, curx - 1, cury, empty - 1);
        recur(grid, curx, cury + 1, empty - 1);
        recur(grid, curx, cury - 1, empty - 1);
        // 因为4个recur是一个接一个完成的，第一个recur在往下drilldown的时候会改变变量环境，
        // 因此他们往下drilldown的时候要恢复环境，否则在后面几个recur运行时，grid就是被第一个污染的环境了
        // 同理如果empty是一个环境变量或者变量对象，也需要回溯，但这里empty是作为参数且传的不是变量对象，而是数字，不用回溯
        grid[curx][cury] = 0;
    }
}
