

/*
Given a string and an integer k, you need to reverse the first k characters for
every 2k characters counting from the start of the string. If there are less than
k characters left, reverse all of them. If there are less than 2k but greater than
or equal to k characters, then reverse the first k characters and left the other as original.

Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"

Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]
* */
public class ReverseStringII_541 {
    public static void main(String[] args) {
        ReverseStringII_541 test=new ReverseStringII_541();
        System.out.println(test.reverseStr("abcdefgh",3));
    }

    public String reverseStr(String s, int k) {
        char[] string=s.toCharArray();
        int left=0;
        int right=left+k-1;
        while(left<s.length()-1){
            if(right>s.length()-1){  //剩余数量小于k
                reverse(string,left,s.length()-1);
                break;
            } else {
                reverse(string,left,right);
                left=left+2*k;
                right=left+k-1;
            }
        }
        return String.valueOf(string);
    }

    public void reverse(char[] s,int left,int  right){
        while(left<right){
            char temp=s[left];
            s[left++]=s[right];
            s[right--]=temp;
        }
    }
}
