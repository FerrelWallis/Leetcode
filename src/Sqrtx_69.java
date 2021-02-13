public class Sqrtx_69 {
    public int mySqrt(int x) {
        int left = 0, right = x, ans = -1;
        //左右向内夹逼到最后是相邻的两个值,2-3这种情况下
        //最后一次循环,如果x小于右边界,则答案必为左值,实现去小数的操作 => 2.8->2
        //如果x等于右边界,才取右边界
        while (left <= right) {
            //防止溢出
            int mid = left + (left - right) / 2;
            if(x < mid * mid) right = mid - 1;
            else {
                ans = mid;
                left = mid + 1;
            }
        }
        return ans;
    }

    //牛顿迭代法
    // 算法原理：不断用 (x,f(x))的切线来逼近方程 x^2-a=0 的根。根号 a 实际上就是 x^2-a=0 的一个正实根，这个函数的导数是 2x。
    // 也就是说，函数上任一点 (x,f(x)) 处的切线斜率是 2x。那么，x−f(x)/(2x) 就是一个比 x 更接近的近似值。
    // 代入 f(x)=x^2-a 得到 x-(x^2-a)/(2x)，也就是 (x+a/x)/2。 x 是猜测值 a 是求根的值。
    public int mySqrt2(int x) {
        if(x == 0) return 0;
        return (int) sqrt((double) x, x);
    }

    private double sqrt(double cur, int x) {
        double next = (cur + (x / cur)) / 2;
        if ((int)next == (int)cur) return cur; //当下一个近似值的整数与当前一致说明答案整数部分已得出
        else return sqrt(next, x);
    }

}
