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
        ListNode head = initList(new int[]{1, 2, 3, 4, 5, 6});

        CommonList cl = new CommonList();
        System.out.println(cl.findMidNode(head));
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

}
