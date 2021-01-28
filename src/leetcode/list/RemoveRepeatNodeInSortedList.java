package leetcode.list;

import java.util.Objects;

/**
 *
 * 删除有序链表中重复出现的元素
 * 限定语言：C++、Java
 * 给出一个升序排序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
 * 例如：
 * 给出的链表为 1→2→3→3→4→4→5, 返回 1→2→5.
 * 给出的链表为 1→1→1→2→3, 返回 2→3.
 *
 * 思路：1.使用linkedHashMap 或 转成数组
 *      2、O(n) 一次遍历链表
 */
public class RemoveRepeatNodeInSortedList {

    public ListNode deleteDuplicateNode(ListNode head){
        ListNode headRes = null;
        ListNode lastNode = null;
        ListNode cur = head;
        ListNode lastDuplicateNode = null;

        while(Objects.nonNull(cur)){
            ListNode next = cur.next;
            if(Objects.isNull(next)){
                lastNode.next = new ListNode(cur.val);
                return headRes;
            }

            if(Objects.nonNull(lastDuplicateNode)){
                if(Objects.equals(lastDuplicateNode.val, cur.val)){
                    cur=cur.next;
                    continue;
                }
            }

            if(!Objects.equals(next.val, cur.val)){
                if(Objects.isNull(headRes)){
                    headRes = new ListNode(cur.val);
                    lastNode = headRes;
                } else {
                    lastNode.next = new ListNode(cur.val);
                    lastNode = lastNode.next;
                }
            } else {
                lastDuplicateNode = cur;
            }

            cur = cur.next;
        }

        return headRes;

    }

    public static void main(String[] args){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(1);
        ListNode l4 = new ListNode(1);
        ListNode l5 = new ListNode(1);
        ListNode l6 = new ListNode(4);
        ListNode l7 = new ListNode(5);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;

        RemoveRepeatNodeInSortedList r = new RemoveRepeatNodeInSortedList();
        ListNode res = r.deleteDuplicateNode(l1);
    }
}
