public class ValidPerfectSquare_367 {
    //二分 这里牛顿迭代不适用，牛顿迭代用来求近似值，不能确定是否正好完全平方
    //1 <= num <= 2^31 - 1 因为有这个限制 ，mid会超出int[-2^31,2^31-1]范围，溢出，所以要用long
    public boolean isPerfectSquare(int num) {
        if(num < 2) return true;
        long left = 2, right = num/2;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if(num == mid * mid) return true;
            else if(num < mid * mid) right = mid - 1;
            else left = mid + 1;
        }
        return false;
    }

    //牛顿迭代法
    public boolean isPerfectSquare2(int num) {
        //只有1, 2, 3，4，5，的num/2值的平方会一开始就小于num，可以直接判断x*x是否等于num
        //取根号值近似值的整数部分，之可能会有两种结果，等于根号值和小于根号值
        //因此当当前取到的近似值整数部分的平方小于等于num时就可以跳出判断
        //因为只取整数部分，如果不是perfect square，它的平方就会小于num
        if(num == 1) return true;
        long guess = num / 2;
        while (guess * guess > num) {
            guess = (guess + num / guess) / 2;
        }
        return guess * guess == num;
    }

    //数学方法：平方数特性 1 -> 1   4 -> 1+3  9 -> 1+3+5  16 -> 1+3+5+7   25 -> 1+3+5+7+9
    //不是斐波拉契数列（斐波拉契是当前数等于前两个数相加）
    public boolean isPerfectSquare3(int num) {
        long check = 1;  //long防止溢出
        long temp = 1;
        while (check < num) {
            temp += 2;
            check += temp;
        }
        return check == num;
    }

}
