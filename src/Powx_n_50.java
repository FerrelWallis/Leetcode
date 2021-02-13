public class Powx_n_50 {
    //1.暴力 O(n) 超时
    public double myPow(double x, int n) {
        double ans=1;
        for(int i=0;i<Math.abs(n);i++){
            ans=x*ans;
        }
        if(n<0) return 1/ans;
        else return ans;
    }

    //2.分治思想（特殊递归）时间复杂度O（logn）
    public double myPow2(double x, int n) {
        return n>0? powhelper(x,n) : 1/powhelper(x,n);
    }

    private double powhelper(double x, int n) {
        if(n==0) return 1.0;
        double next=powhelper(x,n/2);
        return n%2==0 ? next*next : next*next*x;
    }
}
