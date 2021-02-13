public class JumpGameV_1340 {
    //1340. Jump Game V
    //Given an array of integers arr and an integer d. In one step you can jump from index i to index:

    //    i + x where: i + x < arr.length and 0 < x <= d.
    //    i - x where: i - x >= 0 and 0 < x <= d.

    //In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all
    //indices k between i and j (More formally min(i, j) < k < max(i, j)).
    //You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.
    //Notice that you can not jump outside of the array at any time.

    //Example 1:
    //Input: arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
    //Output: 4
    //Explanation: You can start at index 10. You can jump 10 --> 8 --> 6 --> 7 as shown.
    //Note that if you start at index 6 you can only jump to index 7. You cannot jump to index 5 because 13 > 9.
    //You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 > 9.
    //Similarly You cannot jump from index 3 to index 2 or index 1.

    //Example 2:
    //Input: arr = [3,3,3,3,3], d = 3
    //Output: 1
    //Explanation: You can start at any index. You always cannot jump to any index.

    //Example 3:
    //Input: arr = [7,6,5,4,3,2,1], d = 1
    //Output: 7
    //Explanation: Start at index 0. You can visit all the indicies.

    //Example 4:
    //Input: arr = [7,1,7,1,7,1], d = 2
    //Output: 2

    //Example 5:
    //Input: arr = [66], d = 1
    //Output: 1

    //1. 动态规划 自顶向下 通过(DFS + Memoization)实现  时间复杂度 O(nd)  空间复杂度 O(n)
    //子问题：当前作为起点可跳跃的最大值 => 可跳跃位置作为起点时，可跳跃的最大值 + 1
    //状态定义：
    //动态方程：dp[i] = max(dp[i +- k]) + 1   -d < k < d 且 arr[k] < arr[i]
    //这里要实现 max(dp[i +- k]) 可以用到 dfs
    public int maxJumps(int[] arr, int d) {
        int[] dp = new int[arr.length];
        int res = 0;
        for(int i = 0; i < arr.length; i++) res = Math.max(res, dfs(arr, d, i, dp));
        return res;
    }

    private int dfs(int[] arr, int d, int index, int[] dp) {
        int res = 0;
        if(dp[index] != 0) return dp[index];
        for(int i = index - 1; i >= index - d && i >= 0 && arr[index] > arr[i]; i--)
            res = Math.max(res, dfs(arr, d, i, dp));
        for(int i = index + 1; i <= index + d && i <= arr.length - 1 && arr[index] > arr[i]; i++)
            res = Math.max(res, dfs(arr, d, i, dp));
        dp[index] = res + 1;
        return dp[index];
    }

    //2. 自底向上 时间复杂度 O(n) 空间复杂度 O(n)
    //自顶向下的思考角度时当前的位置作为起点，开始向外延伸。
    //而自底向上的动态规划思考角度是将当前位置作为最终跳到的节点，找比当前节点大的点往回跳
    //子问题：当前作为起点可跳跃的最大值 => 可跳跃位置作为起点时，可跳跃的最大值 + 1
    //状态定义：
    //动态方程：dp[i] = max(dp[i +- k]) + 1   -d < k < d 且 arr[k] < arr[i]
    //we start at a low height and try to go to the higher heights.
    //The best strategy at any index is to either go to the next just-higher height to the left
    //or to the next just-higher height to the right as long as they're within d distance.
    //We can calculate next higher element to the left and to the right using monotone stack technique.
    //(Essentially: For each element, we pop all the elements less than or equal to it
    //and then push the current element into stack so that stack contains monotonically decreasing elements
    //(in end to top direction) at all times.)
    //We can use top down DP with memoization after we have figured out left and right elements for all indices
    //or we can combine results calculation during the left and right element search process itself (refer below code).
    public int maxJumps2(int[] arr, int d) {
        int n = arr.length, res = 0, top = 0;
        int[] dp = new int[n], stack = new int[n];
        for(int i = 0; i <= n; i++) {
            while(top > 0 && (i == n || arr[stack[top - 1]] < arr[i])) {
                int r = top - 1, l = r - 1;
                while(l >= 0 && arr[stack[l]] == arr[stack[r]]) l--;
                for(int j = l + 1; j <= r; j++) {
                    if(l >= 0 && stack[j] - stack[l] <= d) dp[stack[l]] = Math.max(dp[stack[l]], 1 + dp[stack[j]]);
                    if(i < n && i - stack[j] <= d) dp[i] = Math.max(dp[i], 1 + dp[stack[j]]);
                }
                top -= r - l;
            }
            stack[top++] = i;
        }
        for(int i = 0; i < n; i++) res = Math.max(res, dp[i]);
        return res + 1;
    }
}
