package leetcode.list;

/**
 * Created by zcy on 18-5-16.
 */
public class ListNode {
    int val;
    ListNode next;

    public ListNode(int x){
        val = x;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode tmp = this;
        while(tmp != null){
            sb.append(tmp.val + " ");
            tmp = tmp.next;
        }

        return sb.toString();
    }
}
