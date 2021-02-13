
//爬楼梯思考方式：暴力？基本情况？找最近重复子问题
//当爬三级台阶的时候，由于每次指能跨一步或跨两步，因此，只有两种可能：1. 从一级台阶跨两部到三级
//                                                            2. 从二级台阶跨一步到三级台阶
//因此可以总结 F(n)=F(n-1)+F(n-2)  -> 斐波拉契数量
public class ClimbingStairs_70 {
    //n=1 sum=1; n=2 sum=2; n=3 sum=3; n=4 sum=5; n=5 sum=8
    //可以看出 sum(n)=sum(n-1)+sum(n-2) 即斐波契数列
    //该数列求解有三种
    // 1.递归(无记忆化搜索) O(2^n)  =》优化（记忆化搜索）O（n） =》优化 尾递归 O(n) 减少空间复杂度
    // 2.矩阵快速幂
    // 3.通项公式
    public static void main(String[] args) {
        ClimbingStairs_70 test=new ClimbingStairs_70();
        System.out.println(test.climbStairs3(5));
    }

    //1.无记忆化，自顶向下递归，存在很多重复操作 会超时 结构是二叉树式扩散，时间复杂度O(2^n)
    public int climbStairs4(int n) {
        if(n==0||n==1||n==2) return n;
        else return climbStairs(n-1)+climbStairs(n-2);
    }

    //2.自底向上递归
    public int climbStairs5(int n) {
        if(n==1||n==2) return n;
        return climb(3,1,2,n);
    }

    public int climb(int level,int p1,int p2,int n){
        if(level==n) return p1+p2;
        int temp=p2;
        p2=p1+p2;
        p1=temp;
        return climb(level+1,p1,p2,n);
    }

    //迭代
    public int climbStairs(int n) {
        int sum=0;
        int p1=0;
        int p2=1;
        for(int i=1;i<=n;i++){
            sum=p1+p2;
            p1=p2;
            p2=sum;
        }
        return sum;
    }


    //矩阵快速幂O(logn)
    public int climbStairs2(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }
    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }
    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }


    //斐波那契数列1,1,2,3,5,8,...    当前数列1,2,3,5,8,.... 所以n是n+1
    // 通项公式1/sqrt(5)*[((1+sqrt(5))/2)^n-((1-sqrt(5))/2)^2]
    //通项公式求解O(1)
    public int climbStairs3(int n) {
        int sum=(int)(1/Math.sqrt(5)*(Math.pow((1+Math.sqrt(5))/2,n+1)-Math.pow((1-Math.sqrt(5))/2,n+1)));
        return sum;
    }

}
