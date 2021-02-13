public class LongestSubstringwithAtMostKDistinctCharacters_340 {
    //340. Longest Substring with At Most K Distinct Characters
    //Given a string s and an integer k, return the length of the longest substring of s that contains
    //at most k distinct characters.

    //Example 1:
    //Input: s = "eceba", k = 2
    //Output: 3
    //Explanation: The substring is "ece" with length 3.

    //Example 2:
    //Input: s = "aa", k = 1
    //Output: 2
    //Explanation: The substring is "aa" with length 2.

    //Constraints:
    //1 <= s.length <= 5 * 104
    //0 <= k <= 50

    //滑动窗口(hashmap + 双指针)，right先扩张，当包含t所有元素，开始收缩left
    //时间复杂度O(n)   substring通解题目：leetcode76，3，159，340，349
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] hashmap = new int[128];
        int begin = 0, end = 0, valid = k, maxlen = 0;
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
