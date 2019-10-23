import java.util.Scanner;

/*
给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

示例 1:

输入: 1->2->3->4->5->NULL, k = 2
输出: 4->5->1->2->3->NULL
解释:
向右旋转 1 步: 5->1->2->3->4->NULL
向右旋转 2 步: 4->5->1->2->3->NULL
示例 2:

输入: 0->1->2->NULL, k = 4
输出: 2->0->1->NULL
解释:
向右旋转 1 步: 2->0->1->NULL
向右旋转 2 步: 1->2->0->NULL
向右旋转 3 步: 0->1->2->NULL
向右旋转 4 步: 2->0->1->NULL
 */
public class RotateList {
	public ListNode rotateRight(ListNode head, int k) {
		int n = 0;
		if(head!=null)
		{
			ListNode temp = head;
			while(temp.next != null)
			{
				temp = temp.next;
				n++;
			}
			temp.next = head;
			for(int i=0;i < n-(k%(n+1));i++)
			{
				head = head.next;
			}
			temp = head.next;
			head.next = null;
			return temp;
		}
		else 
		{
			return head;
		}
    }
	
	public static void main(String[] args)
	{
		int n;
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		ListNode head = null;
		for(int i = 0; i < n; i++)
		{
			int x = in.nextInt();
			ListNode temp = new ListNode(x);
			temp.next = head;
			head = temp;
		}
		ListNode temp = head;
		while(temp != null)
		{
			System.out.print(temp.val + " ");
			temp = temp.next;
		}
		System.out.print("\n");
		RotateList a = new RotateList();
		temp = a.rotateRight(head, 6);
		while(temp != null)
		{
			System.out.print(temp.val + " ");
			temp = temp.next;
		}
		
	}
}
