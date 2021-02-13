
/*
Determine whether an integer is a palindrome. An integer is a palindrome when it reads
the same backward as forward.

Example 1:
Input: 121
Output: true

Example 2:
Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

Example 3:
Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

Follow up:Could you solve it without converting the integer to a string?
* */
public class PalindromeNumber_9 {
    public static void main(String[] args) {
        PalindromeNumber_9 test=new PalindromeNumber_9();
        System.out.println(test.isPalindrome(1000021));
    }

    //数学解法优化，取一半，reverse是否相等
    public boolean isPalindrome2(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false; //末尾是0，且不为0的数字定不是
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }


    //数学解法
    public boolean isPalindrome(int x) {
        if(x<0) return false;
        if(x>=0&&x<=9) return true;
        int reverse=0;
        int temp=x;
        while (temp!=0){
            reverse=reverse*10+temp%10;
            temp=temp/10;
        }

        if (reverse==x) return true;
        else return false;
    }



}
