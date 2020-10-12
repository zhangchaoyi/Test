package leetcode.list;

import java.util.Objects;

/**
 * 判断链表是否存在环
 *
 * 给定一个链表，判断链表中是否有环。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 *
 *
 * 进阶：
 *
 * 你能用 O(1)（即，常量）内存空间复杂度解决此问题吗？
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 3 —> 2 -> 0
 *       \  /
 *        -4
 *
 *
 * 双指针解决的问题： 倒数第k个数、找链表的中点、判断环、环长度(在快慢指针相遇后开始计步数，直到两个指针第二次相遇即可得到步数)
 * 时间复杂度O(n) 空间复杂度 O(1)
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/10/12 11:18
 */
public class HasCycle {

    public boolean hasCycle(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return false;
        }

        //快慢指针进行判断,慢指针每次走一步，快指针每次两步
        ListNode slow = head;
        ListNode fast = head.next;

        //对于不循环链表，fast先到终点
        //对于循环链表，fast会在某个节点遇到slow
        while(Objects.nonNull(fast) && !Objects.equals(fast, slow)){
            ListNode fastNext = fast.next;
            if(Objects.nonNull(fastNext)){
                fast = fastNext.next;
            } else {
                fast = null;
            }
            slow = slow.next;
        }

        if (Objects.isNull(fast)) {
            return false;
        }
        //快慢指针相等
        return true;
    }

    public static void main(String[] args){
//        ListNode head = new ListNode(3);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(0);
//        head.next.next.next = new ListNode(-4);
//        head.next.next.next.next = head.next;

        ListNode head = new ListNode(3);
        ListNode next = new ListNode(2);
        ListNode nextNext = new ListNode(0);
        ListNode nextNextNext = new ListNode(-4);
        head.next = next;
        next.next = nextNext;
        nextNext.next = nextNextNext;
        nextNextNext.next = next;

        HasCycle hc = new HasCycle();
        System.out.println(hc.hasCycle(head));
    }

}
