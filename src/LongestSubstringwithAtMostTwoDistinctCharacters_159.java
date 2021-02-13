public class LongestSubstringwithAtMostTwoDistinctCharacters_159 {
    //159. Longest Substring with At Most Two Distinct Characters
    //Given a string s , find the length of the longest substring t that contains at most 2 distinct characters.

    //Example 1:
    //Input: "eceba"
    //Output: 3
    //Explanation: t is "ece" which its length is 3.

    //Example 2:
    //Input: "ccaabbb"
    //Output: 5
    //Explanation: t is "aabbb" which its length is 5.

    //滑动窗口(hashmap + 双指针)，right先扩张，当包含t所有元素，开始收缩left
    //时间复杂度O(n)   substring通解题目：leetcode76，3，159，340
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] hashmap = new int[128];
        int begin = 0, end = 0, valid = 2, maxlen = 0;
        while (end < s.length()) {
            char send = s.charAt(end);
            if(hashmap[send] == 0) valid--;
            hashmap[send]++;
            end++;
            while (valid < 0) {
                char sbegin = s.charAt(begin);
                hashmap[sbegin]--;
                if(hashmap[sbegin] == 0) valid++;
                begin++;
            }
            if(maxlen < end - begin) maxlen = end - begin;
        }
        return maxlen;
    }
}
