import java.util.HashMap;
import java.util.HashSet;

/*
Find the length of the longest substring T of a given string (consists of lowercase letters only)
such that every character in T appears no less than k times.

Example 1:
Input:s = "aaabb", k = 3
Output:3
The longest substring is "aaa", as 'a' is repeated 3 times.

Example 2:
Input:s = "ababbc", k = 2
Output:5
The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
* */
public class LongestSubstringwithAtLeastKRepeatingCharacters_395 {

    public static void main(String[] args) {
        LongestSubstringwithAtLeastKRepeatingCharacters_395 test=new LongestSubstringwithAtLeastKRepeatingCharacters_395();
        System.out.println(test.longestSubstring("abcdedghijklmnopqrstuvwxyz",2));


    }


    //分支递归双指针
    public int longestSubstring2(String s, int k) {
        int len = s.length();
        if (len == 0 || k > len) return 0;
        if (k < 2) return len;

        return count(s.toCharArray(), k, 0, len - 1);
    }

    private static int count(char[] chars, int k, int p1, int p2) {
        if (p2 - p1 + 1 < k) return 0;
        int[] times = new int[26];  //  26个字母
        //  统计出现频次
        for (int i = p1; i <= p2; ++i) {
            ++times[chars[i] - 'a'];
        }
        //  如果该字符出现频次小于k，则不可能出现在结果子串中
        //  分别排除，然后挪动两个指针
        while (p2 - p1 + 1 >= k && times[chars[p1] - 'a'] < k) {
            ++p1;
        }
        while (p2 - p1 + 1 >= k && times[chars[p2] - 'a'] < k) {
            --p2;
        }

        if (p2 - p1 + 1 < k) return 0;
        //  得到临时子串，再递归处理
        for (int i = p1; i <= p2; ++i) {
            //  如果第i个不符合要求，切分成左右两段分别递归求得
            if (times[chars[i] - 'a'] < k) {
                return Math.max(count(chars, k, p1, i - 1), count(chars, k, i + 1, p2));
            }
        }
        return p2 - p1 + 1;
    }




    //分治递归，拆分字串6ms
    public int count=0;
    public int longestSubstring(String s, int k) {
        int len = s.length();
        if (len == 0 || k > len) return 0;
        if (k < 2) return len;
        sub(s,k);
        return count;
    }


    public void sub(String s,int k){
        int[] map=new int[128];
        for(int i=0;i<s.length();i++)
            map[s.charAt(i)]++;
        for(int i=97;i<=122;i++){
            if(map[i]<k&&map[i]>0){
                String[] strings=s.split(""+(char)i);
                for(String ss:strings){
                    System.out.println(ss);
                    sub(ss,k);
                }
                return;
            }
        }
        if(count<s.length()) count=s.length();
    }
}
