public class LongestSubstringWithoutRepeatingCharacters_3 {
    //3. Longest Substring Without Repeating Characters
    //Given a string s, find the length of the longest substring without repeating characters.

    //Example 1:
    //Input: s = "abcabcbb"
    //Output: 3
    //Explanation: The answer is "abc", with the length of 3.

    //Example 2:
    //Input: s = "bbbbb"
    //Output: 1
    //Explanation: The answer is "b", with the length of 1.

    //Example 3:
    //Input: s = "pwwkew"
    //Output: 3
    //Explanation: The answer is "wke", with the length of 3.
    //Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

    //Example 4:
    //Input: s = ""
    //Output: 0

    //Constraints:
    //0 <= s.length <= 5 * 104
    //s consists of English letters, digits, symbols and spaces.

    //滑动窗口(hashmap + 双指针)，right先扩张，当包含t所有元素，开始收缩left
    //时间复杂度O(n)   substring通解题目：leetcode76，3，159，340
    //hashset包含s中所有character且仅有一次
    //移动end查看hashset是否存在该元素，存在表示第一次出现，hashset.remove该元素, end++
    //如果不存在，说明已经第二次出现，
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> hashSet = new HashSet<>();
        int begin = 0, end = 0, maxlen = 0;
        boolean invalid = false;
        for(char ss : s.toCharArray()) hashSet.add(ss);
        while (end < s.length()) {
            char send = s.charAt(end);
            if(!hashSet.contains(send)) invalid = true;
            hashSet.remove(send);
            end++;
            while (invalid) {
                char sbegin = s.charAt(begin);
                if(sbegin == send) invalid = false;
                else hashSet.add(sbegin);
                begin++;
            }
            if(maxlen < end - begin) maxlen = end - begin;
        }
        return maxlen;
    }
}
