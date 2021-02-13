import java.util.LinkedList;
import java.util.Stack;

public class LongestValidParentheses_32 {
    public static void main(String[] args) {
        LongestValidParentheses_32 test=new LongestValidParentheses_32();
        System.out.println(test.longestValidParentheses("()(()"));
    }

    //32. Longest Valid Parentheses
    //Given a string containing just the characters '(' and ')', find the length of the longest valid
    //(well-formed) parentheses substring.

    //Example 1:
    //Input: s = "(()"
    //Output: 2
    //Explanation: The longest valid parentheses substring is "()".

    //Example 2:
    //Input: s = ")()())"
    //Output: 4
    //Explanation: The longest valid parentheses substring is "()()".

    //Example 3:
    //Input: s = ""
    //Output: 0

    //stack栈 存入的是左括号的下标， 计算距离即为合法数量, stack.peek是离得最近的 （非法/尚未配对的括号下标）
    public int longestValidParentheses4(String s) {
        LinkedList<Integer> stack = new LinkedList<>();
        int result = 0;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '(') {
                stack.pop();
                result = Math.max(result, i - stack.peek());
            } else {
                stack.push(i);
            }
        }
        return result;
    }


    //动态规划  子问题：valid parentheses有两种情况 ()() 和 (()) ，即当前（）内部的合法数+ 外部的合法数 + 2
    //          在当前为）的时候结算，所以（的直接为0
    //状态定义：当前为）
    //              前一个为（时，累加2， 累加到前两个的sum上， dp[i] = dp[i-2] + 2  满足()()的情况
    //              前一个为）时，查看 i - dp[i-1] -1 是否为(, 如果是，表示是(())的情况
    //              dp[i] = dp[i-1] + dp[i-dp[i-1]-2] + 2    dp[i-1]表示内部合法数，dp[i-dp[i-1]-2]表示外部合法数
    //动态转移方程：if(i == ')')  1. if(i-1 == '(') dp[i] = dp[i-2] + 2
    //                          2. if(i-1 == ')') dp[i] = dp[i-1] + dp[i-dp[i-1]-2] + 2
    public int longestValidParentheses(String s) {
        int len = s.length(), ans = 0;
        int[] dp = new int[len];
        char[] ss = s.toCharArray();
        for(int i = 0; i < len; i++) {
            if(ss[i] == ')') {
                if(ss[i - 1] == '(' && i - 1 >= 0) {  //考虑 i-1 小于0 的情况直接取 0
                    dp[i] = ((i - 2 >= 0)? dp[i - 2] : 0) + 2; //考虑 i-2 小于0 的情况直接取 0
                    ans = Math.max(ans, dp[i]);
                } else {
                    //考虑 i-dp[i-1]-2 小于0 的情况直接取 0
                    dp[i] = dp[i - 1] + ((i - dp[i - 1] - 2 >= 0)? dp[i - dp[i - 1] - 2] : 0) + 2;
                    ans = Math.max(ans, dp[i]);
                }
            }
        }
        return ans;
    }

    //优化 转移方程 1 可以去掉，因为前一个为(，意味着内部没有valid数，dp[i-1] = 0，只需外部 + 2
    //dp[i] = 0 + dp[i-0-2] + 2 => dp[i] = dp[i-2] + 2也就是说 转移方程 1 包含在方程 2 中了
    public int longestValidParentheses2(String s) {
        int len = s.length(), ans = 0;
        int[] dp = new int[len];
        char[] ss = s.toCharArray();
        for(int i = 0; i < len; i++) {
            if(ss[i] == ')') {
                dp[i] = dp[i - 1] + ((i - dp[i - 1] - 2 >= 0)? dp[i - dp[i - 1] - 2] : 0) + 2;
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }


}
