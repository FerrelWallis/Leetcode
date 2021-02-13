import java.util.LinkedList;
import java.util.Queue;

public class JumpGameIII_1306 {
    //1306. Jump Game III
    //Given an array of non-negative integers arr, you are initially positioned at start index of the array.
    //When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
    //Notice that you can not jump outside of the array at any time.

    //Example 1:
    //Input: arr = [4,2,3,0,3,1,2], start = 5
    //Output: true
    //Explanation:
    //All possible ways to reach at index 3 with value 0 are:
    //index 5 -> index 4 -> index 1 -> index 3
    //index 5 -> index 6 -> index 4 -> index 1 -> index 3

    //Example 2:
    //Input: arr = [4,2,3,0,3,1,2], start = 0
    //Output: true
    //Explanation:
    //One possible way to reach at index 3 with value 0 is:
    //index 0 -> index 4 -> index 1 -> index 3

    //Example 3:
    //Input: arr = [3,0,2,1,2], start = 2
    //Output: false
    //Explanation: There is no way to reach at index 1 with value 0.

    //bfs
    public boolean canReach(int[] arr, int start) {
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[arr.length];
        queue.add(start);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int index = queue.poll();
                if(visited[index] == 1) continue;
                if(arr[index] == 0) return true;
                visited[index] = 1;
                if(index + arr[index] >= arr.length) queue.add(index + arr[index]);
                if(index - arr[index] < 0) queue.add(index - arr[index]);
            }
        }
        return false;
    }

    //dfs
    public boolean canReach2(int[] arr, int start) {
        if(start < 0 || start >= arr.length || arr[start] < 0) return false;
        if(arr[start] == 0) return true;
        arr[start] = -arr[start];
        return canReach2(arr, start + arr[start]) || canReach2(arr, start - arr[start]);
    }


}
