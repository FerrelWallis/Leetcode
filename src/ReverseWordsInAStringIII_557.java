
/*
Given a string, you need to reverse the order of characters in each word within
a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"

Note: In the string, each word is separated by single space and there will not be
any extra space in the string.
*
* */
public class ReverseWordsInAStringIII_557 {

    public static void main(String[] args) {
        ReverseWordsInAStringIII_557 test=new ReverseWordsInAStringIII_557();
        System.out.println(test.reverseWords2("the sky is blue"));
    }

    //使用java给的方法，split reverse 7ms
    public String reverseWords2(String s) {
        String words[] = s.split(" ");
        StringBuilder res=new StringBuilder();
        for (String word: words)
            res.append(new StringBuffer(word).reverse().toString() + " ");
        return res.toString().trim();
    }


    //双指针3-5ms
    public String reverseWords(String s) {
        char[] cs=s.toCharArray();
        int left=0;
        int right=0;
        while(right<s.length()){
            if(cs[right]!=' ') right++;
            else {
                reverse(cs,left,right-1);
                right++;
                left=right;
            }
        }
        reverse(cs,left,right-1);
        return new String(cs);
    }

    public void reverse(char[] s,int left,int right){
        while (left<right){
            char temp=s[left];
            s[left++]=s[right];
            s[right--]=temp;
        }
    }

}
