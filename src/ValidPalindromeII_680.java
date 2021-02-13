
/*
Given a non-empty string s, you may delete at most one character.
Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True

Example 2:
Input: "abca"
Output: True
Explanation: You could delete the character 'c'.

Note:
The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
* */
public class ValidPalindromeII_680 {
    public static void main(String[] args) {
        ValidPalindromeII_680 test=new ValidPalindromeII_680();
        System.out.println(test.validPalindrome("abccda"));
    }

    //递归算法
    boolean flag=true;
    public boolean validPalindrome(String s) {
        int left=0,right=s.length()-1;
        while(left<right){
            if(s.charAt(left)==s.charAt(right)){
                left++;
                right--;
            }else {
                if(flag){
                    flag=false;
                    return validPalindrome(s.substring(left,right)) || validPalindrome(s.substring(left+1,right+1));
                }else return false;
            }
        }
        return true;
    }

}
