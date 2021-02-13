import java.util.*;

public class SortCharactersByFrequency {


    /*Java 大顶堆
执行用时 :4 ms, 在所有 Java 提交中击败了99.71%的用户
内存消耗 :37.3 MB, 在所有 Java 提交中击败了98.86%的用户

当我们统计到字符频率的时候，就可以利用堆排序将频率高的字符进行“上浮”，最后我们在遍历的时候就是频率最高字符在首位，然后再一次打印即可。
*/

    //PriorityQueue：优先队列排序（天生小顶堆，具体看add方法中调用的siftup方法，当节点大于父节点，停止上浮操作break）
    //Comparable接口：想要自定义对象也能像Array.sort排序需要实现comparable接口并且重写compareTo方法

    public String frequencySort(String s) {
        //初始化字母数组，统计字符频率
        int[] latters = new int[128];
        //填充数组，字符对应出现频率
        for(char c:s.toCharArray()){
            latters[c]++;
        }
        //优先队列排序（天生小顶堆，具体看add方法中调用的siftup方法，因此需要改变Latter的compareto，变成大顶堆）
        PriorityQueue<Latter> queue = new PriorityQueue<>();

        //将数组放入优先队列
        for (int i = 0;i<latters.length;i++){
            if (latters[i]!=0){
                //优先队列的add方法中的siftup方法中的逻辑是compare一旦>=0，就停止上浮（小堆顶，小的上浮）
                //这里修改了compare，使得节点小于父节点时返回>=0，逻辑就变成了节点大于父节点就上浮，小于就停止
                queue.add(new Latter((char) i,latters[i]));
            }
        }

        StringBuilder stringBuilder = new StringBuilder();


        //将大顶堆（包含字符和频率，组成字符串）
        while (!queue.isEmpty()){
            Latter latter = queue.poll();
            for (int i =0;i<latter.count;i++)
                stringBuilder.append(latter.latter);
        }


        return stringBuilder.toString();
    }

    //latter类实现comparable接口，实现字符对应频率排序
    public class Latter implements Comparable<Latter>{
        public char latter = '0';
        public int count = 0;

        public Latter(char latter, int count) {
            this.latter = latter;
            this.count = count;
        }

        //重写compareto,
        // 这里返回o.count - this.count,表示如果父节点大，返回正数，否则返回负数
        @Override
        public int compareTo(Latter o) {
            return o.count - this.count;
        }
    }



    //大顶堆（二叉树）
    /*
    * 假设当前节点为i，他的左节点为2i+1和右键点2i+2
    *
    *
    * */











}
