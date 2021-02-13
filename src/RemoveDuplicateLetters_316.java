import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/*
* Given a string which contains only lowercase letters,
* remove duplicate letters so that every letter appears once and only once.
* You must make sure your result is the smallest in lexicographical order
* among all possible results.

Example 1:
Input: "bcabc"
Output: "abc"

Example 2:
Input: "cbacdcbc"
Output: "acdb"

Note: This question is the same as 1081: 
https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
*
* */
public class RemoveDuplicateLetters_316 {

    //贪心算法 每次递归中，在保证其他字符至少出现一次的情况下，确定最小左侧字符。
    // 之后再将未处理的后缀字符串继续递归。
    //*时间复杂度：O(N)O(N)。 每次递归调用占用 O(N)O(N) 时间。
    // 递归调用的次数受常数限制(只有26个字母)，最终复杂度为 O(N) * C = O(N)O(N)∗C=O(N)。

    //*空间复杂度：O(N)O(N)，每次给字符串切片都会创建一个新的字符串（字符串不可变），
    // 切片的数量受常数限制，最终复杂度为 O(N) * C = O(N)O(N)∗C=O(N)。
    public String removeDuplicateLetters(String s) {
        // find pos - the index of the leftmost letter in our solution
        // we create a counter and end the iteration once the suffix doesn't have each unique character
        // pos will be the index of the smallest character we encounter before the iteration ends
        int[] cnt = new int[26];
        int pos = 0;
        //获取字符串中所有字符出现频率
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            //获取字符串中最小的字符的位置放入pos，对比当前字符是否比pos小
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            //比较完成后将频率-1，如果减完为0说明此字符串后面的无需继续比较，即使有更小的也不能放在它前面
            cnt[s.charAt(i) - 'a']=cnt[s.charAt(i) - 'a']-1;
            if (cnt[s.charAt(i) - 'a'] == 0) break;
        }
        // our answer is the leftmost letter plus the recursive call on the remainder of the string
        // note that we have to get rid of further occurrences of s[pos] to ensure that there are no duplicates
        //判断当前字符串是否为空，即全部比较完成，不再进行递归。
        //当前字符串不为空（不代表下一递归字符串不为空），则将当前最小字符拼接
        // 并且切下当前最小字符串后面的部分,变更且将后面所有当前最小字符都删掉
        // ""+char->String
        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll(""+s.charAt(pos), ""));

    }



    //贪心算法-用栈实现
    //用栈来存储最终返回的字符串，并维持字符串的最小字典序。
    //每遇到一个字符，如果这个字符不存在于栈中，就需要将该字符压入栈中，否则就skip。
    //但在压入之前，需要先将之后还会出现，并且字典序比当前字符大的栈顶字符移除，然后再将当前字符压入。
    //时间复杂度：O(N)O(N)。虽然外循环里面还有一个内循环，但内循环的次数受栈中剩余字符总数的限制，
    // 因此最终复杂度仍为 O(N)O(N)。
    //
    //空间复杂度：O(1)O(1)。看上去空间复杂度像是 O(N)O(N)，但这不对！
    // 首先， seen 中字符不重复，其大小受字母表大小的限制。
    // 其次，只有 stack 中不存在的元素才会被压入，因此 stack 中的元素也唯一。
    // 所以最终空间复杂度为 O(1)O(1)。
    public String removeDuplicateLetters2(String s) {

        //创建栈存放最终答案
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            // we can only try to add c if it's not already in our solution
            // this is to maintain only one of each character
            //如果stack中没有当前字符，说明该字符还没存入栈中，进行下一步判断，否则就跳过
            if (!stack.contains(c)){
                while(!stack.isEmpty() && c < stack.peek() && s.indexOf(stack.peek(),i) != -1){
                    //如果栈不为空，且栈顶字符大于当前字符，且栈顶字符再当前位置之后仍然存在（即该字符之后还会出现）
                    //则将栈顶字符pop出，并且将栈顶字符从seen中删除，再将当前字符放入栈
                    //若不符则跳过，直接放入stack
                    stack.pop();
                }
                //将字符放入栈，并且记录到seen中
                stack.push(c);
            }
        }

        //获取到答案stack形式，转为string 返回
        StringBuilder sb = new StringBuilder(stack.size());
        for (Character c : stack) sb.append(c.charValue());
        return sb.toString();
    }



}
