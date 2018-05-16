package leecode.list;

import java.util.Stack;

/**
 * Created by zcy on 18-5-16.
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。

 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 输出：7 -> 0 -> 8
 原因：342 + 465 = 807
 个位 -> 十位 -> 百位 -> 千位


 1. 链表对应结点相加时增加前一个结点的进位，并保存下一个结点的进位；除法得进位，模得结果。
 2. 两个链表长度不一致时，要处理较长链表剩余的高位和进位计算的值；
 3. 如果最高位计算时还产生进位，则还需要添加一个额外结点。
 */
public class AddTwoNumbers {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2){
        ListNode tmp = null;
        ListNode result = null;

        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = sum / 10;

            ListNode node = new ListNode(sum % 10);
            if (tmp == null) {
                tmp = node;
                result = tmp;
            } else {
                tmp.next = node;
                tmp = tmp.next;
            }

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        return result;
    }

    public static void main(String[] arsg){
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode node = addTwoNumbers(l1, l2);
        while(node != null){
            System.out.print(node.val + " ");
            node = node.next;
        }

    }
}
