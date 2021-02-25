import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache_460 {
    //460. LFU Cache
    //Design and implement a data structure for a Least Frequently Used (LFU) cache.
    //Implement the LFUCache class:
    //LFUCache(int capacity) Initializes the object with the capacity of the data structure.
    //int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
    //void put(int key, int value) Update the value of the key if present, or inserts the key if not already
    //present. When the cache reaches its capacity, it should invalidate and remove the least frequently
    //used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys
    //with the same frequency), the least recently used key would be invalidated.

    //To determine the least frequently used key, a use counter is maintained for each key in the cache.
    //The key with the smallest use counter is the least frequently used key.
    //When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation).
    //The use counter for a key in the cache is incremented either a get or put operation is called on it.

    //Example 1:
    //Input
    //["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
    //[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
    //Output
    //[null, null, null, 1, null, -1, 3, null, -1, 3, 4]

    //Explanation:
    //// cnt(x) = the use counter for key x
    //// cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
    //LFUCache lfu = new LFUCache(2);
    //lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
    //lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
    //lfu.get(1);      // return 1
    //                 // cache=[1,2], cnt(2)=1, cnt(1)=2
    //lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
    //                 // cache=[3,1], cnt(3)=1, cnt(1)=2
    //lfu.get(2);      // return -1 (not found)
    //lfu.get(3);      // return 3
    //                 // cache=[3,1], cnt(3)=2, cnt(1)=2
    //lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
    //                 // cache=[4,3], cnt(4)=1, cnt(3)=2
    //lfu.get(1);      // return -1 (not found)
    //lfu.get(3);      // return 3
    //                 // cache=[3,4], cnt(4)=1, cnt(3)=3
    //lfu.get(4);      // return 4
    //                 // cache=[3,4], cnt(4)=2, cnt(3)=3

    //Constraints:
    //    0 <= capacity, key, value <= 104
    //    At most 105 calls will be made to get and put.
    //Follow up: Could you do both operations in O(1) time complexity?

    //哈希表 + 1个双向链表 插入新元素操作时间都咋读O(n)，遍历查找最近的比当前freq大的node
    static class LFUCache {
        class myNode {
            int key;
            int value;
            int freq;
            myNode pre;
            myNode next;
            public myNode() {}
            public myNode(int key, int value) {
                this.key = key;
                this.value = value;
                freq = 0;
            }
        }

        int capacity, size;
        myNode head, tail;
        HashMap<Integer, myNode> hashMap;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            size = 0;
            head = new myNode();
            tail = new myNode();
            head.next = tail;
            tail.pre = head;
            hashMap = new HashMap<>();
        }

        public int get(int key) {
            myNode node = hashMap.getOrDefault(key, null);
            if(node == null) return -1;
            node.freq++; //频率+1
            moveForward(node); //检查当前节点在频率+1之后，是否需要上移
            return node.value;
        }

        public void put(int key, int value) {
            if(capacity == 0) return;
            myNode node = hashMap.getOrDefault(key, null);
            if(node == null) {
                if(size == capacity) {
                    myNode curtail = removeTail();
                    hashMap.remove(curtail.key);
                    size--;
                }
                node = new myNode(key, value);
                hashMap.put(key, node);
                addTailForward(node);
                size++;
            } else {
                node.value = value;
                node.freq++;
                moveForward(node);
            }
        }

        private myNode removeTail() {
            myNode curtail = tail.pre;
            curtail.pre.next = tail;
            tail.pre = curtail.pre;
            return curtail;
        }

        private void addTailForward(myNode node) {
            //向前寻找最近的频率 > 当前节点频率的节点,将node插入该节点后面，并将原节点删除
            myNode node2 = findBiggerFreq(tail);
            moveTo(node, node2);
        }

        //检查当前node的freq
        private void moveForward(myNode node) {
            //向前寻找最近的频率 > 当前节点频率的节点,将node插入该节点后面，并将原节点删除
            myNode node2 = findBiggerFreq(node);
            if (node2 == node.pre) return; //pre的freq正好大于等于当前节点的freq，不需要往前移动位置
            removeNode(node);
            moveTo(node, node2);
        }

        private void moveTo(myNode node, myNode node2) {
            node.pre = node2;
            node.next = node2.next;
            node2.next.pre = node;
            node2.next = node;
        }

        private void removeNode(myNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        private myNode findBiggerFreq(myNode node) {
            myNode bigNode = node.pre;
            while (bigNode != head && bigNode.freq <= node.freq) bigNode = bigNode.pre;
            return bigNode;
        }
    }


    //2. 双hashmap  链表直接用LinkedHashSet替代(遍历顺序与插入顺序一致，即第一个是最早插入的) 时间复杂度O(1)
    static class LFUCache2 {
        class myNode {
            int key;
            int value;
            int freq;
            public myNode() {}
            public myNode(int k, int v) {
                this.key = k;
                this.value = v;
                freq = 1;
            }
        }

        HashMap<Integer, myNode> hashMap = new HashMap<>();
        HashMap<Integer, LinkedHashSet> list= new HashMap<>();
        //用过freq区分的链表。每个freq新建一个链表
        int size, capacity;
        int min; //记录当前的最小频次,当容量溢出，最先删除

        public LFUCache2(int capacity) {
            size = 0;
            this.capacity = capacity;
            hashMap = new HashMap<>();
            list = new HashMap<>();
        }

        public int get(int key) {
            myNode node = hashMap.getOrDefault(key, null);
            if(node == null) return -1;
            moveAsFreq(node);
            return node.value;
        }

        public void put(int key, int value) {
            if(capacity == 0) return;
            myNode node = hashMap.getOrDefault(key, null);
            if(node == null) { //元素不存在
                if(size == capacity) {
                    myNode minnode = removeMinFreq();
                    hashMap.remove(minnode.key);
                    size--;
                }
                node = new myNode(key, value);
                hashMap.put(key, node);
                addNode(node);
                size++;
            } else { //元素已存在
                node.value = value;
                moveAsFreq(node);
            }
        }

        private myNode removeMinFreq() {
            LinkedHashSet<myNode> set = list.get(min);
            myNode minnode = set.iterator().next();
            set.remove(minnode);
            return minnode;
        }

        private void addNode(myNode node) {
            LinkedHashSet set = list.computeIfAbsent(1, k -> new LinkedHashSet());
            set.add(node);
            min = 1;
        }

        private void moveAsFreq(myNode node) {
            //先删除原freq中的node，再加入新的freq
            LinkedHashSet set = list.get(node.freq);
            set.remove(node);
            if(min == node.freq && set.size() == 0) min = node.freq + 1;
            node.freq++;
            LinkedHashSet temp = list.computeIfAbsent(node.freq, k -> new LinkedHashSet());
            temp.add(node);
        }

    }

    //3. 思想同上 双hashmap + 自定义双向链表 时间复杂度O(1)
    static class LFUCache3 {
        class myNode {
            int key, value, freq;
            myNode pre, next;
            public myNode() {}
            public myNode(int k, int v) {
                key = k;
                value = v;
                freq = 1;
            }
        }

        int size, capacity, min;
        HashMap<Integer, myNode> hashMap;
        HashMap<Integer, DoubleLinkedList> freqMap;

        public LFUCache3(int capacity) {
            size = 0;
            this.capacity = capacity;
            hashMap = new HashMap<>();
            freqMap = new HashMap<>();
        }

        public int get(int key) {
            //获取node的value，且freq++，freqMap中原来freq的链表里删除，添加到freq+1的链表里
            myNode node = hashMap.getOrDefault(key, null);
            if(node == null) return -1;
            moveOneFreq(node);
            return node.value;
        }

        public void put(int key, int value) {
            //如果元素已存在，更改value，和freq，moveFreq。
            //如果元素不存在，先看容量是否溢出，溢出先删除freq最小链表中的节点，再新建node，addToFreq
            if (capacity == 0) return;
            myNode node = hashMap.getOrDefault(key, null);
            if(node == null) {
                if(size == capacity) {
                    DoubleLinkedList list = freqMap.get(min);
                    hashMap.remove(list.tail.pre.key);
                    list.removeNode(list.tail.pre);
                    size--;
                }
                node = new myNode(key, value);
                hashMap.put(key, node);
                addToFreq(node, 1);
                min = 1; //新加的元素freq都为1，所以min更新为1
                size++;
            } else {
                node.value = value;
                moveOneFreq(node);
            }
        }

        private void moveOneFreq(myNode node) {
            removeFromFreq(node);
            node.freq++;
            addToFreq(node, node.freq);
        }

        private void addToFreq(myNode node, int freq) {
            DoubleLinkedList list = freqMap.computeIfAbsent(freq, k -> new DoubleLinkedList());
            list.addNode(node);
            freqMap.put(freq, list);
        }

        private void removeFromFreq(myNode node) { //判断当前是否为最小freq的list，且内部元素是否为0？ 是min++;
            DoubleLinkedList list = freqMap.get(node.freq);
            list.removeNode(node);
            if(node.freq == min && list.size == 0) min++;
        }

        private class DoubleLinkedList {
            //链表内每次添加新节点到头部，因为容量溢出需要删除节点删除最早插入的节点
            int size; //当前元素数量
            myNode head, tail;
            public DoubleLinkedList() {
                head = new myNode();
                tail = new myNode();
                head.next = tail;
                tail.pre = head;
                size = 0;
            }

            //addNode
            public void addNode(myNode node) {
                node.pre = head;
                node.next = head.next;
                head.next.pre = node;
                head.next = node;
                size++;
            }

            //removeNode
            public void removeNode(myNode node) {
                node.pre.next = node.next;
                node.next.pre = node.pre;
                size--;
            }
        }
    }









    /**
     * Your LFUCache object will be instantiated and called as such:
     * LFUCache obj = new LFUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
}
