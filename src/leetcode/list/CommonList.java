package leetcode.list;

import java.util.Objects;

/**
 * @Author: chaoyi.zhang
 * @Date: 2020/9/23 17:52
 */
public class CommonList {

    /**
     * 找中点，双指针，快指针每次两步，慢指针每次一步
     *
     * @param head
     * @return
     */
    public static ListNode findMidNode(ListNode head) {
        if(Objects.isNull(head)){
            return null;
        }
        if (Objects.isNull(head.next)) {
            return head;
        }
        if (Objects.isNull(head.next.next)) {
            return head;
        }
        //至少3个, 中间元素偏左  -- 如果需要中间元素偏右则初始 ListNode fast = head;
        ListNode fast = head.next;
        ListNode slow = head;
        while(Objects.nonNull(fast)){
            fast = fast.next;
            if (Objects.isNull(fast)) {
                return slow;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args){
        ListNode head = initList(new int[]{0});

        System.out.println(cut(head, 2));
    }

    public static ListNode initList(int[] array){
        ListNode head = new ListNode(array[0]);

        ListNode lastNode = head;
        for(int i=1;i<array.length;i++){
            ListNode ln = new ListNode(array[i]);
            lastNode.next = ln;
            lastNode = ln;
        }
        return head;
    }

    /**
     * 两段有序链表二路归并
     * @param left  有序链表
     * @param right  有序链表
     * @return
     */
    public static ListNode merge(ListNode left, ListNode right){
        ListNode newHead = new ListNode(Integer.MIN_VALUE);
        ListNode tailNode = newHead;//tail标识最后一个元素

        while(Objects.nonNull(left) && Objects.nonNull(right)){
            if (left.val < right.val) {//add left
                tailNode.next = left;
                left = left.next;
            } else {//add right
                tailNode.next = right;
                right = right.next;
            }
            tailNode = tailNode.next;
        }
        if (Objects.isNull(left)) {//直接add right
            tailNode.next = right;
        } else if(Objects.isNull(right)){//直接add left
            tailNode.next = left;
        }

        return newHead.next;
    }

    /**
     * 截断step长度，返回剩余的链表， 需要把原链断开
     * 如 1->2->3->4->5->6->7     step = 3 , 返回4->5->6->7
     * 如果链表长度小于step，返回空
     * @param head
     * @return
     */
    public static ListNode cut(ListNode head, int step){
        if(Objects.isNull(head)){
            return null;
        }
        int count = 0;
        ListNode curNode = head;
        ListNode preNode = null;//记录前一个，用于断链
        while(count!=step && Objects.nonNull(curNode)){
            preNode = curNode;
            curNode = curNode.next;
            count++;
        }
        if(Objects.isNull(curNode) && count!=step){
            return null;
        }
        preNode.next = null;
        return curNode;
    }

}
