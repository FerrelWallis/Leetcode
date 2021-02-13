import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class WordSearchII_212 {
    //212. Word Search II
    //Given an m x n board of characters and a list of strings words, return all words on the board.
    //Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells
    //are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

    //Example 1:
    //Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
    //Output: ["eat","oath"]

    //Example 2:
    //Input: board = [["a","b"],["c","d"]], words = ["abcb"]
    //Output: []

    //Constraints:
    //m == board.length
    //n == board[i].length
    //1 <= m, n <= 12
    //board[i][j] is a lowercase English letter.
    //1 <= words.length <= 3 * 104
    //1 <= words[i].length <= 10
    //words[i] consists of lowercase English letters.
    //All the strings of words are unique.

    //Trie字典树
    class solution1 {
        public HashSet<String> ans = new HashSet<>();
        public List<String> findWords(char[][] board, String[] words) {
            //先将words放入字典树
            Trie trie = new Trie();
            for(int i = 0; i < words.length; i++) {
                trie.insert(words[i]);
            }
            TrieNode root = trie.getRoot();
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    StringBuilder sb =new StringBuilder();
                    sb.append(board[i][j]);
                    backtrace(i, j, sb, trie, board);
                }
            }
            return new LinkedList<>(ans);
        }

        private void backtrace(int row, int col, StringBuilder sb, Trie trie, char[][] board) {
            if(row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] == '#') return;
            if(trie.search(sb.toString())) ans.add(sb.toString());
            if(trie.startsWith(sb.toString())) {
                int[][] way = new int[][]{{1, 0},{-1, 0},{0, 1},{0, -1}};
                char temp = board[row][col];
                board[row][col] = '#';
                for(int i = 0; i < way.length; i++) {
                    int newrow = row + way[i][0], newcol = col + way[i][1];
                    sb.append(board[newrow][newcol]);
                    backtrace(newrow, newcol, sb, trie, board);
                    sb.deleteCharAt(sb.length() - 1);
                }
                board[row][col] = temp;
            }
        }


        class TrieNode {
            private TrieNode[] children;
            private boolean isEnd;
            public TrieNode() {
                children = new TrieNode[26];
            }
            //set
            public void set(char ch) {
                children[ch - 'a'] = new TrieNode();
            }
            //get
            public TrieNode get(char ch) {
                return children[ch - 'a'];
            }
            //contain
            public boolean containKey(char ch) {
                return children[ch - 'a'] != null;
            }
            //setEnd
            public void setEnd() {
                isEnd = true;
            }
            //getEnd
            public boolean getEnd() {
                return isEnd;
            }
        }

        class Trie {
            private TrieNode root;
            public Trie() {
                root = new TrieNode();
            }
            public TrieNode getRoot() {
                return root;
            }
            public void insert(String word) {
                TrieNode r = root;
                for(char w : word.toCharArray()) {
                    if(!r.containKey(w)) r.set(w);
                    r = r.get(w);
                }
                r.setEnd();
            }
            public boolean search(String word) {
                TrieNode r = root;
                for(char w : word.toCharArray()) {
                    if(!r.containKey(w)) return false;
                    r = r.get(w);
                }
                return r.getEnd();
            }
            public boolean startsWith(String word) {
                TrieNode r = root;
                for(char w : word.toCharArray()) {
                    if(!r.containKey(w)) return false;
                    r = r.get(w);
                }
                return true;
            }
        }
    }


    //Trie字典树优化 1. 不需要每次都从root开始查找，所以不需要Trie类
    //              2. 直接将String 存放于TrieNode中，这样查到了直接用，不需要每次append delete
    //              3. 可能会有重复的查到，之前是使用hashset重复put的特性，现在可以在找到过一次之后将之前存在于Node中的String设为空
    class solution2 {

        List<String> ans = new LinkedList<>();
        public List<String> findWords(char[][] board, String[] words) {
            TrieNode root = buildTrie(words);
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    if(root.containKey(board[i][j])) dfs(i, j, root, board);
                }
            }
            return ans;
        }

        private void dfs(int row, int col, TrieNode cur, char[][] board) {
            if(board[row][col] == '#' || !cur.containKey(board[row][col])) return;
            if(cur.getString() != "") {
                ans.add(cur.getString());
                cur.setString("");
            }
            int[][] way = new int[][]{{1, 0}, {-1, 0}, {0, 1},{0, -1}};
            char temp = board[row][col];
            board[row][col] = '#';
            for(int i = 0; i < 4; i++) {
                int newrow = row + way[i][0], newcol = col + way[i][1];
                if(newrow >=0 && newrow < board.length && newcol >= 0 && newcol < board[0].length)
                    dfs(newrow, newcol, cur.get(board[row][col]), board);
            }
            board[row][col] = temp;
        }

        public TrieNode buildTrie(String[] words) {
            TrieNode root = new TrieNode();
            for(String word : words) {
                TrieNode r = root;
                for(char w : word.toCharArray()) {
                    if(!r.containKey(w)) r.set(w);
                    r = r.get(w);
                }
                r.setString(word);
            }
            return root;
        }



        class TrieNode {
            private String word = "";
            private TrieNode[] children;
            public TrieNode() {
                children = new TrieNode[26];
            }
            //set
            public void set(char ch) {
                children[ch - 'a'] = new TrieNode();
            }
            //get
            public TrieNode get(char ch) {
                return children[ch - 'a'];
            }
            //contain
            public boolean containKey(char ch) {
                return children[ch - 'a'] != null;
            }
            //setString
            public void setString(String s) {
                word = s;
            }
            //getString
            public String getString() {
                return word;
            }
        }



    }
}
