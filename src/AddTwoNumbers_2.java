public class AddTwoNumbers_2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans=new ListNode(0);
        ListNode head=ans,p=l1,q=l2;
        int carry=0;
        while(p!=null && q!=null) {
            int x=(p==null)? 0:p.val;
            int y=(q==null)? 0:q.val;
            int sum=carry+x+y;
            carry=sum/10;
            head.next=new ListNode(sum%10);
            head=head.next;
            if(l1!=null) l1=l1.next;
            if(l2!=null) l2=l2.next;
        }
        if(carry>0) head.next=new ListNode(carry);
        return ans.next;
    }
}
