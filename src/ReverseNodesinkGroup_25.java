public class ReverseNodesinkGroup_25 {



    //递归
    public ListNode reverseKGroup2(ListNode head, int k) {
        if(head==null || head.next==null) return head;
        ListNode tail=head;
        for(int i=0;i<k;i++){
            if(tail==null) return head;
            tail=tail.next;
        }
        ListNode newhead=reverse2(head,tail);
        head.next=reverseKGroup2(tail,k);
        return newhead;
    }

    public ListNode reverse2(ListNode head,ListNode tail){
        ListNode pre=null;
        ListNode next=null;
        while(head!=tail){
            next=head.next;
            head.next=pre;
            pre=head;
            head=next;
        }
        return pre;
    }


    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            //剩余数量小于k的话，则不需要反转。
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(head, tail);
        //下一轮的开始的地方就是tail
        head.next = reverseKGroup(tail, k);

        return newHead;
    }

    /*
    左闭又开区间
     */
    private ListNode reverse(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode next = null;
        while (head != tail) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
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
