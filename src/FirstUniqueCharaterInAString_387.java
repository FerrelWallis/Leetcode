/*
* Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
* Examples:
* s = "leetcode"
* return 0.
* s = "loveleetcode",
* return 2.
* Note: You may assume the string contain only lowercase letters.
* */

import java.util.HashMap;

public class FirstUniqueCharaterInAString_387 {
    public static void main(String[] args) {
        FirstUniqueCharaterInAString_387 test=new FirstUniqueCharaterInAString_387();
        String s = "loveleetcode";
        System.out.println(test.LinearTimeComplexity(s));
        System.out.println(test.Count_26(s));
    }

    public int Count_26(String s){
        //设定初始index值为-1
        int index=-1;
        //遍历26字母
        for(char c='a';c<='z';c++){
            //获取字母对应在字符串中第一次出现的位置
            int begin=s.indexOf(c);
            //如果字母在字符串中第一次出现和最后一次出现的位置相同，且不为-1(-1表示没出现)，说明字母只出现了一次
            if(s.indexOf(c)==s.lastIndexOf(c) && begin!=-1){
                //两种情况可以直接将字母的初始位置赋值给index, 第一次赋值(index没被赋过值)，begin小于之前字母的index
                if(index==-1 || begin<index)
                    index=begin;
            }
        }

        return index;
    }


    public int LinearTimeComplexity(String s) {
        //线性时间复杂度使用hashmap
        HashMap<Character,Integer> count=new HashMap<Character,Integer>();

        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            //getOrDefault查看是否存在key，存在即取值，不存在就使用默认值
            count.put(c,count.getOrDefault(c,0)+1);
        }

        for(int i=0;i<s.length();i++){
            if(count.get(s.charAt(i))==1)
                return i;
        }

        return -1;
    }


    /*
    *  线性时间复杂度解法
    * 这道题最优的解法就是线性复杂度了？？？为了保证每个元素是唯一的，至少得把每个字符都遍历一遍。
    * 算法的思路就是遍历一遍字符串，然后把字符串中每个字符出现的次数保存在一个散列表中。
    * 这个过程的时间复杂度为 O(N)，其中 N 为字符串的长度。
    * 接下来需要再遍历一次字符串，这一次利用散列表来检查遍历的每个字符是不是唯一的。
    * 如果当前字符唯一，直接返回当前下标就可以了。第二次遍历的时间复杂度也是 O(N)。
    *
    * 复杂度分析
    * 时间复杂度： O(N)
    * 只遍历了两遍字符串，同时散列表中查找操作是常数时间复杂度的。
    * 空间复杂度： O(N))
    * 用到了散列表来存储字符串中每个元素出现的次数。
    * */


}
