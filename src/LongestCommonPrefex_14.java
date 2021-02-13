import java.util.Arrays;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 *
 * All given inputs are in lowercase letters a-z.
 *
 */
public class LongestCommonPrefex_14 {

    public static void main(String[] args) {
        //System.out.println("1111");
        LongestCommonPrefex_14 test=new LongestCommonPrefex_14();
        String[] strs={"flower","flow","flight","fly"};
        //String[] strs={"dog","racecar","car"};

        //System.out.println(test.brutalForce(strs));
        //System.out.println(test.brutalForce(strs));
        System.out.println(test.mytry(strs));
    }

    public String brutalForce(String[] strs) {
        if(strs==null ||strs.length==0)
            return "";
        String prefix=strs[0]; //prefix一直在减少，所以一开始直接拿第一个作为prefix
        for(String s:strs){
            //前缀，所以如果prefix与每个字符串indexof对比，不为0（不存在是-1，存在但不是前缀>0）就进行剪切操作再进行对比
            if(prefix=="") break;
            while(s.indexOf(prefix)!=0){
                if(prefix=="") break;
                prefix=prefix.substring(0,prefix.length()-1);
            }
        }
        return prefix;
    }


    public String Sort(String[] strs) {
        if(strs==null ||strs.length==0)
            return "";
        Arrays.sort(strs);
        String first=strs[0];
        String last=strs[strs.length-1];
        while(last.indexOf(first)!=0){
            if(first=="") break;
            first=first.substring(0,first.length()-1);
        }
        return first;
    }

    public String mytry(String[] strs) {
        if(strs==null || strs.length==0) return "";
        String shortest=strs[0];
        for(String s:strs){
            if(s.length()<shortest.length())
                shortest=s;
        }
        for (String s:strs){
            if(shortest=="") break;
            while (s.indexOf(shortest)!=0){
                if(shortest=="") break;
                shortest=shortest.substring(0,shortest.length()-1);
            }
        }
        return shortest;
    }


}
