import java.util.HashSet;

public class LinkedListCycleII_142 {
    //1. 哈希表 4ms
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> seen=new HashSet<>();
        while(head!=null){
            if(seen.contains(head)) return head;
            else {
                seen.add(head);
                head=head.next;
            }
        }
        return null;
    }

    //快慢双指针法（环形链表专用解法）距离尾部第K个节点、寻找环入口、寻找公共尾部入口等问题都用双指针法
    //第一次重合的情况满足两个条件：1. f=2s   2. f=s+nb   由1、2可以得出 s=nb f=2nb
    //所以 在重合之后，再次走a步就能第二次重合，即 s=a+nb   f=a+2nb,且重合点就是圈的入口
    //因此，第一次重合后，fast回到起点，每次走一格，将会与slow在圈的入口相遇
    public ListNode detectCycle3(ListNode head) {
        ListNode fast=head;
        ListNode slow=head;
        while(true){
            if(fast==null||fast.next==null) return null;
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow) break;
        }
        fast=head;
        while(fast!=slow){
            fast=fast.next;
            slow=slow.next;
        }
        return fast;
    }


    public ListNode detectCycle2(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
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
