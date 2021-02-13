import java.util.HashSet;

public class RemoveDuplicatesfromSortedList_83 {

    public static void main(String[] args) {

    }

    //1.哈希
    public ListNode deleteDuplicates(ListNode head) {
        HashSet<Integer> seen=new HashSet<>();
        ListNode pre=head;
        seen.add(pre.val);
        while(pre!=null){
            if(seen.contains(pre.next.val)) {
                pre.next=pre.next.next;
            }else{
                seen.add(pre.next.val);
                pre=pre.next;
            }
        }
        return head;
    }

    //2.单指针
    public ListNode deleteDuplicates3(ListNode head) {
        ListNode cur=head;
        while (cur!=null&&cur.next!=null){
            if(cur.val==cur.next.val) cur.next=cur.next.next;
            else cur=cur.next;
        }
        return head;
    }


    public ListNode deleteDuplicates2(ListNode head) {
        ListNode cur=head;
        while(cur!=null && cur.next!=null){
            if(cur.next.val==cur.val){
                cur.next=cur.next.next;
            }else{
                cur=cur.next;
            }
        }
        return head;
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
