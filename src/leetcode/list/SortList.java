package leetcode.list;

import java.util.Objects;

/**
 * 在 O(nlogn) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * 1.归并, 时间复杂度 O(nlogn) , 空间复杂度虽然无需像数组一样申请空间，递归深度 O(logn)
 *
 * 2.bomToTop
 *
 */
public class SortList {

    //时间复杂度O(nlogn)，空间复杂度O(1)
    public ListNode sortList(ListNode head) {
        //加一个头结点，处理边界节点时，逻辑相同
        ListNode dummyHead = new ListNode(Integer.MAX_VALUE);
        dummyHead.next = head;

        //计算长度
        int length = 0;
        ListNode work = head;
        while (work != null) {
            work = work.next;
            length++;
        }

        ListNode tail = dummyHead;
        //每次步长*2
        for (int step = 1; step < length; step <<= 1) {
            work = dummyHead.next;
            tail = dummyHead;//记录每一趟归并的结果，下趟归并时重置tail
            while (work != null) {
                /*
                 * 第一句left->@->@->@->@->@->@->null
                 * 第二句left->@->@->null   right->@->@->@->@->null
                 * 第三句left->@->@->null   right->@->@->null   work->@->@->null
                 */
                ListNode left = work;
                ListNode right = cut(left, step);//将链表拆成两部分，左边为step长链表，右边为剩余链表
                work = cut(right, step);//这步执行完毕，left为step链表，right为step链表，work为剩余链表，下一趟排序的基础链表

                //总是把tail移到当前链表的最后一个位置，用于拼接下一趟循环产生的结果
                tail.next = merge(left, right);
                while (tail.next != null) {
                    tail = tail.next;
                }
            }
        }
        return dummyHead.next;
    }

    //作用：在step位置断链，并返回后面部分的链头
    public ListNode cut(ListNode head, int step) {
        while (--step != 0 && head != null) {
            head = head.next;
        }
        //如果链表不够step长，就返回空
        if (head != null) {
            ListNode result = head.next;
            head.next = null;
            return result;
        }else {
            return null;
        }
    }

    //归并两条链表
    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode work = new ListNode(0);
        ListNode head = work;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                work.next = l1;
                l1 = l1.next;
            } else {
                work.next = l2;
                l2 = l2.next;
            }
            work = work.next;
        }
        if (l1 != null) {
            work.next = l1;
        }
        if (l2 != null) {
            work.next = l2;
        }
        return head.next;
    }

    public static void main(String[] args){
        SortList sl = new SortList();
        ListNode l = CommonList.initList(new int[]{-1,5,3,4,0});
        ListNode result = sl.mergeSortList(l);

        System.out.println(result);
    }

    /**
     * 找中点： 快、慢双指针进行
     * @param head
     * @return
     */
    public ListNode mergeSortList(ListNode head){
        if(Objects.isNull(head)){
            return null;
        }
        if(Objects.isNull(head.next)){
            return head;
        }

        ListNode mid = CommonList.findMidNode(head);
        ListNode midNext = mid.next;
        mid.next = null;//断链

        ListNode left = mergeSortList(head);
        ListNode right = mergeSortList(midNext);
        head = mergeS(left, right);

        return head;
    }

    /**
     * 两段有序链表二路归并
     * @param left  有序链表
     * @param right  有序链表
     * @return
     */
    public ListNode mergeS(ListNode left, ListNode right){
        ListNode newHead = new ListNode(Integer.MIN_VALUE);
        ListNode tailNode = newHead;//tail标识最后一个元素

        while(Objects.nonNull(left) || Objects.nonNull(right)){
            if (Objects.isNull(left)) {//直接add right
                tailNode.next = right;
                right = right.next;
            } else if(Objects.isNull(right)){//直接add left
                tailNode.next = left;
                left = left.next;
            } else {
                if (left.val < right.val) {//add left
                    tailNode.next = left;
                    left = left.next;
                } else {//add right
                    tailNode.next = right;
                    right = right.next;
                }
            }
            tailNode = tailNode.next;
        }

        return newHead.next;
    }
}
