import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskScheduler_621 {
    //Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.
    //
    //However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
    //
    //Return the least number of units of times that the CPU will take to finish all the given tasks.
    //
    // 
    //
    //Example 1:
    //
    //Input: tasks = ["A","A","A","B","B","B"], n = 2
    //Output: 8
    //Explanation:
    //A -> B -> idle -> A -> B -> idle -> A -> B
    //There is at least 2 units of time between any two same tasks.
    //Example 2:
    //
    //Input: tasks = ["A","A","A","B","B","B"], n = 0
    //Output: 6
    //Explanation: On this case any permutation of size 6 would work since n = 0.
    //["A","A","A","B","B","B"]
    //["A","B","A","B","A","B"]
    //["B","B","B","A","A","A"]
    //...
    //And so on.
    //Example 3:
    //
    //Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
    //Output: 16
    //Explanation:
    //One possible solution is
    //A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
    //只需考虑idle数量，答案就是tasks数量+idel数量，如果idel数量 <= 0 说明任务完全可以填满所有的空闲时间，idel直接取0
    //最大重复的char数量max , maxcount为字符数量为max的字符数量   比如： AAABBBCD  max = 3  maxcount = 2
    // 初始idel数 = (max -1) * (n - (maxcount - 1))
    // 可用来填充idel的任务数量 available = task.length - max * maxcount
    //时间复杂度O(N) 空间复杂度O(26)
    public int leastInterval(char[] tasks, int n) {
        int[] counter = new int[26];
        int max = 0, maxcount = 0, len = tasks.length;
        for(int i = 0; i < tasks.length; i++) {
            counter[tasks[i] - 'A']++;
            if(counter[tasks[i] - 'A'] == max) maxcount++;
            else if(counter[tasks[i] - 'A'] > max) {
                max = counter[tasks[i] - 'A'];
                maxcount = 1;
            }
        }
        int originidle = (max - 1) * (n - (maxcount - 1));
        int available = len - max * maxcount;
        int idle = Math.max(0, originidle - available);
        return len + idle;
    }

    //解法2：priority queue + hashmap
    //记录所有类型的task的数量，存于taskToCount, 所有task的任务数量放入优先队列，优先poll任务书最大的，将每个任务类型按优先级poll出
    //再准备一个cooldown 哈希表，每次将poll出的（任务数量-1）暂存于cooldown中，并将放入的时间作为key，
    //每次循环查看是否有与当前时间间隔已经为n的key，说明该任务类型冷却时间已经到，重新放入queue中
    //相似题型：leetcode 358 、 767
    public int leastInterval2(char[] tasks, int n) {
        if(n == 0) return tasks.length;
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for(char t : tasks) hashMap.put(t, hashMap.getOrDefault(t, 0) + 1);
        Queue<Integer> priority_queue = new PriorityQueue<>((a, b) -> (b - a)); //大的优先级越高
        for(char t : hashMap.keySet()) priority_queue.add(hashMap.get(t));
        HashMap<Integer, Integer> cooldown = new HashMap<>();
        int curtime = 0;
        while (!priority_queue.isEmpty() || !cooldown.isEmpty()) {
            if(cooldown.containsKey(curtime - n - 1))
                priority_queue.add(cooldown.remove(curtime - n - 1));
            if(!priority_queue.isEmpty()) {
                int left = priority_queue.poll() - 1;
                if(left != 0) cooldown.put(curtime, left);
            }
            curtime++;
        }
        return curtime;
    }
}
