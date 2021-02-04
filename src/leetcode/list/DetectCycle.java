package leetcode.list;

import java.util.Objects;

/**
 * 142. 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 *
 * 说明：不允许修改给定的链表。
 *
 * 进阶：
 *
 * 你是否可以使用 O(1) 空间解决此题？
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 *
 *
 * 提示：
 *
 * 链表中节点的数目范围在范围 [0, 104] 内
 * -105 <= Node.val <= 105
 * pos 的值为 -1 或者链表中的一个有效索引
 *
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/10 16:39
 *
 * 思路：
 * 1.HashMap记录节点，比较引用即可   时间复杂度 O(n + kn + n)  , 空间复杂度O(n)
 *
 * 2.快慢指针  数学推算  1->2->3->4->5->6->7
 *                                 \   /
 *                                   8
 *     快指针走两步，慢指针走一步
 *     假设头节点到第一个入环点的距离为a，  第一个入环点到快慢指针相遇点的距离为b， 相遇点到入环点的距离为c
 *     则慢指针走过的距离=a+b
 *     快指针走过=a+b+n(b+c), n>1
 *     由快指针是慢 指针两倍可得 a+b+n(b+c) = 2(a+b)  ===> a=nc+nb-b ===> a=n(b+c)-b ===> a=(n-1)(b+c)+c
 *     说明在快慢指针相遇时，一个新指针从head出发, 慢指针继续走， 等这个新指针和慢指针相遇时恰好是入环点
 *
 */
public class DetectCycle {

    public ListNode detectCycle(ListNode head) {
        if (Objects.isNull(head)) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;

        do{
            fast = fast.next;
            if(Objects.nonNull(fast)){
                fast = fast.next;
            } else {
                break;
            }
            slow = slow.next;
        }while(Objects.nonNull(fast) && slow != fast);

        if(Objects.isNull(fast)){
            return null;
        }
        //新指针从头开始，直到与slow相遇
        ListNode pr = head;
        while(pr != slow){
            pr = pr.next;
            slow = slow.next;
        }

        return pr;
    }

    public static void main(String[] args){
        ListNode a = new ListNode(1);
//        ListNode b = new ListNode(2);
//        ListNode c = new ListNode(0);
//        ListNode d = new ListNode(-4);
//        ListNode e = new ListNode(5);
//        ListNode f = new ListNode(6);
//        a.next = b;
//        b.next = a;
//        c.next = d;
//        d.next = b;
//        d.next = e;
//        e.next = f;

        DetectCycle dc = new DetectCycle();
        System.out.println(dc.detectCycle(a).val);
    }

}
