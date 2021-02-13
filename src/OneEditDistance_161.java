/*
* Given two strings s and t, determine if they are both one edit distance apart.
* Note: 
* There are 3 possiblities to satisify one edit distance apart:
* Insert a character into s to get t
* Delete a character from s to get t
* Replace a character of s to get t
*
Example 1:
Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.

Example 2:
Input: s = "cab", t = "ad"
Output: false
Explanation: We cannot get t from s by only one step.

Example 3:
Input: s = "1203", t = "1213"
Output: true
Explanation: We can replace '0' with '1' to get t.
* */
public class OneEditDistance_161 {



    public boolean isOneEditDistance(String s, String t) {
        int ns=s.length();
        int nt=t.length();

        //确保s是较短或相等的那个
        if(ns>nt) return isOneEditDistance(t,s);
        //长的字符串长度大于短的超过1，就直接false
        if(nt-ns>1) return false;

        for(int i=0;i<ns;i++){
            System.out.println(ns);
            if(s.charAt(i)!=t.charAt(i)){
                if(s.length()==t.length())
                    return s.substring(i+1).equals(t.substring(i+1));
                else
                    return s.substring(i).equals(t.substring(i+1));
            }

        }

        //如果在s的长度里没有不同，且t比s多一个字符，则true,否则表名两字符串完全相同false
        return ns==nt-1;
    }




}
