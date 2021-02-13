import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
* Given an input string, reverse the string word by word.
Example 1:
Input: "the sky is blue"
Output: "blue is sky the"

Example 2:
Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:
Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space
in the reversed string.

Note:
A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces.
However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
* */
public class ReverseWordsInAString_151 {
    public static void main(String[] args) {
        ReverseWordsInAString_151 test=new ReverseWordsInAString_151();
        String s="hello  ww!";
        System.out.println(test.reverseWords(s));
    }


    //1ms 切割
    public String reverseWords(String s) {
        String ss=s.trim(); //trim可以去除字符串头部和尾部的空格
        if(ss.length()==0) return ""; //s为“”或者只含有空格的情况，直接return
        String[] words=ss.split(" ");
        StringBuilder sb=new StringBuilder(words.length);
        for(int i=words.length-1;i>=0;i--){
            if(words[i].length()==0) continue;
            sb.append(words[i]).append(" ");
        }
        if(sb.length()>0) sb.deleteCharAt(sb.length()-1);  //最后一个word append之后要去除append的空格
        return sb.toString();
    }
}
