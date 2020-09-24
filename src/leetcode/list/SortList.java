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

    public static void main(String[] args){
        SortList sl = new SortList();
        ListNode l = CommonList.initList(new int[]{-1,5,3,4,0});
        ListNode result = sl.mergeSortList(l);

        System.out.println(result);
    }

    public ListNode sortList(ListNode head) {
        return mergeSortList(head);
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
        head = merge(left, right);

        return head;
    }

    /**
     * 两段有序链表二路归并
     * @param left  有序链表
     * @param right  有序链表
     * @return
     */
    public ListNode merge(ListNode left, ListNode right){
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
