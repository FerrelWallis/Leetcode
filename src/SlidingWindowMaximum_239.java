import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

public class SlidingWindowMaximum_239 {

    public static void main(String[] args) {
        int[] nums=new int[]{1,3,-1,-3,5,3,6,7};
        SlidingWindowMaximum_239 test=new SlidingWindowMaximum_239();
        test.maxSlidingWindow3(nums,3);
    }

    //暴力
    public int[] maxSlidingWindow3(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        int maxIndex = -1;
        int j = 0;
        for(int i = 0; i <= nums.length - k; i++){
            if(i <= maxIndex && maxIndex < i + k){  //前一个滑动块最大值在当前滑动块内，就只比较最后一个和最大值
                System.out.println(i);
                System.out.println(maxIndex);
                if(nums[maxIndex] <= nums[i+k-1]){
                    maxIndex = i+k-1;
                }
            } else {
                maxIndex = i;
                for(int m = i; m <= i + k -1; m++){
                    if(nums[maxIndex] < nums[m]) maxIndex = m;
                }
            }
            ans[j++] = nums[maxIndex];
        }
        return ans;
    }


    //1.双端队列 12ms 时间复杂度O(n),空间复杂度O(n)
    //注意点：遍历过程中，最新的值入队前循环比较队尾元素是否小于最新元素，小于最新元素全部出队，
    // 有最新元素在，它们不可能为最大值，这样就可以保证队头永远是当前窗口的最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n=nums.length;
        if(n*k==0) return new int[0];
        else if(k==1) return nums;
        else{
            ArrayDeque<Integer> deque=new ArrayDeque<>();
            int[] ans=new int[nums.length-k+1];
            for(int i=0;i<nums.length;i++){
                while(!deque.isEmpty() && nums[deque.getLast()]<nums[i]) deque.removeLast();
                deque.addLast(i);
                if(deque.getFirst()==i-k) deque.removeFirst();
                if(i>=k-1){
                    //如果当前元素到k开始往答案数组中放
                    ans[i-k+1]=nums[deque.getFirst()];
                }
            }
            return ans;
        }
    }



    //2.动态规划
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        if (n * k == 0) return new int[0];
        if (k == 1) return nums;

        int [] left = new int[n];
        left[0] = nums[0];
        int [] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            // from left to right
            if (i % k == 0) left[i] = nums[i];  // block_start
            else left[i] = Math.max(left[i - 1], nums[i]);

            // from right to left
            int j = n - i - 1;
            if ((j + 1) % k == 0) right[j] = nums[j];  // block_end
            else right[j] = Math.max(right[j + 1], nums[j]);
        }

        int [] output = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; i++)
            output[i] = Math.max(left[i + k - 1], right[i]);

        return output;
    }

}
