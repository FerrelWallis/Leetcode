import java.lang.reflect.Array;
import java.util.*;

/*
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Input: ["abcd","dcba","lls","s","sssll"]
Output: [[0,1],[1,0],[3,2],[2,4]]
Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]

Example 2:
Input: ["bat","tab","cat"]
Output: [[0,1],[1,0]]
Explanation: The palindromes are ["battab","tabbat"]
* */
public class PalindromePairs_336 {
    public static void main(String[] args) {
        PalindromePairs_336 test=new PalindromePairs_336();
        String[] words=new String[]{"abcd","dcba","lls","s","sssll"};
        System.out.println(test.palindromePairs(words));
    }


    /*时间复杂度：O(k^2*n)。
建立哈希表需要 O(n⋅k) 的时间。每个单词需要 O(k) 的时间插入并且有 n 个单词。
然后，对于 n 个单词，每个单词我们将搜索三个不同的情况。首先是这个词本身的相反词。这需要 O(k) 的时间。
第二个情况是回文跟另一个词的相反词。第三种情况的另一个词的相反词后跟回文。这两个情况的花费是一样的，
所以我们就看前面的情况。我们需要找到给定单词的所有回文前缀。查找单词的所有回文前缀可以在 O(k^2)的时间内完成。
因为有 k 个可能的前缀，检查每个前缀需要 O(k) 的时间，所以对于每个单词，我们都要进行 k^2 + k^2 +k 处理，
由于有 n 个单词，所以结果是 O(k^2*n)。值得注意的是，暴力方法成本为O（n^2*k），因此，这种方法并不是在任何
情况下更好。只有当 n > k 的情况下更好，在测试用例中测试解决方案中也确实如此。
*/

    //散列（思路与字典树一样，通过哈希表实现）51ms
    public List<List<Integer>> palindromePairs4(String[] words) {
        HashMap<String,Integer> reverse=new HashMap<>();
        List<List<Integer>> ans=new ArrayList<>();
        for(int i=0;i<words.length;i++)
            reverse.put(new StringBuilder(words[i]).reverse().toString(),i);

        for(int i=0;i<words.length;i++){
            String cur=words[i];
            //prefix
            for(int j=0;j<cur.length();j++){
                if(isParmlind2(cur,j,cur.length()-1)) {
                    String prefix=cur.substring(0,j);
                    if(reverse.containsKey(prefix)&&reverse.get(prefix)!=i)
                        ans.add(new ArrayList<>(Arrays.asList(i,reverse.get(prefix))));
                }
            }
            //suffix
            for(int j=cur.length()-1;j>=0;j--){
                if(isParmlind2(cur,0,j)){
                    String suffix=cur.substring(j+1);
                    if(reverse.containsKey(suffix)&&reverse.get(suffix)!=i)
                        ans.add(new ArrayList<>(Arrays.asList(reverse.get(suffix),i)));
                }
            }
            //equal
            if(reverse.containsKey(cur)&&reverse.get(cur)!=i)
                ans.add(new ArrayList<>(Arrays.asList(i,reverse.get(cur))));

        }

        return ans;
    }

    public boolean isParmlind2(String s, int left, int right) {
        while(left < right) {
            if(s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }




    //前缀树
    private Node root;
    public List<List<Integer>> palindromePairs2(String[] words) {
        this.root = new Node();
        int n = words.length;
        // 字典树的插入，注意维护每个节点上的两个列表
        for (int i = 0; i < n; i++) {
            String rev = new StringBuilder(words[i]).reverse().toString();
            Node cur = root;
            if (isPalindrome(rev.substring(0))) cur.suffixs.add(i);
            for (int j = 0; j < rev.length(); j++) {
                char ch = rev.charAt(j);
                if (cur.children[ch-'a']==null) cur.children[ch-'a'] = new Node();
                cur = cur.children[ch-'a'];
                if (isPalindrome(rev.substring(j+1))) cur.suffixs.add(i);
            }
            cur.words.add(i);
        }
        // 用以存放答案的列表
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String word = words[i];
            Node cur = root;
            int j = 0;
            for ( ;j < word.length(); j++) {
                // 到j位置，后续字符串若是回文对，则在该节点位置上所有单词都可以与words[i]构成回文对
                // 因为我们插入的时候是用每个单词的逆序插入的:)
                if(isPalindrome(word.substring(j)))
                    for (int k : cur.words)
                        if (k != i) ans.add(Arrays.asList(i,k));

                char ch = word.charAt(j);
                if (cur.children[ch-'a'] == null) break;
                cur = cur.children[ch-'a'];

            }
            // words[i]遍历完了，现在找所有大于words[i]长度且符合要求的单词，suffixs列表就派上用场了:)
            if (j == word.length())
                for (int k : cur.suffixs)
                    if (k != i) ans.add(Arrays.asList(i,k));

        }
        return ans;

    }
    //  判断一个字符串是否是回文字符串
    private boolean isPalindrome(String w) {
        int i = 0, j = w.length()-1;
        while (i < j) {
            if (w.charAt(i) != w.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }


    //暴力法 超出时间O(n^2*k)
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> list=new ArrayList<>();
        for(int i=0;i<words.length;i++){
            for(int j=0;j<words.length;j++){
                if(i==j)continue;
                StringBuilder sb=new StringBuilder(words[i]+words[j]);
                if(sb.toString().equals(sb.reverse().toString())){
                    list.add(Arrays.asList(i,j));
                }
            }
        }
        return list;
    }
}

class Node {
    public Node[] children;
    public List<Integer> words;
    public List<Integer> suffixs;

    public Node() {
        this.children = new Node[26];
        this.words = new ArrayList<>();
        this.suffixs = new ArrayList<>();
    }
}
