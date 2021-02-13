public class ReverseLinkedList_206 {
    //遍历翻转 双指针 时间复杂度O(n)
    public ListNode reverseList(ListNode head) {
        if(head==null) return null;
        ListNode first=null;
        ListNode second=head;
        while(second!=null){
            ListNode temp=second.next;
            second.next=first;
            first=second;
            second=temp;
        }
        return first;
    }

    //递归   nk（head）  nk+1的next要指向nk，则head.next.next=head; 再将nk的next指向空，
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newhead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newhead;
    }



    public ListNode reverseList3(ListNode head) {
        //终止条件：只有一个节点或递归到最后一个节点
        if(head==null || head.next==null) return head;
        ListNode ans=reverseList3(head.next);
        head.next.next=head;
        head.next=null;
        return ans;
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

