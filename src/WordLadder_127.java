import javafx.util.Pair;

import java.util.*;

public class WordLadder_127 {



    //广度优先遍历 采用思路一：更换每一位的a-z字母，判断是否存在于wordlist
    //               思路二：遍历wordlist，找相差1的
    // 原因：1.一般单词不会特别长 将wordlist放到set里查找是否存在方便，时间复杂度低
    // 2.wordlist可能会特别的长，思路二速度就会慢

    //bfs
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int len = beginWord.length();
        HashMap<String,List<String>> dictionary = new HashMap<>();
        for(String word : wordList) {
            for(int i = 0; i < len; i++) {
                String newword = word.substring(0,i) + "*" + word.substring(i+1, len);
                List<String> temp = dictionary.getOrDefault(newword, new LinkedList<>());
                temp.add(word);
                dictionary.put(newword, temp);
            }
        }
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.add(new Pair<>(beginWord, 1));
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            Pair<String, Integer> cur = queue.poll();
            String curword = cur.getKey();
            int level = cur.getValue();
            for(int i = 0; i < len; i++) {
                String key = curword.substring(0, i) + "*" + curword.substring(i + 1, len);
                List<String> onediff = dictionary.getOrDefault(key, new LinkedList<>());
                for(String diff : onediff) {
                    if(diff.equals(endWord)) return level + 1;
                    if(!visited.contains(diff)) {
                        visited.add(diff);
                        queue.add(new Pair<>(diff, level + 1));
                    }
                }
            }
        }
        return 0;
    }


    //双向bfs
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        int len = beginWord.length();
        HashMap<String, List<String>> dictionary = new HashMap<>();
        for(String word : wordList) {
            for(int i = 0; i < len; i++) {
                String new_word = word.substring(0, i) + "*" + word.substring(i + 1, len);
                List<String> temp = dictionary.getOrDefault(new_word, new LinkedList<>());
                temp.add(word);
                dictionary.put(new_word, temp);
            }
        }
        Queue<Pair<String,Integer>> begin_queue = new LinkedList<>();
        Queue<Pair<String,Integer>> end_queue = new LinkedList<>();
        HashMap<String, Integer> begin_visited = new HashMap<>();
        HashMap<String, Integer> end_vidited = new HashMap<>();
        begin_queue.add(new Pair<>(beginWord, 1));
        end_queue.add(new Pair<>(endWord, 1));
        begin_visited.put(beginWord, 1);
        end_vidited.put(endWord, 1);
        while (!begin_queue.isEmpty() && !end_queue.isEmpty()) {
            int ans = spread(begin_queue, begin_visited, dictionary, end_vidited);
            if(ans != -1) return ans;
            ans = spread(end_queue, end_vidited, dictionary, begin_visited);
            if(ans != -1) return ans;
        }
        return 0;
    }

    private int spread(Queue<Pair<String, Integer>> queue, HashMap<String, Integer> vidited, HashMap<String, List<String>> dictionary, HashMap<String, Integer> other_visited) {
        Pair<String, Integer> cur = queue.poll();
        String word = cur.getKey();
        int level = cur.getValue(), len = word.length();
        for(int i = 0; i < len; i++) {
            String new_word = word.substring(0, i) + "*" + word.substring(i + 1, len);
            List<String> wordlist = dictionary.getOrDefault(new_word, new LinkedList<>());
            for(String w : wordlist) {
                if(other_visited.containsKey(w)) return level + other_visited.get(w);
                if(!vidited.containsKey(w)) {
                    vidited.put(w, level + 1);
                    queue.add(new Pair<>(w, level + 1));
                }
            }
        }
        return -1;
    }

}
