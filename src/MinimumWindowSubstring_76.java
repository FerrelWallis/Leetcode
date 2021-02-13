import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MinimumWindowSubstring_76 {
    //Given two strings s and t, return the minimum window in s which will contain all the characters in t. If there is no such window in s that covers all characters in t, return the empty string "".
    //
    //Note that If there is such a window, it is guaranteed that there will always be only one unique minimum window in s.
    //
    // 
    //
    //Example 1:
    //
    //Input: s = "ADOBECODEBANC", t = "ABC"
    //Output: "BANC"
    //Example 2:
    //
    //Input: s = "a", t = "a"
    //Output: "a"
    // 
    //
    //Constraints:
    //
    //1 <= s.length, t.length <= 105
    //s and t consist of English letters.
    // 
    //
    //Follow up: Could you find an algorithm that runs in O(n) time?
    //滑动窗口(hashmap + 双指针)，right先扩张，当包含t所有元素，开始收缩left
    //时间复杂度O(n)   substring通解题目：leetcode76，3，159，340
    class Solution {
        public String minWindow(String s, String t) {
            HashMap<Character, Integer> need = new HashMap<Character, Integer>();
            HashMap<Character, Integer> window = new HashMap<>();
            for (char c :  t.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);

            int left = 0, right = 0;
            int valid = 0;
            // 记录最小覆盖字串的起始索引及长度
            int start = 0, len = Integer.MAX_VALUE;
            while (right < s.length()) {
                char c = s.charAt(right);
                right++;
                // 判断取出的字符是否在字串中
                if (need.containsKey(c)) {
                    window.put(c, window.getOrDefault(c,0) + 1);
                    if (window.get(c).equals(need.get(c))) {
                        valid++;
                    }
                }

                // 判断是否需要收缩（已经找到合适的覆盖串）
                while (valid == need.size()) {
                    if (right - left < len) {
                        start = left;
                        len = right - left;
                    }

                    char c1 = s.charAt(left);
                    left++;
                    if (need.containsKey(c1)) {
                        if (window.get(c1).equals(need.get(c1))) {
                            valid--;
                        }
                        window.put(c1, window.getOrDefault(c1, 0) - 1);
                    }

                }
            }

            return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
        }
    }


}
