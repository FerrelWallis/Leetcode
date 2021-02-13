import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class JumpGameVI_1696 {
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

    //dp + 单调队列 子问题：将每个index作为落点，计算jump到该处最大值
    //状态定义： 当前最大值 = 前 k 个值 中的最大值 + nums[i]
    //dp方程： dp[i] = max(dp[i-1] ~ dp[i-k]) + nums[i]
    //维护 max(dp[i-1] ~ dp[i-k]) 使用单调队列queue确保队列头保持在k范围内最大值，
    //新的值加入队列时，将所有小于该值的pop出，再put，确保单调递增
    //时间复杂度O(n) 空间复杂度O(n)
    public int maxResult(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();  //这里存放的是index
        int[] dp = new int[nums.length];
        deque.addLast(0);
        dp[0] = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(deque.peekFirst() < i - k) deque.removeFirst();
            dp[i] = dp[deque.peekFirst()] + nums[i];
            while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        return dp[nums.length - 1];
    }
    
}
