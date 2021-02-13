import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

public class MinimumGeneticMutation_433 {


    //深度优先遍历
    // 直接遍历bank看与当期是否相差1个char 将符合且没访问过的的字符串进入下一层
    int count=Integer.MAX_VALUE;
    public int minMutation(String start, String end, String[] bank) {
        helper(new HashSet<String>(),0,start,end,bank);
        return count==Integer.MAX_VALUE? -1:count;
    }

    private void helper(HashSet<String> visited, int mincount, String cur, String end, String[] bank) {
        if (cur.equals(end)) {
            count = Math.min(count, mincount);
            return;
        }
        for (int i = 0; i < bank.length; i++) {
            int diff = 0;
            for (int j = 0; j < 8; j++) {
                if (cur.charAt(j) != bank[i].charAt(j)) diff++;
                if (diff > 1) break;
            }
            if (diff == 1 && !visited.contains(bank[i])) {
                visited.add(bank[i]);
                helper(visited, mincount + 1, bank[i], end, bank);
                visited.remove(bank[i]);
            }
        }
    }


    //广度优先遍历
    public int minMutation2(String start, String end, String[] bank) {
        HashSet<String> banks=new HashSet<>();
        for(String b:bank) banks.add(b);
       int count=0;
       char[] mut={'A','C','G','T'};
        Deque<String> queue=new LinkedList<>();
        HashSet<String> visited=new HashSet<>();
        queue.add(start);
        while (!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                String cur=queue.poll();
                if(cur.equals(end)) return count;
                char[] temp=cur.toCharArray();
                for(int j=0;j<8;j++){
                    char old = temp[j];
                    for(char c:mut){
                        temp[j]=c;
                        String news=new String(temp);
                        if(!visited.contains(news) && banks.contains(news)){
                            visited.add(news);
                            queue.add(news);
                        }
                    }
                    temp[j]=old;
                }
            }
            count++;
        }
        return -1;
    }


}
