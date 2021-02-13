
/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters
and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:
Input: "A man, a plan, a canal: Panama"
Output: true

Example 2:
Input: "race a car"
Output: false
* */
public class ValidPalindrome_125 {

    public static void main(String[] args) {
        ValidPalindrome_125 test=new ValidPalindrome_125();
        System.out.println(test.isPalindrome(".,"));
    }

    //暴力法 30ms 自顶向下编程
    public boolean isPalindrome2(String s) {
        String filterS=filterString(s);
        String reverseS=reverseString(filterS);
        return filterS.equalsIgnoreCase(reverseS);
    }

    private String filterString(String s) {
        return s.replaceAll("[^A-Za-z0-9]","");
    }

    private String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }


    //双指针 3ms
    public boolean isPalindrome(String s) {
        if(s.length()==0||s.length()==1) return true;
        char[] string=s.toLowerCase().toCharArray();
        int left=0;
        int right=s.length()-1;
        while(left<right){
            //数字0-9，ask48-57，小写字母a-z，ask97-122
            while(left<=s.length()-1&&!check(string[left])) left++;
            while(right>=0&&!check(string[right])) right--;
            if(left>right) break;
            if(string[left]!=string[right]) return false;
            left++;
            right--;
        }
        return true;
    }


    public boolean check(char s){
        int c=(int) s;
        if((c>=48&&c<=57)||(c>=97&&c<=122))
            return true;
        else return false;
    }
}
