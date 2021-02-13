

/*
* Given an input string , reverse the string word by word. 

Example:
Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]

Note: 
A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces.
The words are always separated by a single space.
Follow up: Could you do it in-place without allocating extra space?
* */
public class ReverseWordsInAStringII_186 {
    public static void main(String[] args) {
        ReverseWordsInAStringII_186 test=new ReverseWordsInAStringII_186();
        char[] s=new char[]{'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        test.reverseWords(s);
    }

    //两次反转，第一次里面word反转，第二次整体反转
    public void reverseWords(char[] s) {
        int left=0;
        int right=0;
        while(right<s.length){
            //只有遇到空格才会进行上一个word的反转，所以最后一个word不会进行翻转
            if(s[right]!=' ') right++;
            else{
                reverse(s,left,right-1);
                right++;
                left=right;
            }
        }
        //手动添加最后一个word的反转，left,right，已就绪
        reverse(s,left,right-1);
        reverse(s,0,s.length-1);
        System.out.println(String.valueOf(s));
    }

    public void reverse(char[] s,int left,int right){
        while(left<right){
            char temp=s[left];
            s[left++]=s[right];
            s[right--]=temp;
        }
    }

}
