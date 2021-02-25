import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheLCCI_16_25 {
    //面试题 16.25. LRU Cache LCCI
    //Design and build a "least recently used" cache, which evicts the least recently used item.
    //The cache should map from keys to values (allowing you to insert and retrieve a value
    //associ­ated with a particular key) and be initialized with a max size. When it is full,
    //it should evict the least recently used item.
    //You should implement following operations:  get and put.
    //Get a value by key: get(key) - If key is in the cache, return the value, otherwise return -1.
    //Write a key-value pair to the cache: put(key, value) - If the key is not in the cache, then write
    //its value to the cache. Evict the least recently used item before writing if necessary.

    //Example:
    //LRUCache cache = new LRUCache( 2 /* capacity */ );
    //cache.put(1, 1);
    //cache.put(2, 2);
    //cache.get(1);       // returns 1
    //cache.put(3, 3);    // evicts key 2
    //cache.get(2);       // returns -1 (not found)
    //cache.put(4, 4);    // evicts key 1
    //cache.get(1);       // returns -1 (not found)
    //cache.get(3);       // returns 3
    //cache.get(4);       // returns 4

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
                pre = new myNode();
                next = new myNode();
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
            curtail.pre.next = tail;
            tail.pre = curtail.pre;
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
