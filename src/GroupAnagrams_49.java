import java.util.*;

public class GroupAnagrams_49 {

    //1.排序 时间复杂度O(nklogk)
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String,List<String>> ans = new HashMap<>();
        for(String cur:strs){
            char[] cursort=cur.toCharArray();
            Arrays.sort(cursort);
            String key=String.valueOf(cursort);
            if(!ans.containsKey(key)) ans.put(key,new ArrayList<>());
            ans.get(key).add(cur);
        }
        return new ArrayList(ans.values());
    }

    //2. 26字母计数法
    public List<List<String>> groupAnagrams2(String[] strs) {
        HashMap<String, List<String>> hash = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            int[] num = new int[26];
            //记录每个字符的次数
            for (int j = 0; j < strs[i].length(); j++) {
                num[strs[i].charAt(j) - 'a']++;
            }
            //转成 0#2#2# 类似的形式
            String key = "";
            for (int j = 0; j < num.length; j++) {
                key = key + num[j] + '#';
            }
            if (hash.containsKey(key)) {
                hash.get(key).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                hash.put(key, temp);
            }

        }
        return new ArrayList<List<String>>(hash.values());
    }



    //3. 算术基本定理，又称为正整数的唯一分解定理，即：每个大于1的自然数，要么本身就是质数，
    // 要么可以写为2个以上的质数的积，而且这些质因子按大小排列之后，写法仅有一种方式。
    //利用这个，我们把每个字符串都映射到一个正数上。
    //用一个数组存储质数
    //prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103}。
    //然后每个字符串的字符减去 ' a ' ，然后取到 prime 中对应的质数。把它们累乘。
    //例如 abc ，就对应 'a' - 'a'， 'b' - 'a'， 'c' - 'a'，即 0, 1, 2，也就是对应素数 2 3 5，
    //然后相乘 2 3 5 = 30，就把 "abc" 映射到了 30。
    public List<List<String>> groupAnagrams3(String[] strs) {
        HashMap<Integer, List<String>> hash = new HashMap<>();
        //每个字母对应一个质数
        int[] prime = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103 };
        for (int i = 0; i < strs.length; i++) {
            int key = 1;
            //累乘得到 key
            for (int j = 0; j < strs[i].length(); j++) {
                key *= prime[strs[i].charAt(j) - 'a'];
            }
            if (hash.containsKey(key)) {
                hash.get(key).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                hash.put(key, temp);
            }
        }
        return new ArrayList(hash.values());
    }

}
