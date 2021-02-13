import javafx.util.Pair;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class ReorganizeString_767 {
    //767. Reorganize String
    //Given a string S, check if the letters can be rearranged so that two characters that are adjacent to
    //each other are not the same.
    //If possible, output any possible result.  If not possible, return the empty string.
    //Example 1:
    //Input: S = "aab"
    //Output: "aba"
    //Example 2:
    //Input: S = "aaab"
    //Output: ""
    //Note: S will consist of lowercase letters and have length in range [1, 500].

    //hashmap + priorityqueue  相似题型：leetcode 621、358
    //记录所有类型的task的数量，存于taskToCount, 所有task的任务数量放入优先队列，优先poll任务书最大的，将每个任务类型按优先级poll出
    //再准备一个cooldown 哈希表，每次将poll出的（任务数量-1）暂存于cooldown中，并将放入的时间作为key，
    //每次循环查看是否有与当前时间间隔已经为n的key，说明该任务类型冷却时间已经到，重新放入queue中
    //如果优先队列为空，但cooldown里还有，说明不能形成要求字符串，返回""
    //时间复杂度O(N) 空间复杂度O(26)
    public String reorganizeString(String S) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for(char s : S.toCharArray()) hashMap.put(s, hashMap.getOrDefault(s, 0) + 1);
        Queue<Pair<Character, Integer>> priority_queue = new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));
        for(char s : hashMap.keySet()) priority_queue.add(new Pair<>(s, hashMap.get(s)));
        HashMap<Integer, Pair<Character, Integer>> cooldown = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int curtime = 0;
        while (!priority_queue.isEmpty()) {
            if(cooldown.containsKey(curtime - 2))
                priority_queue.add(cooldown.remove(curtime - 2));
            Pair<Character, Integer> cur = priority_queue.poll();
            sb.append(cur.getKey());
            int left = cur.getValue() - 1;
            if(left != 0) cooldown.put(curtime, new Pair<>(cur.getKey(), left));
            curtime++;
        }
        return (priority_queue.isEmpty() && cooldown.isEmpty())? sb.toString() : "";
    }
}
