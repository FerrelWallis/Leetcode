import java.util.*;

public class LetterCombinationsofaPhoneNumber_17 {

    /*
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that
     * the number could represent.
     * A mapping of digit to letters (just like on the telephone buttons) is given below.
     * Note that 1 does not map to any letters.
     *
     * 2abc 3def 4ghi 5jkl 6mno 7pqrs 8tuv 9wxyz
     *
     * Example:
     * Input: "23"
     * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     *
     * Note: Although the above answer is in lexicographical order, your answer could be in any order you want.
     */
    //回溯法
    public List<String> ans=new ArrayList<>();
    HashMap<Character,String> map=new HashMap<>();
    public List<String> letterCombinations(String digits) {
        if(digits=="" || digits.length()==0) return ans;
        int len=digits.length();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
        helper(digits.toCharArray(),0,new StringBuilder());
        return ans;
    }

    private void helper(char[] digits, int cur,StringBuilder sb) {
        if(cur>=digits.length) {
            ans.add(sb.toString());
            return;
        }
        char[] letters=map.get(digits[cur]).toCharArray();
        for(char l:letters){
            sb.append(l);
            helper(digits,cur+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    //先入先出栈 广度优先遍历
    //队列实现先入先出
    public List<String> letterCombinations2(String digits) {
        LinkedList<String> ans=new LinkedList<>();
        if(digits.isEmpty() || digits.length()==0) return ans;
        String[] map=new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i=0;i<digits.length();i++){
            int digi=Character.getNumericValue(digits.charAt(i));
            while (ans.peek().length()==i){
                String temp=ans.remove();
                for (char c:map[digi].toCharArray()){
                    ans.add(temp+c);
                }
            }
        }
        return ans;
    }

    //FIFO对列简化
    public List<String> letterCombinations3(String digits) {
        LinkedList<String> ans=new LinkedList<>();
        if(digits.isEmpty() || digits.length()==0) return ans;
        String[] map = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        while (ans.peek().length()!=digits.length()){
            String temp=ans.remove();
            for (char c:map[digits.charAt(temp.length())-'0'].toCharArray()){
                ans.add(temp+c);
            }
        }
        return ans;
    }


}
