
/*
* The count-and-say sequence is the sequence of integers with the first five terms as following:
1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence. You can do so recursively, in other words from the previous member read off the digits, counting the number of digits in groups of the same digit.
Note: Each term of the sequence of integers will be represented as a string.

Example 1:

Input: 1
Output: "1"
Explanation: This is the base case.
Example 2:

Input: 4
Output: "1211"
Explanation: For n = 3 the term was "21" in which we have two groups "2" and "1", "2" can be read as "12" which means frequency = 1 and value = 2, the same way "1" is read as "11", so the answer is the concatenation of "12" and "11" which is "1211".

*
* */
public class CountandSay_38 {

    public static void main(String[] args) {
        CountandSay_38 test=new CountandSay_38();
        System.out.println(test.countAndSay2(2));
    }

    //递归
    public String countAndSay(int n) {
        StringBuilder say=new StringBuilder();
        String s="1";
        if(n==1) return s;

        String last=countAndSay(n-1);
        char x=last.charAt(0);
        int curr=0;
        int i=0;
        for(i=0;i<last.length();i++){
            if(last.charAt(i)!=x){
                say.append(i-curr).append(x);
                x=last.charAt(i);
                curr=i;
            }
//            if(i==last.length()-1)
//                say.append(i-curr+1).append(x);
        }
        say.append(i-curr).append(x); //最后一段判断可以写在循环内，但会增加一定运算时间，每次都要判断
        return say.toString();
    }

    //迭代（与递归方法相同，形式不同）
    public String countAndSay2(int n) {
        StringBuilder say=new StringBuilder();
        String s="1";
        if(n==1) return s;
        else
            for(int i=0;i<n-1;i++)
                s=say(s);
        return s;
    }

    public String say(String last){
        StringBuilder say=new StringBuilder();
        char x=last.charAt(0);
        int curr=0;
        int i=0;
        for(i=0;i<last.length();i++){
            if(last.charAt(i)!=x){
                say.append(i-curr).append(x);
                x=last.charAt(i);
                curr=i;
            }
        }
        say.append(i-curr).append(x);
        return say.toString();
    }
}
