package leetcode.list;

import leetcode.list.ListNode;

import java.util.Objects;

/**
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/10 16:48
 *
 * 思路： 1.借助O(n)数组
 *       2.找到链表中点，同时对一半做链表翻转
 *
 *       1->2->3->4->5    slow=3
 *       1->2->3->4->5->6  slow=3
 */
public class IsListPalindrome {

    public boolean isPalindrome(ListNode head) {
        if(Objects.isNull(head) || Objects.isNull(head.next)){
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        boolean isEven = false;

        while (Objects.nonNull(fast) && Objects.nonNull(fast.next)){
            fast = fast.next.next;
            //slow指针同时完成翻转
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;

            if(Objects.isNull(fast)){
                isEven = true;
                System.out.println("is even");
                break;
            }
        }
        System.out.println(slow);
        System.out.println(pre);

        if(!isEven){
            slow = slow.next;
        }
        //开始遍历slow和pre两个链表是否一致
        while(Objects.nonNull(pre)){
            if (Objects.isNull(slow)) {
                return false;
            }
            if(!Objects.equals(slow.val, pre.val)){
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }
        if (Objects.nonNull(slow)) {
            return false;
        }

        return true;
    }

    public static void main(String[] args){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(1);
//        ListNode c = new ListNode(3);
//        ListNode d = new ListNode(2);
//        ListNode e = new ListNode(1);
//        ListNode f = new ListNode(6);
        a.next = b;
//        b.next = c;
//        c.next = d;
//        d.next = e;
//        e.next = f;
        IsListPalindrome isListPalindrome = new IsListPalindrome();
        System.out.println(isListPalindrome.isPalindrome(a));
    }
}
