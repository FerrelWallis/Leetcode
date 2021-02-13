import java.util.Stack;

public class TrappingRainWater_42 {
    //接雨水特点：当前元素的左边最高墙和右边最高墙之中较小的墙，跟当前元素的高相减，即当前位置的节水量

    public static void main(String[] args) {
        int[] height=new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingRainWater_42 test=new TrappingRainWater_42();
        test.trap(height);
    }
    //1.动态规划 时间复杂度O(n) 空间复杂度O(n) 1ms
    public int trap(int[] height) {
        if(height==null || height.length==0) return 0;
        int len=height.length;
        int[] leftmax=new int[len];
        int[] rightmax=new int[len];
        leftmax[0]=0;
        rightmax[len-1]=0;
        for(int i=1;i<len;i++){
            leftmax[i]=Math.max(leftmax[i-1],height[i-1]);
        }
        for(int i=len-2;i>=0;i--){
            rightmax[i]=Math.max(rightmax[i+1],height[i+1]);
        }
        int ans=0;
        for(int i=0;i<len;i++){
            int minus=Math.min(leftmax[i],rightmax[i])-height[i];
            System.out.println(minus);
            if(minus>0) ans+=minus;
        }
        return ans;
    }

    //2.双指针 时间复杂度O(n) 空间复杂度O(1) 在动态规划的前提下，提升空间复杂度,一次循环完成
    public int trap4(int[] height) {
        int sum=0;
        int left=0;
        int right=height.length-1;
        int leftmax=0;
        int rightmax=0;
        while (left<right){
            if(height[left]<height[right]){
                if(height[left]>=leftmax) leftmax=height[left];
                else sum+=leftmax-height[left];
                left++;
            }else{
                if(height[right]>=rightmax) rightmax=height[right];
                else sum+=rightmax-height[right];
                right--;
            }
        }
        return sum;
    }

    //3.单调栈 单调递减 4ms
    public int trap3(int[] height) {
        int len=height.length;
        Stack<Integer> stack=new Stack<>();
        int sum=0;
        int cur=0;
        while (cur<len){
            while(!stack.empty() && height[stack.peek()]<height[cur]){
                int bottom=height[stack.pop()];
                if(stack.empty()) break;
                int w=cur-stack.peek()-1;
                int h=Math.min(height[cur],height[stack.peek()])-bottom;
                sum+=w*h;
            }
            stack.push(cur++);
        }
        return sum;
    }
}
