import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache_146 {
    public static void main(String[] args) {
        LRUCache_146 cache = new LRUCache_146( 2 /* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }
    /**
     * 使用linkedHashmap实现LRUCache
     */
    private LinkedHashMap<Integer,Integer> map;
    private final int capacity;
    public LRUCache_146(int capacity) {
        this.capacity=capacity;
        map=new LinkedHashMap<Integer,Integer>(capacity,0.75f,true) {
            //为true自动删除最老的节点
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size()>capacity;
            }
        };
    }

    public int get(int key) {
        return map.getOrDefault(key,-1);
    }

    public void put(int key, int value) {
        map.put(key,value);
    }
}
