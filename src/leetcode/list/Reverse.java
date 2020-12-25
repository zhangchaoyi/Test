package leetcode.list;

/**
 * Created by zcy on 18-5-16.
 * 206 todo
 *
 * 92. 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 *
 * ===============================================================
 * 按步长反转链表
 * 输入  1->2->3->4->5->6    k=3
 * 输出 3->2->1->6->5->4
 *
 */
public class Reverse {

    private static ListNode reverseNodeRecursive(ListNode head){
        if(head==null||head.next ==null)
            return head;
        ListNode prev = reverseNodeRecursive(head.next);//一直遍历到链表最后一个
        head.next.next = head;//翻转链表的指向
        head.next = null;
        return prev;
    }

    public static ListNode reverseNode(ListNode l){
        if (l == null)
        {
            return null;
        }
        //记录翻转后的最后一个节点
        ListNode reverseHead = null;
        // 指针1：当前节点
        ListNode currentNode = l;
        // 指针2：当前节点的前一个节点
        ListNode prevNode = null;

        while(currentNode != null)
        {
            // 指针3：当前节点的后一个节点
            ListNode nextNode = currentNode.next;
            if(nextNode == null)
            {
                reverseHead = currentNode;
            }
            // 将当前节点的后一个节点指向前一个节点
            currentNode.next = prevNode;
            // 将前一个节点指向当前节点
            prevNode = currentNode;
            // 将当前节点指向后一个节点
            currentNode = nextNode;
        }

        return reverseHead;
    }
}
