package leetcode.list;

import java.util.Objects;

/**
 * 21. 合并两个有序链表
 *
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 *
 *
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 */
public class MergeTwoList {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(Objects.isNull(l1)){
            return l2;
        }
        if(Objects.isNull(l2)){
            return l1;
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while(Objects.nonNull(l1) && Objects.nonNull(l2)){
            if(l1.val <= l2.val){
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if(Objects.nonNull(l1)){
            tail.next = l1;
        }
        else if(Objects.nonNull(l2)){
            tail.next = l2;
        }

        return dummy.next;
    }

    public static void main(String[] args){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);

        l1.next = l2;
        l2.next = l3;


        ListNode l8 = new ListNode(1);
        ListNode l9 = new ListNode(3);
        ListNode l10 = new ListNode(4);

        l8.next = l9;
        l9.next = l10;


        MergeTwoList mtl = new MergeTwoList();
        System.out.println(mtl.mergeTwoLists(l1, l8));
    }
}
