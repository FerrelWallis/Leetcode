/*
* Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word (last word means the last appearing word if we loop from left to right) in the string.
* If the last word does not exist, return 0.
* Note: A word is defined as a maximal substring consisting of non-space characters only.
* Example:
* Input: "Hello World"
* Output: 5
*
* */


public class LengthOfLastWord_58 {

    public static void main(String[] args) {
        LengthOfLastWord_58 test=new LengthOfLastWord_58();
        String s="";
        //System.out.println(test.Split(s));
        System.out.println(test.Backward(s));
    }

    public int Split(String s) {
        //这里其实不判断也行，如果输入字符串为""，用split" "切完，words.length为1，因为""中没有空格元素
        //if(s=="" || s.length()==0) return 0;
        String[] words=s.split(" ");
        //如果输入字符串为" ",split完就没有元素了，words.length=0,下面数组下标会报错，提前判断
        if (words.length==0) return 0;
        int len=words[words.length-1].length();
        return len;
    }

    public int Backward(String s){
        //核心思想是将字符串尾部的空格去掉后，从后往前找第一个空格，字符串长度减去最后一个空格位置就是last word长度
        //特殊情况" "和"",s.trim.lastIndexOf(" ")=-1 ，+1后不影响判断
        int i=s.trim().lastIndexOf(" ")+1; //这里加一是因为之前获取的是" "的位置，从0数起；加一后从一数起
        int len=s.trim().length(); //字符串长度从1数起，这样可以直接做运算了
        return len-i;
    }



}
