/*
* 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom
* 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
* (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。
* 杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
* 注意：你可以假设两个字符串均只含有小写字母。
* canConstruct("a", "b") -> false
* canConstruct("aa", "ab") -> false
* canConstruct("aa", "aab") -> true
*/

import java.util.HashMap;

public class RansomNote_383 {

    public static void main(String[] args) {

        RansomNote_383 test=new RansomNote_383();

        System.out.println(test.canConstruct("a", "b"));
        System.out.println(test.canConstruct("aa", "ab"));
        System.out.println(test.canConstruct("aa", "aab"));


    }

    //哈希表解法
    public boolean canConstruct(String ransomNote, String magazine) {

        if(ransomNote.length()>magazine.length()) return false;

        //创建哈希表存储magzine中赎金信对应字符的位置
        HashMap<Character,Integer> index=new HashMap<Character,Integer>();
        //循环获取ransom中的字符
        for(char c:ransomNote.toCharArray()){
            //ransom中字符在magzine中的位置，第一次从0开始寻找，之前已经有相同字符出现，则从之前字符位置开始查找
            int i=magazine.indexOf(c,index.getOrDefault(c,0));
            //如果查找不到则返回false
            if(i==-1) return false;
            //查得到就将该字符和当前查到的，它在magzine中位置的后面一个位置放入index，下依次查找就从后一个位置找起
            index.put(c,i+1);
        }
        //如果全部循环完都没有return false则true
        return true;

    }

}
