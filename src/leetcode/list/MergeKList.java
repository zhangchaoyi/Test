package leetcode.list;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by zcy on 18-5-16.
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 输入:
 [
 1->4->5,
 1->3->4,
 2->6
 ]
 输出: 1->1->2->3->4->4->5->6

 思路：1、合并到一个链表，换成数组排序，再换回链表 时间复杂度 O(knlogkn)

      2、使用size=k的最小堆，初始化放入k个链表头，每次取出堆顶最小的节点，并把该节点的下一个链表节点放入堆
        时间复杂度O(knlogk) 一次插入的时间复杂度O(logk)

 ----------------------------------------------------------------------------------
 */
public class MergeKList {

    public static ListNode mergeKLists(ListNode[] lists){
        if(lists == null || lists.length==0){
            return null;
        }
        if(lists.length==1){
            return lists[0];
        }
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, Comparator.comparingInt((ListNode l) -> l.val));

        for(int i=0;i<lists.length;i++){
            if(Objects.nonNull(lists[i])){
                minHeap.add(lists[i]);
            }
        }
        ListNode dummy = new ListNode(Integer.MAX_VALUE);
        ListNode tail = dummy;

        while(!minHeap.isEmpty()){
            ListNode top = minHeap.poll();
            tail.next = top;
            tail = tail.next;

            if(Objects.nonNull(top.next)){
                minHeap.add(top.next);
            }
        }

        return dummy.next;
    }


    public static void main(String[] args){
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(6);
        ListNode l4 = new ListNode(8);
        ListNode l5 = new ListNode(10);
        ListNode l6 = new ListNode(12);
        ListNode l7 = new ListNode(14);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;

        ListNode l8 = new ListNode(3);
        ListNode l9 = new ListNode(3);
        ListNode l10 = new ListNode(5);
        ListNode l11 = new ListNode(7);
        ListNode l12 = new ListNode(9);
        ListNode l13 = new ListNode(11);
        ListNode l14 = new ListNode(13);

        l8.next = l9;
        l9.next = l10;
        l10.next = l11;
        l11.next = l12;
        l12.next = l13;
        l13.next = l14;

        ListNode l15 = new ListNode(1);
        ListNode l16 = new ListNode(4);
        ListNode l17 = new ListNode(6);
        ListNode l18 = new ListNode(12);
        ListNode l19 = new ListNode(15);
        ListNode l20 = new ListNode(19);
        ListNode l21 = new ListNode(20);

        l15.next = l16;
        l16.next = l17;
        l17.next = l18;
        l18.next = l19;
        l19.next = l20;
        l20.next = l21;

        System.out.println(l1);
        System.out.println(l8);
        System.out.println(l15);


        ListNode[] lists = new ListNode[]{};
//        lists[0] = l1;
//        lists[1] = l8;
//        lists[2] = l15;
//        [[]]
        System.out.println(mergeKLists(lists));

        ForkJoinPool.commonPool();
        new ForkJoinPool();
    }
}
