package leecode.list;

/**
 * Created by zcy on 18-5-16.
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

    private static ListNode reverseNode(ListNode l){
        if (l == null)
        {
            return null;
        }

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
