package leetcode.list;

/**
 * Created by zcy on 18-5-31.
 */
public class DeleteNode {

    public void deleteNode(ListNode head, int val){
        ListNode pre = null;
        ListNode node = head;
        while(node != null){
            if(node.val == val){
                if(pre == null){//head
                    head = head.next;
                    break;
                }
                pre.next = node.next;
                break;
            }
            pre = node;
            node = node.next;
        }

        System.out.println(head);
    }

    public static void main(String[] args){
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(6);
        ListNode l4 = new ListNode(8);
        ListNode l5 = new ListNode(10);
        ListNode l6 = new ListNode(12);
        ListNode l7 = new ListNode(14);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;

        System.out.println(l1);

        DeleteNode deleteNode = new DeleteNode();
        deleteNode.deleteNode(l1, 4);

    }
}
