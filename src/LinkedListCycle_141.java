import java.util.HashSet;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class LinkedListCycle_141 {

    //遍历 哈希表法 时间复杂度O(n) 时间复杂度O(n)
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> seen=new HashSet<>();
        while(head!=null){
            if(seen.contains(head))
                return true;
            else seen.add(head);
            head=head.next;
        }
        return false;
    }


    //快慢指针 循环链表专门的解法 时间复杂度O(n)  空间复杂度O(1)
    //快的比慢的每次多一步，因此如果有圈，会陷入循环，直到快的套圈慢的，最坏时间复杂度为O(n+k) 快慢距离/速率差，
    //速率差为1，快慢距离为K圈的长度
     public boolean hasCycle2(ListNode head) {
        if(head==null || head.next==null) return false;
        ListNode slow=head;
        ListNode fast=head.next;
        while(slow!=fast){
            if(fast==null || fast.next==null) return false;
            slow=slow.next;
            fast=fast.next.next;
        }
        return true;
    }

}


class ListNode{
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}