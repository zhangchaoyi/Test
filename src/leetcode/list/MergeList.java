package leetcode.list;

/**
 * Created by zcy on 18-5-16.
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 输入:
 [
 1->4->5,
 1->3->4,
 2->6
 ]
 输出: 1->1->2->3->4->4->5->6

 插入排序 时间复杂度O(n平方)
 ----------------------------------------------------------------------------------
 可以改进算法，每次同时对k个list取出各个list的第一个元素比较大小 并且取出最小的一个，循环取数得到最终结果 时间复杂度O(k*n))
 */
public class MergeList {

    public static ListNode mergeKLists(ListNode[] lists){
        if(lists == null || lists.length == 0){
            return null;
        }

        ListNode head = lists[0];//第一个排序链表头节点

        for(int i=1 ; i<lists.length; i++){
            ListNode node = lists[i];
            while(node != null){
                ListNode next = node.next;
                head = addNode(head, node);
                node = next;
            }
        }

        return head;
    }

    /**
     * 将当前node插入到有序的链表中
     * @param sortListHead
     * @param insertNode
     */
    private static ListNode addNode(ListNode sortListHead, ListNode insertNode){
        ListNode newNode = new ListNode(insertNode.val);
        if(sortListHead == null){
            return newNode;
        }
        ListNode cur = sortListHead;
        ListNode prev = null;
        while(cur != null && cur.val <= newNode.val){
            prev = cur;
            cur = cur.next;
        }
        if(cur != null){//当前的cur比insert大
            if(prev == null){//头节点
                newNode.next = cur;
                sortListHead = newNode;
            } else {
                prev.next = newNode;
                newNode.next = cur;
            }
        } else {//添加到最后节点
            prev.next = newNode;
        }

        return sortListHead;
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

        ListNode l8 = new ListNode(3);
        ListNode l9 = new ListNode(3);
        ListNode l10 = new ListNode(5);
        ListNode l11 = new ListNode(7);
        ListNode l12 = new ListNode(9);
        ListNode l13 = new ListNode(11);
        ListNode l14 = new ListNode(13);

        l8.next = l9;
        l9.next = l10;
        l10.next = l11;
        l11.next = l12;
        l12.next = l13;
        l13.next = l14;

        ListNode l15 = new ListNode(1);
        ListNode l16 = new ListNode(4);
        ListNode l17 = new ListNode(6);
        ListNode l18 = new ListNode(12);
        ListNode l19 = new ListNode(15);
        ListNode l20 = new ListNode(19);
        ListNode l21 = new ListNode(20);

        l15.next = l16;
        l16.next = l17;
        l17.next = l18;
        l18.next = l19;
        l19.next = l20;
        l20.next = l21;

        System.out.println(l1);
        System.out.println(l8);
        System.out.println(l15);


        ListNode[] lists = new ListNode[]{};
        System.out.println(mergeKLists(lists));

    }
}
