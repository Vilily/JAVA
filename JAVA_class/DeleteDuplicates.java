/*
 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

示例 1:

输入: 1->2->3->3->4->4->5
输出: 1->2->5
示例 2:

输入: 1->1->1->2->3
输出: 2->3
 */
public class DeleteDuplicates {
	public ListNode deleteDuplicates(ListNode head) {
		ListNode head_last = head;
		ListNode head_temp = head;
		ListNode last = head;
		ListNode temp = head;
		
        while(head_temp != null)
        {
        	boolean is_duplicate = false;
        	last = head_temp;
        	temp = last.next;
        	
        	while(temp != null)
        	{
        		if(temp.val == head_temp.val)
        		{
        			temp = temp.next;
        			last.next = temp;
        			is_duplicate = true;
        		}
        		else 
        		{
        			last = temp;
        			temp = temp.next;
				}
        	}
        	
        	if(is_duplicate)
        	{
        		if(head_last == head_temp)
        		{
        			head_temp = head_temp.next;
        			head_last = head_temp;
        			head = head_temp;
        		}
        		else 
        		{
        			head_temp = head_temp.next;
        			head_last.next = head_temp;
				}
        	}
        	else 
        	{
				head_last = head_temp;
				head_temp = head_temp.next;
			}
        }
        return head;
    }
        	
        
	
	public void delete(ListNode last)
	{
		last.next = last.next.next;
	}
}

/*
 执行用时 :
4 ms
, 在所有 java 提交中击败了
6.35%
的用户
内存消耗 :
36.4 MB
, 在所有 java 提交中击败了
65.11%
的用户
 */

