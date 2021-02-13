import java.util.Arrays;

public class ValidAnagram_242 {

    //1. 暴力排序法，排序时间复杂度O(nlogn) 所以最终时间复杂度O(nlogn)
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] x1 = s.toCharArray();
        char[] x2 = t.toCharArray();
        Arrays.sort(x1);
        Arrays.sort(x2);
        for (int i = 0; i < s.length(); i++) {
            if (x1[i] != x2[i]) return false;
        }
        return true;
    }


    //2. 哈希表
    public boolean isAnagram2(String s, String t) {
        if(s.length()!=t.length()) return false;
        int[] map=new int[26];
        char[] x1=s.toCharArray();
        char[] x2=t.toCharArray();
        for(int i=0;i<s.length();i++){
            map[x1[i]-'a']++;
            map[x2[i]-'a']--;
        }
        for(int count:map) if(count!=0) return false;
        return true;
    }

}
