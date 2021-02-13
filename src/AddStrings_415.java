/*
给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
注意：
1. num1 和num2 的长度都小于 5100.
2. num1 和num2 都只包含数字 0-9.
3. num1 和num2 都不包含任何前导零。
4. 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
*/

public class AddStrings_415 {

    public static void main(String[] args) {

    }

    //通过carry进行进位操作
    public String Carry(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        //创建carry用于进位
        int carry = 0;
        int i = num1.length()-1;  //两个字符串的长度，从0开始
        int j = num2.length()-1;
        //当两个字符串都被全部遍历完，且进位完成
        while(i >= 0 || j >= 0 || carry != 0){
            //将两个字符串，从作为数字来看的个位开始进行运算，加到carry
            if(i>=0) carry += num1.charAt(i--)-'0';
            if(j>=0) carry += num2.charAt(j--)-'0';
            //将carry（位数之和）的个位附加到sb
            sb.append(carry%10);
            //carry=carry/10，在carry中留下十位，在下一个位数运算时加上，即进位操作
            carry /= 10;
        }
        //由于从个位开始操作，并添加到sb，因此最后需要反转
        return sb.reverse().toString();
    }





}
