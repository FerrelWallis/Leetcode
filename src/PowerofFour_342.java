public class PowerofFour_342 {
    //Given an integer n, return true if it is a power of four. Otherwise, return false.
    //An integer n is a power of four, if there exists an integer x such that n == 4x.

    //Example 1:
    //Input: n = 16
    //Output: true

    //Example 2:
    //Input: n = 5
    //Output: false

    //Example 3:
    //Input: n = 1
    //Output: true

    //Constraints: -231 <= n <= 231 - 1

    // 1. 迭代 %4 为0 的情况下 进行循环除4
    //时间复杂度O(logn)  假设n 则循环了x次   4^x = n  =>  x = log4(n)
    public boolean isPowerOfFour(int n) {
        if(n == 0) return false;
        while (n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }

    //2. 位运算，二进制有且仅有一个1，且1的位置必须为2的倍数, 即n必须是2^x (x : 0 ~ 30)
    //时间复杂度O(16), 即O(1)
    public boolean isPowerOfFour2(int n) {
        if((n & (n - 1)) == 0) {
            boolean flag = false;
            for(int i = 0; i < 31; i += 2) {
                if(Math.pow(2, i) == n) flag = true;
                if(flag) return true;
            }
            return flag;
        } else return false;
    }

    //3. 换底公式 loga(b) = logc(b) / logc(a)
    //假设输入的n是4的幂次，即 n = 4^x => x = log4(n) => x = log(n) / log(4)
    //x必为整数, 所以检查小数是否大于1e-10
    public boolean isPowerOfFour3(int n) {
        if(n == 0) return false;
        double eps = 1e-10;
        double log = Math.log10(n) / Math.log10(4);
        return (log - (int) log < eps);
    }

}
