import java.util.*;

public class JumpGameIV_1345 {
    //JumpGameIV_1345
    //Given an array of integers arr, you are initially positioned at the first index of the array.
    //In one step you can jump from index i to index:
    //i + 1 where: i + 1 < arr.length.
    //i - 1 where: i - 1 >= 0.
    //j where: arr[i] == arr[j] and i != j.
    //Return the minimum number of steps to reach the last index of the array.
    //Notice that you can not jump outside of the array at any time.

    //Example 1:
    //Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
    //Output: 3
    //Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.

    //Example 2:
    //Input: arr = [7]
    //Output: 0
    //Explanation: Start index is the last index. You don't need to jump.

    //Example 3:
    //Input: arr = [7,6,9,6,9,6,9,7]
    //Output: 1
    //Explanation: You can jump directly from index 0 to index 7 which is last index of the array.

    //Example 4:
    //Input: arr = [6,1,9]
    //Output: 2

    //Example 5:
    //Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
    //Output: 3

    //第一想法
    //dp 子问题：遍历数组，计算每一个跳到当前位置需要的最小step
    //状态定义：当前位置需要的最小step 有三种可能性，取最小的 1、 前面一个的step + 1
    //                                                  2、 后面一个的step + 1
    //                                                  3、 数组最早出现的x的step + 1   arr[x] == arr[i]
    //动态方程：dp[i] = min(dp[i + 1] + 1, dp[i - 1] + 1, dp[x] + 1) arr[x] == arr[i]
    //但是 dp[i+1] +1这一部分不好处理, 最好的处理方法是将同一个step的一起处理，这就是广度优先遍历

    //bfs广度优先遍历
    public int minJumps(int[] arr) {
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>(); // number => minstep 记录该数字第一次出现时的step
        for(int i = 1; i < arr.length; i++) {
            //// java8之前。从map中根据key获取value操作可能会有下面的操作
            //Object key = map.get("key");
            //if (key == null) {
            //    key = new Object();
            //    map.put("key", key);
            //}
            //// java8之后。上面的操作可以简化为一行，若key对应的value为空，会将第二个参数的返回值存入并返回
            //Object key2 = map.computeIfAbsent("key", k -> new Object());
            hashMap.computeIfAbsent(arr[i], k -> new LinkedList<>()).add(i);
        }
        int minstep = 0;
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        queue.add(0);
        visited.add(0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int index = queue.poll();
                if(index == arr.length - 1) return minstep;
                List<Integer> cur = hashMap.getOrDefault(arr[index], new LinkedList<>());
                cur.add(index + 1);
                cur.add(index - 1);
                for(int c : cur) {
                    if(c < arr.length && c >= 0 && !visited.contains(c)) {
                        queue.add(c);
                        visited.add(c);
                    }
                }
                cur.clear();  // avoid later lookup indicesOfValue arr[i]
                // 这里clear就是进行过一次获取相同数字的操作之后，防止下次再遇到相同数字的时候再次重复添加运算
            }
            minstep++;
        }
        return 0;
    }

}
