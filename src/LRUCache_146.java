import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache_146 {
    //Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
    //Implement the LRUCache class:
    //LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
    //int get(int key) Return the value of the key if the key exists, otherwise return -1.
    //void put(int key, int value) Update the value of the key if the key exists. Otherwise, add
    //the key-value pair to the cache. If the number of keys exceeds the capacity from this operation,
    //evict the least recently used key.
    //Follow up: Could you do get and put in O(1) time complexity?

    //Example 1:
    //Input
    //["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
    //[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
    //Output
    //[null, null, null, 1, null, -1, null, -1, 3, 4]

    //Explanation
    //LRUCache lRUCache = new LRUCache(2);
    //lRUCache.put(1, 1); // cache is {1=1}
    //lRUCache.put(2, 2); // cache is {1=1, 2=2}
    //lRUCache.get(1);    // return 1
    //lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    //lRUCache.get(2);    // returns -1 (not found)
    //lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    //lRUCache.get(1);    // return -1 (not found)
    //lRUCache.get(3);    // return 3
    //lRUCache.get(4);    // return 4

    //Constraints:
    //    1 <= capacity <= 3000
    //    0 <= key <= 3000
    //    0 <= value <= 104
    //    At most 3 * 104 calls will be made to get and put.

    //1.用语言自带的类LinkedHashMap
    class LRUCache extends LinkedHashMap<Integer, Integer> {
        int capacity;
        public LRUCache(int capacity) {
            //LinkedHashMap构造函数中，accessOrder设置为false时，会按照插入顺序进行排序，
            //当accessOrder为true是，会按照访问顺序（也就是插入和访问都会将当前节点放置到尾部，
            //尾部代表的是最近访问的数据）
            super(capacity, 0.75F, true); //调用父类的构造函数，构造LinkedHashMap
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1); //使用父类的getOrDefault, 没有返回-1
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            //Map.Entry<Integer, Integer>是指一个空的 key-value pair
            return size() > capacity;
        }
    }


    //2. 自己写 哈希表 + 双向链表
    public class LRUCache1{
        class myNode {
            int key;
            int value;
            myNode pre;
            myNode next;
            public myNode(int k, int v) {
                key = k;
                value = v;
            }
            public myNode() {}
        }

        int size; //表示缓存中当前有几个元素
        int capacity; //表示缓存容量
        HashMap<Integer, myNode> hashMap;
        myNode head, tail;

        public LRUCache1(int capacity) {
            size = 0;
            this.capacity = capacity;
            hashMap = new HashMap<>();
            head = new myNode();
            tail = new myNode();
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            myNode node = hashMap.getOrDefault(key, null);
            if(node == null) return -1;
            //LRU中node被使用过了，因此更新到缓存头部。
            //先通过哈希表定位到node，再删除原本的node位置，最后将node添加到头部
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            myNode node = hashMap.getOrDefault(key, null);

            if(node != null) { //如果key已经存在，更新一下node的value，在将node移到头部
                node.value = value;
                moveToHead(node);
            } else { //如果不存在，新建节点添加(加入哈希表和双向链表)，并且查看是否超出容量，超出容量就删除最后一个节点
                node = new myNode(key, value);
                hashMap.put(key, node);
                addToHead(node);
                size++;
                if(size > capacity) {
                    myNode tailnode = removeTail();
                    hashMap.remove(tailnode.key);
                    size--;
                }
            }
        }

        private myNode removeTail() {
            myNode curtail = tail.pre;
            removeNode(curtail);
            return curtail;
        }

        private void moveToHead(myNode node) {
            removeNode(node); //先将原本的node在链表中删除
            addToHead(node); //再在链表头部添加node
        }

        private void addToHead(myNode node) {
            node.pre = head;
            node.next = head.next;
            head.next.pre = node;
            head.next = node;
        }

        private void removeNode(myNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
    }


    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
}
