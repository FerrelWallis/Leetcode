import javafx.util.Pair;

import java.util.*;

public class WordLadderII_126 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans=new LinkedList<>();
        HashSet<String> worddict=new HashSet<>(wordList);
        if(!worddict.contains(endWord)) return ans;
        HashSet<String> visited=new HashSet<>();
        Queue<List<String>> queue=new LinkedList<>();
        List<String> list=new LinkedList<>(Arrays.asList(beginWord));
        queue.add(list);
        visited.add(beginWord);
        boolean flag=false;
        while (!queue.isEmpty() && !flag) {
            int size=queue.size();
            HashSet<String> subVisited=new HashSet<>();
            for (int i=0;i<size;i++) {
                List<String> cur=queue.remove();
                String preword=cur.get(cur.size()-1);
                char[] chars=preword.toCharArray();
                for(int j=0;j<preword.length();j++) {
                    char temp=chars[j];
                    for(char c='a';c<='z';c++) {
                        chars[j]=c;
                        if(c==temp) continue;
                        String nextword=new String(chars);
                        if(worddict.contains(nextword) && !visited.contains(nextword)) {
                            List<String> nextpath=new LinkedList<>(cur);
                            nextpath.add(nextword);
                            if(nextword.equals(endWord)) {
                                flag=true;
                                ans.add(nextpath);
                            }
                            queue.add(nextpath);
                            subVisited.add(nextword);
                        }
                    }
                    chars[j]=temp;
                }
            }
            visited.addAll(subVisited);
        }
        return ans;
    }


    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        // 结果集
        List<List<String>> res = new ArrayList<>();
        Set<String> words = new HashSet<>(wordList);
        // 字典中不包含目标单词
        if (!words.contains(endWord)) {
            return res;
        }
        // 存放关系：每个单词可达的下层单词
        Map<String, List<String>> mapTree = new HashMap<>();
        Set<String> begin = new HashSet<>(), end = new HashSet<>();
        begin.add(beginWord);
        end.add(endWord);
        if (buildTree(words, begin, end, mapTree, true)) {
            dfs(res, mapTree, beginWord, endWord, new LinkedList<>());
        }
        return res;
    }

    // 双向BFS，构建每个单词的层级对应关系
    private boolean buildTree(Set<String> words, Set<String> begin, Set<String> end, Map<String, List<String>> mapTree, boolean isFront){
        if (begin.size() == 0) {
            return false;
        }
        // 始终以少的进行探索
        if (begin.size() > end.size()) {
            return buildTree(words, end, begin, mapTree, !isFront);
        }
        // 在已访问的单词集合中去除
        words.removeAll(begin);
        // 标记本层是否已到达目标单词
        boolean isMeet = false;
        // 记录本层所访问的单词
        Set<String> nextLevel = new HashSet<>();
        for (String word : begin) {
            char[] chars = word.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char temp = chars[i];
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    chars[i] = ch;
                    String str = String.valueOf(chars);
                    if (words.contains(str)) {
                        nextLevel.add(str);
                        // 根据访问顺序，添加层级对应关系：始终保持从上层到下层的存储存储关系
                        // true: 从上往下探索：word -> str
                        // false: 从下往上探索：str -> word（查找到的 str 是 word 上层的单词）
                        String key = isFront ? word : str;
                        String nextWord = isFront ? str : word;
                        // 判断是否遇见目标单词
                        if (end.contains(str)) {
                            isMeet = true;
                        }
                        if (!mapTree.containsKey(key)) {
                            mapTree.put(key, new ArrayList<>());
                        }
                        mapTree.get(key).add(nextWord);
                    }
                }
                chars[i] = temp;
            }
        }
        if (isMeet) {
            return true;
        }
        return buildTree(words, nextLevel, end, mapTree, isFront);
    }

    // DFS: 组合路径
    private void dfs (List<List<String>> res, Map<String, List<String>> mapTree, String beginWord, String endWord, LinkedList<String> list) {
        list.add(beginWord);
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(list));
            list.removeLast();
            return;
        }
        if (mapTree.containsKey(beginWord)) {
            for (String word : mapTree.get(beginWord)) {
                dfs(res, mapTree, word, endWord, list);
            }
        }
        list.removeLast();
    }


    //bfs
    public List<List<String>> findLadders4(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();

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
        Queue<List<String>> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.add(new LinkedList<>(Arrays.asList(beginWord)));
        visited.add(beginWord);
        boolean flag = false;
        while (!queue.isEmpty() && !flag) {
            List<String> cur = queue.poll();
            String cur_word = cur.get(cur.size() - 1);
            for(int i = 0; i < len; i++) {
                String new_word = cur_word.substring(0, i) + "*" + cur_word.substring(i + 1, len);
                List<String> nextlist = dictionary.getOrDefault(new_word, new LinkedList<>());
                for(String next : nextlist) {
                    List<String> temp = new LinkedList<>(cur);
                    temp.add(next);
                    if(next.equals(endWord)) {
                        flag = true;
                        ans.add(temp);
                    }
                    if(!visited.contains(next)) {
                        visited.add(next);
                        queue.add(temp);
                    }
                }
            }
        }
        return ans;
    }


    //双向bfs
    public HashMap<String, List<String>> dictionary = new HashMap<>();
    public List<List<String>> ans = new ArrayList<>();
    public List<List<String>> findLadders5(String beginWord, String endWord, List<String> wordList) {
        int len = beginWord.length();
        for(String word : wordList) {
            for(int i = 0; i < len; i++) {
                String new_word = word.substring(0, i) + "*" + word.substring(i + 1, len);
                List<String> temp = dictionary.getOrDefault(new_word, new LinkedList<>());
                temp.add(word);
                dictionary.put(new_word, temp);
            }
        }
        Queue<List<String>> begin_queue = new LinkedList<>();
        Queue<List<String>> end_queue = new LinkedList<>();
        HashSet<String> begin_visited = new HashSet<>();
        HashSet<String> end_visited = new HashSet<>();
        begin_queue.add(Arrays.asList(beginWord));
        end_queue.add(Arrays.asList(endWord));
        begin_visited.add(beginWord);
        end_visited.add(endWord);
        spread2(begin_queue, end_queue, begin_visited, end_visited);
        return ans;
    }

    private void spread2(Queue<List<String>> begin_queue, Queue<List<String>> end_queue, HashSet<String> begin_visited, HashSet<String> end_visited) {
        if(begin_queue.size() == 0) return;
        if(begin_queue.size() > end_queue.size()) spread2(end_queue, begin_queue, end_visited, begin_visited);
        List<String> cur = begin_queue.poll();
        String preword = cur.get(cur.size() - 1);
        int len = preword.length();
        boolean flag = false;
        for(int i = 0; i < len; i++) {
            String new_word = preword.substring(0, i) + "*" + preword.substring(i + 1, len);
            List<String> nextlist = dictionary.getOrDefault(new_word, new LinkedList<>());
            for(String next : nextlist) {
                List<String> temp = new LinkedList<>(cur);
                temp.add(next);
                if(end_visited.contains(next)) {
                    ans.add(temp);
                    flag = true;
                }
                if(!begin_visited.contains(next)) {
                    begin_visited.add(next);
                    begin_queue.add(temp);
                }
            }
        }
        if(flag) return;
        else spread2(begin_queue,end_queue,begin_visited,end_visited);
    }


}
