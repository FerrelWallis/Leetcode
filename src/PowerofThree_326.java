public class PowerofThree_326 {
    //Given an integer n, return true if it is a power of three. Otherwise, return false.
    //An integer n is a power of three, if there exists an integer x such that n == 3x.

    //Example 1:
    //Input: n = 27
    //Output: true

    //Example 2:
    //Input: n = 0
    //Output: false

    //Example 3:
    //Input: n = 9
    //Output: true

    //Example 4:
    //Input: n = 45
    //Output: false

    //Constraints:-231 <= n <= 231 - 1

    //1. %3 为0 的情况下（没有余数，即可以被3整除，但要注意不等于是3的幂次，比如21(3*7) , 21 % 3 = 0）
    //再循环除3  时间复杂度O(logn)  假设n 则循环了x次   3^x = n  =>  x = log3(n)
    public boolean isPowerOfThree(int n) {
        if(n == 0) return false;
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    //2. 利用int型最大限制
    //在 java 32位int类型(最大值是 2^31 - 1, 最小值是 -2^31)中 3 的最大幂次 是 3^19
    //所以看n是否为3的幂次，就只要看 3^19 是否能被 n整除，排除n是0的特殊条件
    //时间复杂度O(1)
    public boolean isPowerOfThree2(int n) {
        return (n > 0 && Math.pow(3,19) % n == 0)? true : false;
    }

    //3. 换底公式 loga(b) = logc(b) / logc(a)
    //假设输入的n是3的幂次，即 n = 3^x => x = log3(n) => x = log(n) / log(3)
    //x必为整数, 所以检查小数是否大于1e-10
    public boolean isPowerOfThree3(int n) {
        if(n == 0) return false;
        double epi = 1e-20;
        double log = Math.log10(n) / Math.log10(3);
        return (log - (int) log < epi)? true : false;
    }


}
