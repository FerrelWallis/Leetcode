import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleinHistogram_84 {
    public static void main(String[] args) {
        LargestRectangleinHistogram_84 test=new LargestRectangleinHistogram_84();
        int[] height=new int[]{2,1,5,6,2,3};
        System.out.println(test.largestRectangleArea(height));
    }

    //动态规划(目前最优) 2ms 时间复杂度O(n) 空间复杂度O(n)
    public static int largestRectangleArea3(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        //存放左边比它小的下标
        int[] leftLess = new int[height.length];
        //存放右边比它小的下标
        int[] rightLess = new int[height.length];
        rightLess[height.length - 1] = height.length;
        leftLess[0] = -1;

        //动态规划（当前元素的结果可由上一级元素的结果获得），计算每个柱子左边比它小的柱子的下标
        //如果当前元素的左元素小于当前元素，当前的左哨兵为左元素
        //如果左元素大于当前元素，则p为左元素的哨兵，再判断左元素的哨兵是否大于当前元素，大于则p为哨兵的哨兵
        for (int i = 1; i < height.length; i++) {
            int p = i - 1;
            while (p >= 0 && height[p] >= height[i]) {
                p = leftLess[p];
            }
            leftLess[i] = p;
        }
        //计算每个柱子右边比它小的柱子的下标
        for (int i = height.length - 2; i >= 0; i--) {
            int p = i + 1;
            while (p < height.length && height[p] >= height[i]) {
                p = rightLess[p];
            }
            rightLess[i] = p;
        }
        int maxArea = 0;
        //以每个柱子的高度为矩形的高，计算矩形的面积。
        for (int i = 0; i < height.length; i++) {
            maxArea = Math.max(maxArea, height[i] * (rightLess[i] - leftLess[i] - 1));
        }
        return maxArea;
    }


    //单调栈常数优化，从左往右一次循环，当前元素入栈前，循环比较栈顶元素，如果栈顶元素小于当前元素，则为该元素左哨兵
    //当元素出栈时，表示当前即将入栈的元素小于该元素，即为该元素右哨兵
    //从左往右一次遍历结束时，仍有元素留在栈内，剩余元素还未确定右哨兵，右哨兵都为height.length
    //因此每个元素出栈时计算面积并比较  时间复杂度O(n) 空间复杂度O(n) 10ms
    public int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int len=heights.length;
        int[] left=new int[len];
        int[] right=new int[len];
        Arrays.fill(right, len); //将所有右哨兵初始化为最右，最后栈内没有pop的元素的右哨兵皆为最右
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<len;i++){
            while (!stack.isEmpty() && heights[stack.peek()]>=heights[i]){
                right[stack.pop()]=i;
            }
            left[i]=stack.empty()? -1:stack.peek();
            stack.push(i);
        }
        int ans=0;
        for(int i=0;i<len;i++){
            ans=Math.max(ans,heights[i]*(right[i]-left[i]-1));
        }
        return ans;
    }

    //单调栈，三次循环，时间复杂度O(n),空间复杂度O(n) 13ms
    //每个元素必定会入栈，当栈内为空或当前元素大于栈顶元素，当前元素直接入栈，且当前元素的哨兵为栈顶元素下标
    //当前元素小于等于栈顶元素，栈顶元素弹出，循环再次比较当前元素和栈顶元素，直到栈空或者栈顶元素小于当前元素，
    //则表示栈顶元素是该元素的哨兵
    public int largestRectangleArea(int[] heights) {
        int[] left=new int[heights.length];
        int[] right=new int[heights.length];

        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<heights.length;i++){
            while(!stack.empty() && heights[stack.peek()]>=heights[i]){
                stack.pop();
            }
            left[i]=(stack.empty()? -1:stack.peek());
            stack.push(i);
        }
        stack.clear();
        for(int i=heights.length-1;i>=0;i--){
            while (!stack.empty() && heights[stack.peek()]>=heights[i]){
                stack.pop();
            }
            right[i]=(stack.empty()? heights.length:stack.peek());
            stack.push(i);
        }
        int ans=0;
        for(int i=0;i<heights.length;i++){
            ans=Math.max(ans,heights[i]*(right[i]-left[i]-1));
        }
        return ans;
    }






}
