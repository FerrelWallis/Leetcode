
//这题的特点：1. 相同情况下，区域距离越远越好
//          2. 区域受制于较短的那边
public class ContainerWithMostWater_11 {
    public static void main(String[] args) {
        ContainerWithMostWater_11 test=new ContainerWithMostWater_11();
        int[] height=new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(test.maxArea2(height));
    }
    //由于这题的特点双指针法可用，且最佳O(n)
    // 1. 相同情况下，区域距离越远越好  =>  因此初始情况下左指针和右指针分别在最左和最右，分别向里移动确保面积最大
    // 2. 区域受制于较短的那边  =>  因此固定较大的那边不动，移动较短的那边
    //                            因为移动较大的那边只会增加面积减小的可能性，而较小的那边限制了面积增大的可能性
    //常用左右夹逼法
    public int maxArea2(int[] height) {
        int left=0,right=height.length-1;
        int max=0;
        while(left<right){
            int minheight=height[left]>height[right]? height[right--]:height[left++];
            max=Math.max((right-left)*minheight,max);
        }
        return max;
    }



    //暴力法O(n^2)
    public int maxArea(int[] height) {
        int max=0;
        for(int i=0;i<height.length;i++){
            for(int j=i+1;j<height.length;j++){
                int w=j-i;
                int h=Math.min(height[i],height[j]);
                int area=w*h;
                max=area>max? area:max;
            }
        }
        return max;
    }
}
