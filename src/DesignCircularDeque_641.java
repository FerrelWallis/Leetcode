/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
public class DesignCircularDeque_641 {
    int[] myqueue ;
    int front;//队头指针
    int rear;//队尾指针
    int size;//队列当前的大小
    int capacity;//队列的容量

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public DesignCircularDeque_641(int k) {
        this.myqueue = new int[k];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
        this.capacity = k;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if(rear==front && size == capacity) return  false;//如果队列满，插入失败
        else {
            front = (front + capacity -1)% capacity;
            myqueue[front] = value;
            size++;
            return true;
        }
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if(rear==front && size == capacity) return  false;//如果队列满，插入失败
        else {
            myqueue[rear] = value;
            rear = (rear+1+capacity)%capacity;
            size++;
            return true;
        }

    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if( rear == front && size == 0) return false;
        else {
            front = (front+1) % capacity;
            size--;
            return true;
        }

    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if( rear == front && size == 0) return false;
        else {
            rear = (rear - 1 + capacity) % capacity;
            size--;
            return true;
        }

    }

    /** Get the front item from the deque. */
    public int getFront() {
        if((rear == front) && size==0) return -1;
        else {
            int frontE = myqueue[front];
            //front = (front + 1) % capacity;
            //size--;
            return frontE;
        }
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if((rear == front) && size==0) return -1;
        else {
            int rearE = myqueue[(rear-1+capacity)%capacity];
            // rear = (rear - 1 +capacity)%capacity;
            //size--;
            return rearE;
        }

    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return (rear == front) && size==0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return rear==front && size == capacity;

    }




    //1.数组 实现 跟622同源
//    private int[] deque;
//    private int head;
//    private int tail;
//    private int capacity;
//
//    /**
//     * Initialize your data structure here. Set the size of the deque to be k.
//     */
//    public DesignCircularDeque_641(int k) {
//        capacity=k+1;
//        deque=new int[k+1];
//        head=0;
//        tail=0;
//    }
//    /**
//     * Adds an item at the front of Deque. Return true if the operation is successful.
//     */
//    public boolean insertFront(int value) {
//        if(isFull()) return false;
//        head=(head-1+capacity)%capacity;
//        deque[head]=value;
//        return true;
//    }
//
//    /**
//     * Adds an item at the rear of Deque. Return true if the operation is successful.
//     */
//    public boolean insertLast(int value) {
//        if(isFull()) return false;
//        deque[tail]=value;
//        tail=(tail+1)%capacity;
//        return true;
//    }
//
//    /**
//     * Deletes an item from the front of Deque. Return true if the operation is successful.
//     */
//    public boolean deleteFront() {
//        if(isEmpty()) return false;
//        head=(head+1)%capacity;
//        return true;
//    }
//
//    /**
//     * Deletes an item from the rear of Deque. Return true if the operation is successful.
//     */
//    public boolean deleteLast() {
//        if(isEmpty()) return false;
//        tail=(tail-1+capacity)%capacity;
//        return true;
//    }
//
//    /**
//     * Get the front item from the deque.
//     */
//    public int getFront() {
//        if(isEmpty()) return -1;
//        return deque[head];
//    }
//
//    /**
//     * Get the last item from the deque.
//     */
//    public int getRear() {
//        if(isEmpty()) return -1;
//        return deque[(tail-1+capacity)%capacity];
//    }
//
//    /**
//     * Checks whether the circular deque is empty or not.
//     */
//    public boolean isEmpty() {
//        return (head==tail);
//    }
//
//    /**
//     * Checks whether the circular deque is full or not.
//     */
//    public boolean isFull() {
//        return (((tail+1)%capacity)==head);
//    }


}
