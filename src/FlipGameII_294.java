import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
* You are playing the following Flip Game with your friend:
* Given a string that contains only these two characters: + and -,
*  you and your friend take turns to flip two consecutive "++" into "--".
* The game ends when a person can no longer make a move and
* therefore the other person will be the winner.
*
Write a function to determine if the starting player can guarantee a win.
*
Example:
Input: s = "++++"
Output: true
Explanation: The starting player can guarantee a win by flipping the middle "++" to become "+--+".
* */
public class FlipGameII_294 {
    //记忆化搜索+递归
    //创建哈希表存放已经判断过true false的字符串，提高效率
    HashMap<String,Boolean> memo=new HashMap<String, Boolean>();

    public boolean canWin(String s) {
        //先判断是否已经判断过该s的true false，已经判断过就直接返回值
        if(memo.get(s)!=null) return memo.get(s);
        //没有判断过，进行循环判断
        for(int i=1;i<s.length();i++){
            //将string转成char数组
            char[] c=s.toCharArray();
            if(s.charAt(i-1)=='+'&&s.charAt(i)=='+'){
                //如果当前字符串存在++,则将他变成--
                c[i-1]='-';
                c[i]='-';
                //并且将改变后的字符串（递归操作）进行以上判断，如果第二个返回false，则true
                //第二个要返回false:1.第三个返回true
                //所以判断序列为 true false true false 或者 false true false true
                //最底层的判断直接影响首个的判断
                if(!canWin(String.valueOf(c))) {
                    memo.put(s,true);
                    return true;
                }
            }
        }
        memo.put(s,false);
        return false;
    }

    //SG函数动态规划
    public boolean SG(String s) {
        List<Integer> gList = new ArrayList<>();
        int n = s.length();
        int i = 0, j = 0;
        int max = 0;
        while (i < n) {
            if (s.charAt(i) == '-') i++;
            else {
                for (j = i; j < n && s.charAt(j) == '+'; j++);
                gList.add(j - i);
                if (j - i > max) max = j - i;
                i = j + 1;
            }
        }
        if (max <= 1) return false;
        int[] g = new int[max + 1];
        g[0] = 0; g[1] = 0;
        for (int k = 2; k < max + 1; k++) {
            BitMap bm = new BitMap();
            for (int l = 0; l <= (k - 2) / 2; l++) {
                bm.set(g[l] ^ g[k - 2 - l]);
            }
            for (int l = 0; l < k; l++) {
                if (!bm.contains(l)) { g[k] = l; break; }
            }
        }
        int result = 0;
        while (!gList.isEmpty()) {
            result = result ^ g[gList.get(0)];
            gList.remove(0);
        }
        return result != 0;

    }

    class BitMap {
        char[] M;
        int N;

        BitMap(int n) {
            N = (n + 8) / 8;
            M = new char[N];
        }

        BitMap() {
            N = 1;
            M = new char[N];
        }

        private void expand(int k) {
            if ((k + 8) / 8 < N) return;
            int oldN = N;
            N = (k + 8) / 8 * 2;
            char[] newM = new char[N];
            for (int i = 0; i < oldN; i++) {
                newM[i] = M[i];
            }
            M = newM;
        }

        public boolean contains(int k) {
            expand(k);
            return (M[k >> 3] & (0x80 >> (k & 0x07))) != 0;
        }

        public void set(int k) {
            expand(k);
            M[k >> 3] = (char) (M[k >> 3] | (0x80 >> (k & 0x07)));
        }

        public void delete(int k) {
            expand(k);
            M[k >> 3] = (char) (M[k >> 3] & (~(0x80 >> (k & 0x07))));
        }
    }




}
