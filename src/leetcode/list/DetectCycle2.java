package leetcode.list;

import java.util.Objects;

/**
 * 环形链表找出环的长度,如果没有环返回0
 *
 * 进阶：
 *    如何知道环的长度？
 *
 *     思路：快慢指针相遇后，继续走，记录长度len，等待下一次相遇即为len
 *
 * @Author: chaoyi.zhang
 * @Date: 2021/2/4 10:42
 */
public class DetectCycle2 {

    public static void main(String[] args){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(0);
        ListNode d = new ListNode(-4);
        ListNode e = new ListNode(5);
        ListNode f = new ListNode(6);
        ListNode g = new ListNode(8);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = b;

        DetectCycle2 dc = new DetectCycle2();
        System.out.println(dc.getCycleLen(a));
    }

    public int getCycleLen(ListNode head){
        ListNode slow = head;
        ListNode fast = head;

        while(!Objects.equals(slow, fast) || Objects.equals(fast, head)){
            if(Objects.isNull(fast)) {
                return 0;
            }
            ListNode fastNext = fast.next;
            if(Objects.nonNull(fastNext)) {
                fast = fastNext.next;
            } else {
                //没有环
                return 0;
            }

            slow = slow.next;
        }
        System.out.println(slow.val);
        //相遇后快慢指针继续遍历，直到相遇
        int len=0;

        while(!Objects.equals(slow, fast) || len==0){

            fast = fast.next.next;
            slow = slow.next;
            len++;
        }

        return len;
    }
}
