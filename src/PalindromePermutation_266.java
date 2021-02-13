import java.util.HashMap;
import java.util.Iterator;

/*
Given a string, determine if a permutation of the string could form a palindrome.

Example 1:
Input: "code"
Output: false

Example 2:
Input: "aab"
Output: true

Example 3:
Input: "carerac"
Output: true
* */
public class PalindromePermutation_266 {
    public static void main(String[] args) {
        PalindromePermutation_266 test=new PalindromePermutation_266();
        System.out.println(test.canPermutePalindrome("abb"));
    }

    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int[] sArr = new int[256];
        for (int i = 0; i < s.length(); i++) {
            sArr[s.charAt(i) - ' ']++;
        }
        // 对奇数个的字符进行计数，大于2，肯定不能构成回文数
        int cnt = 0;
        for (int i : sArr) {
            if (i % 2 == 1) {
                cnt++;
                if (cnt > 1) {
                    return false;
                }
            }
        }
        return true;

    }
}
