import java.util.Arrays;
import java.util.HashSet;

public class AssignCookies_455 {
    //贪心 每一个子步骤都做当前最佳的选择,且不能回退.(与动态规划不同,动态规划会记录每一步骤,且可以回退)
    //贪心题的重点是证明当前题目可以用简单的贪心得到最佳答案
    public int findContentChildren(int[] g, int[] s) {
        //排序成递增数组
        Arrays.sort(g);
        Arrays.sort(s);
        HashSet<Integer> eaten = new HashSet<>();
        int count=0;
        for(int i = 0; i < g.length; i++) {
            boolean max = true;
            for(int j = 0; j < s.length; j++) {
                if(g[i] <= s[j] && !eaten.contains(j)) {
                    eaten.add(j);
                    count++;
                    max = false;
                }
            }
            if(max) break;
        }
        return count;
    }

    //优化 饼干无需每次都从头开始,随着小朋友的胃口增大,饼干要求的大小只会递增,小的不用再遍历
    //每次都拿当前最小的饼干(比上一次大)喂当前胃口最小的小孩
    public int findContentChildren2(int[] g, int[] s) {
        if (g == null || s == null) return 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int gi = 0, si = 0;
        //当小朋友数量不够或饼干数量不够都结束
        while (gi < g.length && si < s.length) {
            if(g[gi] <= s[si]) gi++;
            si++;
        }
        return gi;
    }
}
