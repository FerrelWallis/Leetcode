public class MergeTwoSortedLists_21 {

    //递归
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        else if(l2==null) return l1;
        else if(l1.val<=l2.val){
            l1.next=mergeTwoLists2(l1.next,l2);
            return l1;
        }else {
            l2.next=mergeTwoLists2(l1,l2.next);
            return l2;
        }
    }


    //迭代 时间复杂度O(n+m) 空间复杂度O(n+m)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newhead=new ListNode(0);
        ListNode prev=newhead;
        while(l1!=null && l2!=null){
            if(l1.val<=l2.val){
                prev.next=l1;
                l1=l1.next;
            }else {
                prev.next=l2;
                l2=l2.next;
            }
            prev=prev.next;
        }
        prev.next = l1==null? l2:l1;
        return newhead.next;
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
