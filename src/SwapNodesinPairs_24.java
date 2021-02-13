public class SwapNodesinPairs_24 {

    public ListNode swapPairs3(ListNode head) {
        ListNode pre =new ListNode(0);
        pre.next=head;
        ListNode temp=pre;
        while(temp.next!=null && temp.next.next!=null){
            ListNode first=temp.next;
            ListNode second=temp.next.next;
            temp.next=second;
            first.next=second.next;
            second.next=first;
            temp=first;
        }
        return pre.next;
    }



    //迭代
    public ListNode swapPairs2(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;
        while(temp.next != null && temp.next.next != null) {
            ListNode start = temp.next;
            ListNode end = temp.next.next;
            temp.next = end;
            start.next = end.next;
            end.next = start;
            temp = start;
        }
        return pre.next;
    }


    //递归从尾部向头部交换，当前head.next连接已交换完成的头部
    public ListNode swapPairs(ListNode head) {
        // 1. 终止条件：当前没有节点或者只有一个节点，肯定就不需要交换了
        if (head == null || head.next == null) return head;

        // 2. 调用单元
        // 需要交换的两个节点是 head 和 head.next
        ListNode firstNode = head;
        ListNode secondNode = head.next;
        // firstNode 连接后面交换完成的子链表的头
        firstNode.next = swapPairs(secondNode.next);
        // secondNode 连接 firstNode
        secondNode.next = firstNode;

        // 3. 返回值：返回交换完成的子链表头
        // secondNode 变成了头结点
        return secondNode;
    }



    class ListNode{
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
