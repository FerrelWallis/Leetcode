import java.util.concurrent.locks.ReentrantLock;

public class DesignCircularQueue_622 {

    //1. 数组实现
//    private int capacity;
//    private int[] queue;
//    private int head;
//    private int count;
//    /** Initialize your data structure here. Set the size of the queue to be k. */
//    public DesignCircularQueue_622(int k) {
//        capacity=k;
//        queue=new int[k];
//        head=0;
//        count=0;
//    }
//    /** Insert an element into the circular queue. Return true if the operation is successful. */
//    public boolean enQueue(int value) {
//        if(count==capacity) return false;
//        queue[(head+count)%capacity]=value;
//        count+=1;
//        return true;
//    }
//    /** Delete an element from the circular queue. Return true if the operation is successful. */
//    public boolean deQueue() {
//        if(count==0) return false;
//        head=(head+1)%capacity;
//        count--;
//        return true;
//    }
//    /** Get the front item from the queue. */
//    public int Front() {
//        if(count==0) return -1;
//        return queue[head];
//    }
//    /** Get the last item from the queue. */
//    public int Rear() {
//        if(count==0) return -1;
//        return queue[(head+count-1)%capacity];
//    }
//    /** Checks whether the circular queue is empty or not. */
//    public boolean isEmpty() {
//        return (count==0);
//    }
//    /** Checks whether the circular queue is full or not. */
//    public boolean isFull() {
//        return (count==capacity);
//    }

    //2.单链表+ReentrantLock实现,优化数组实现，实现了线程安全
    private int capacity;
    private int count;
    ListNode head,tail;
    ReentrantLock lock=new ReentrantLock();
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public DesignCircularQueue_622(int k) {
        capacity=k;
        count=0;
    }
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        lock.lock();
        try {
            if(count==capacity) return false;
            ListNode cur=new ListNode(value);
            if(count==0) head=tail=cur;
            else {
                tail.next=cur;
                tail=tail.next;
            }
            count++;
        }finally {
            lock.unlock();
        }
        return true;
    }
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        lock.lock();
        try {
            if(count==0) return false;
            head=head.next;
            count--;
        }finally {
            lock.unlock();
        }
        return true;
    }
    /** Get the front item from the queue. */
    public int Front() {
        if(count==0) return -1;
        return head.val;
    }
    /** Get the last item from the queue. */
    public int Rear() {
        if(count==0) return -1;
        return tail.val;
    }
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return (count==0);
    }
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return (count==capacity);
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


}
/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */