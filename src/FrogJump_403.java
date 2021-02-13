import java.util.HashMap;
import java.util.HashSet;

public class FrogJump_403 {
    //FrogJump_403
    //A frog is crossing a river. The river is divided into some number of units, and at each unit, there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
    //
    //Given a list of stones' positions (in units) in sorted ascending order, determine if the frog can cross the river by landing on the last stone. Initially, the frog is on the first stone and assumes the first jump must be 1 unit.
    //
    //If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in the forward direction.
    //
    // 
    //
    //Example 1:
    //
    //Input: stones = [0,1,3,5,6,8,12,17]
    //Output: true
    //Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.
    //Example 2:
    //
    //Input: stones = [0,1,2,3,4,8,9,11]
    //Output: false
    //Explanation: There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.

    //动态规划 子问题：条件 第一次跳跃距离为 1，假设上一次条约距离为 k ,下一次条约距离只能是 k or k-1 or k+1,
    //               在能跳到最远的情况下，每一次jump的最远距离就是当前次数，即每一次能跳的最远距离是1，2，3，4，5....
    //状态定义：boolean dp[n+1][n+1] 第一个n+1表示 第几个石子， 第二个n+1表示跳跃的距离
    //         dp[i][k]记录的是处于第i个石子是否能跳跃 k 的距离
    //         因为能跳跃的最远距离 i 所以遍历 0-i之间所有的石子，求之间的距离 diff
    //         并查看dp[j][diff]是否为true，即表示第j个石子是否能跳跃diff的距离抵达i 如果true，则dp[i][diff / diff - 1 / diff + 1]
    //dp方程： for j in i: diff = stones[i] - stones[j]
    //                    if(符合条件) dp[i][diff / diff-1 / diff+1] = true
    //              条件：如果diff < 0 || > N || dp[j][diff]！= true 则三个可能性都不符合
    //                   diff - 1 < 0   则dp[i][diff-1] 不符合
    //                   diff + 1 > N   则dp[i][diff+1] 不符合
    //时间复杂度O(N^2) 空间复杂度O(N^2)
    public boolean canCross2(int[] stones) {
        boolean[][] dp = new boolean[stones.length][stones.length + 1];
        dp[0][1] = true;
        for(int i = 1; i < stones.length; i++) {
            for(int j = 0; j < i; j++) {
                int diff = stones[i] - stones[j];
                if(diff < 0 || diff > stones.length || !dp[j][diff]) continue;
                dp[i][diff] = true;
                if(diff - 1 >= 0) dp[i][diff - 1] = true;
                if(diff + 1 <= stones.length) dp[i][diff + 1] = true;
                if(i == stones.length - 1) return true;
            }
        }
        return false;
    }


    //2. dfs 子问题：条件 第一次跳跃距离为 1，假设上一次条约距离为 k ,下一次条约距离只能是 k or k-1 or k+1,
    //              当前石子i可以jump k的情况下 查看 stones[i] + k 是否包含在stones里面
    // 时间复杂度 O(N^3) 空间复杂度O(N)
    public boolean canCross(int[] stones) {
        //当前石子index 上一次距离 lastjump
        if(stones == null || stones.length == 0 || stones.length == 1 || stones[1] != 1) return false;
        HashMap<Integer,Integer> stonesSet = new HashMap<>();
        for(int i = 0; i < stones.length; i++) stonesSet.put(stones[i], i);
        return dfsjump(stones, stonesSet, 1, 1, new HashSet<>());
    }

    private boolean dfsjump(int[] stones, HashMap<Integer,Integer> stonesSet, int index, int lastjump, HashSet<String> visited) {
        String v = index + "," + lastjump;
        if (visited.contains(v)) return false;
        visited.add(v);
        if(index == stones.length - 1) return true;
        int cur = stones[index];
        for(int i = -1; i <= 1; i++) {
            int nextjump = lastjump + i;
            if(nextjump <= 0) continue;
            int nextstone = cur + nextjump;
            if(stonesSet.containsKey(nextstone))
                if(dfsjump(stones, stonesSet, stonesSet.get(nextstone), nextjump, visited)) return true;
        }
        return false;
    }
}
