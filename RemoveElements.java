/*
 * ÊäÈë: 1->2->6->3->4->5->6, val = 6
Êä³ö: 1->2->3->4->5
 */
public class RemoveElements {
	public ListNode removeElements(ListNode head, int val) {
        while(head != null && head.val == val)
        {
        	head = head.next;
        }
        ListNode temp = head;
        if(temp != null)
        {
	        while(temp.next != null)
	        {
	        	if(temp.next.val == val)
	        	{
	        		temp.next = temp.next.next;
	        		continue;
	        	}
	        	temp = temp.next;
	        }
        }
        return head;
    }
}
