import javafx.util.Pair;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class RearrangeStringkDistanceApart_358 {
    //358. Rearrange String k Distance Apart
    //Given a non-empty string s and an integer k, rearrange the string such that the same characters are
    //at least distance k from each other.
    //All input strings are given in lowercase letters. If it is not possible to rearrange the string,
    //return an empty string "".

    //Example 1:
    //Input: s = "aabbcc", k = 3
    //Output: "abcabc"
    //Explanation: The same letters are at least distance 3 from each other.

    //Example 2:
    //Input: s = "aaabc", k = 3
    //Output: ""
    //Explanation: It is not possible to rearrange the string.

    //Example 3:
    //Input: s = "aaadbbcc", k = 2
    //Output: "abacabcd"
    //Explanation: The same letters are at least distance 2 from each other.

    //hashmap + priorityqueue 相似题型：leetcode 621、767
    //记录所有类型的task的数量，存于taskToCount, 所有task的任务数量放入优先队列，优先poll任务书最大的，将每个任务类型按优先级poll出
    //再准备一个cooldown 哈希表，每次将poll出的（任务数量-1）暂存于cooldown中，并将放入的时间作为key，
    //每次循环查看是否有与当前时间间隔已经为n的key，说明该任务类型冷却时间已经到，重新放入queue中
    //如果优先队列为空，但cooldown里还有，说明不能形成要求字符串，返回""
    public String rearrangeString(String s, int k) {
        if(k <= 1) return s;
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for(char ss : s.toCharArray()) hashMap.put(ss, hashMap.getOrDefault(ss, 0) + 1);

        Queue<Pair<Character, Integer>> priority_queue = new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));
        for(char ss : hashMap.keySet()) priority_queue.add(new Pair<>(ss, hashMap.get(ss)));
        HashMap<Integer, Pair<Character, Integer>> cooldown = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        int curtime = 0;
        while (!priority_queue.isEmpty()) {
            if(cooldown.containsKey(curtime - k - 1))
                priority_queue.add(cooldown.remove(curtime - k - 1));
            Pair<Character, Integer> cur = priority_queue.poll();
            sb.append(cur.getKey());
            int next = cur.getKey() - 1;
            if(next != 0) cooldown.put(curtime, new Pair<>(cur.getKey(), next));
            curtime++;
        }
        return (priority_queue.isEmpty() && cooldown.isEmpty())? sb.toString() : "";
    }
}
