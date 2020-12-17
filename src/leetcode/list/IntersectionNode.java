package leetcode.list;

import java.util.Objects;

/**
 * 160. 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 * 如下面的两个链表：
 *
 *
 *
 * 在节点 c1 开始相交。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 *
 * 示例 2：

 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *
 *
 * 示例 3：
 *
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 *
 *
 * 注意：
 *
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/10 16:43
 *
 * 思路：1.翻转两个链表，然后从后往前开始遍历找到共同的部分，直到不同  O(2n)
 *      2.链表l1 l2长度不同，考虑到 l1+l2 = l2+l1, 因此在遍历完短的链表的时候开始遍历长链表，而长链表遍历完则开始遍历短链表，找相同的部分 O(m+n)
 *         如果相交，肯定有相同的尾部；否则没有相交
 */
public class IntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(Objects.isNull(headA) || Objects.isNull(headB)){
            return null;
        }
        ListNode l1 = headA;
        ListNode l2 = headB;
        boolean startA2Round = false;
        boolean startB2Round = false;
        ListNode intersect = null;

        while(Objects.nonNull(l1) || Objects.nonNull(l2) || !startA2Round || !startB2Round){
            //较短的链表先到尾部
            if(Objects.isNull(l1)){
                if(!startA2Round){
                    l1=headB;
                    startA2Round=true;
                } else {
                    break;
                }
            }
            if(Objects.isNull(l2)){
                if(!startB2Round){
                    l2=headA;
                    startB2Round=true;
                } else {
                    break;
                }
            }
            if(startA2Round || startB2Round){
//                if (l1==l2) {
                if (Objects.equals(l1.val, l2.val)) {
                    if(Objects.isNull(intersect)){
                        intersect = l1;
                    }
                } else {
                    intersect = null;
                }    
            }
            
            l1 = l1.next;
            l2 = l2.next;
        }

        //如果相交两个节点尾部一定相同
        if(Objects.nonNull(l1) || Objects.nonNull(l2)){
            System.out.println("is null");
            return null;
        }
        return intersect;
    }

    public static void main(String[] args){
//        ListNode a = new ListNode(4);
//        ListNode b = new ListNode(1);
//        ListNode c = new ListNode(8);
//        ListNode d = new ListNode(4);
//        ListNode e = new ListNode(5);
//        a.next = b;
//        b.next = c;
//        c.next = d;
//        d.next = e;
//        ListNode a1 = new ListNode(5);
//        ListNode b1 = new ListNode(0);
//        ListNode c1 = new ListNode(1);
//        ListNode d1 = new ListNode(8);
//        ListNode e1 = new ListNode(4);
//        ListNode f1 = new ListNode(5);
//        a1.next = b1;
//        b1.next = c1;
//        c1.next = d1;
//        d1.next = e1;
//        e1.next = f1;

        //0,9,1,2,4
        ListNode a = new ListNode(2);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(4);
        ListNode d = new ListNode(5);
        ListNode e = new ListNode(4);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        ListNode a1 = new ListNode(2);
        ListNode b1 = new ListNode(2);
        ListNode c1 = new ListNode(4);
        ListNode d1 = new ListNode(5);
        ListNode e1 = new ListNode(4);
        a1.next = b1;
        b1.next = c1;
        c1.next = d1;
        d1.next = e1;


        IntersectionNode intersectionNode = new IntersectionNode();
        System.out.println(intersectionNode.getIntersectionNode(a, a1));
    }
}
