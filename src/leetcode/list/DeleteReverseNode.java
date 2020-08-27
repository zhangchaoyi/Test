package leetcode.list;

import java.util.Objects;

/**
 * 19. 删除链表的倒数第N个节点
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 *
 *  使用双指针， 指针1 + n = 指针2， 当指针2到尽头，即指针1的位置
 */
public class DeleteReverseNode {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(Objects.isNull(head)){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        int count = n;
        int len = 1;
        while (fast.next != null){
            if(count<=0){
                slow = slow.next;
            }
            fast = fast.next;
            count--;
            len++;
        }
        System.out.println("sss:"+count+" len:"+len);
        if(Objects.equals(fast, head)){
            return null;
        }
        if(Objects.equals(slow, head)){
            if(count>0){
                return slow.next;
            }

        }
        ListNode next = slow.next;
        slow.next = next.next;

        return head;
    }

    public static void main(String[] args){
        //1->2->3->4->5
        DeleteReverseNode drn = new DeleteReverseNode();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
//        ListNode l3 = new ListNode(3);
//        ListNode l4 = new ListNode(4);
//        ListNode l5 = new ListNode(5);
        l1.next = l2;
//        l2.next = l3;
//        l3.next = l4;
//        l4.next = l5;

        System.out.println(drn.removeNthFromEnd(l1, 1));

    }
}
