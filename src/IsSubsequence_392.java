
/*
Given a string s and a string t, check if s is subsequence of t.
A subsequence of a string is a new string which is formed from the original string by
deleting some (can be none) of the characters without disturbing the relative positions
of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check
one by one to see if T has its subsequence. In this scenario, how would you change your code?

Example 1:
Input: s = "abc", t = "ahbgdc"
Output: true

Example 2:
Input: s = "axc", t = "ahbgdc"
Output: false 

Constraints:
0 <= s.length <= 100
0 <= t.length <= 10^4
Both strings consists only of lowercase characters.
* */
public class IsSubsequence_392 {
    public static void main(String[] args) {
        IsSubsequence_392 test=new IsSubsequence_392();
        System.out.println(test.isSubsequence("bcd","uuuubcd"));
    }

    public boolean isSubsequence(String s, String t) {
        if(s.equals("")) return true;
        if(t.length()==s.length()&&!s.equals(t)||s.length()>t.length()) return false;
        int temp=t.indexOf(s.charAt(0))+1;
        for(int i=1;i<s.length();i++){
            char cur=s.charAt(i);
            int r = t.indexOf(cur,temp);
            if(r<0) return false;
            temp=r+1;
        }
        return true;
    }
}
