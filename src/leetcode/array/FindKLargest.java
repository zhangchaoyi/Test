package leetcode.array;

import java.util.*;

/**
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 *
 * 暴力方式： 1.先排序，在一次遍历找到k  O(nlogn + k)
 *          2.借助堆，空间换时间，构造一个O(k)的最小堆，一次遍历将数组逐一放入堆，返回堆中最小元素
 *  * 维护一个k最小堆，最终堆顶最小值恰好为所求的最小值；遍历过程中的nums[i]与堆顶元素对比是否需要放入堆中，并进行维护堆结构
 *  * 堆使用PriorityQueue或者单调栈 时间复杂度O(nlogk(n个元素，一次调整堆的时间logk)，空间复杂度O(n)
 *          3.基于快排，因为一次快排partion能确定一个挡板元素q的位置，此时只需判断 q 和 length-k(快排基于升序) 是否相等，如果相等返回；
 *                                                                                                          如果q<k,右区间进一步快排；
 *                                                                                                          如果q>k,左区间进一步快排；
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/10 16:47
 *
 */
public class FindKLargest {

    public int findKthLargestWithHeap(int[] nums, int k) {
        //构造前k位的堆
        int[] heap = Arrays.copyOfRange(nums, 0, k);
        int lastParent = (heap.length-1)/2;
        for(int i=lastParent;i>=0;i--){
            adjustTree(heap, i, heap.length);
        }
        //从k开始逐一放入堆进行替换
        for(int i=k;i<nums.length;i++){
            int top = heap[0];
            if (nums[i] > top) {
                heap[0] = nums[i];
                adjustTree(heap, 0, heap.length);
            }
        }

        return heap[0];
    }

    /**
     * 调整最小堆
     * @param nums
     * @param curParent
     * @param size
     */
    public void adjustTree(int[] nums, int curParent, int size){
        int leftChild = curParent*2+1;

        if(leftChild<size) {
            //找出左右子树中最小的节点，与parent节点比较
            int minSubPosition = leftChild;
            int subMin = nums[leftChild];

            int rightChild = leftChild+1;
            if(rightChild<size){
                if(nums[rightChild]<nums[leftChild]){
                    minSubPosition = rightChild;
                    subMin = nums[rightChild];
                }
            }

            if(nums[curParent] > subMin) {
                swap(nums, curParent, minSubPosition);
                //需要进一步调整子树
                adjustTree(nums, minSubPosition, size);
            } else {
                //无需调整子树
                return;
            }
        }
    }

    private void swap(int[] nums, int p, int q){
        int temp = nums[p];
        nums[p] = nums[q];
        nums[q] = temp;
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparing(Integer::intValue));//堆顶元素是最小值
        for(int i=0;i<nums.length;i++){
            if(pq.size() < k){
                pq.add(nums[i]);
            } else {
                int head = pq.peek();
                if(nums[i] > head){
                    pq.poll();
                    pq.add(nums[i]);
                }
            }
        }
        return pq.peek();
    }

    public int findKthLargestWithStack(int[] nums, int k) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<nums.length;i++){
            if(stack.isEmpty()){
                stack.add(nums[i]);
            } else {
                if(stack.size()<k){
                    pushStack(stack, nums[i]);
                } else {
                    Integer head = stack.peek();
                    if(head.intValue() < nums[i]){
                        //进行替换
                        stack.pop();
                        pushStack(stack, nums[i]);
                    }
                }
            }
        }
        return stack.pop();
    }

    /**
     * 单调栈，将num放入stack中, 需要继续保持单调
     * @param stack
     * @param num
     */
    private void pushStack(Stack<Integer> stack, int num){
        if(stack.isEmpty()){
            stack.add(num);
            return;
        }
        int head = stack.peek();

        if(head < num){
            stack.pop();
            pushStack(stack, num);
            stack.add(head);
        } else {
            stack.add(num);
        }
    }
    //=======================基于快排======================================================================================================
    Random random = new Random();

    public int findKthLargestWithQuickSort(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    public int quickSelect(int[] a, int l, int r, int index) {
        int q = randomPartition(a, l, r);
        if (q == index) {
            return a[q];
        } else {
            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
        }
    }

    public int randomPartition(int[] a, int l, int r) {
        int i = random.nextInt(r - l + 1) + l;
        swap(a, i, r);
        return partition(a, l, r);
    }

    public int partition(int[] a, int l, int r) {
        int x = a[r], i = l - 1;
        for (int j = l; j < r; ++j) {
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }


    public static void main(String[] args){
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        FindKLargest fk = new FindKLargest();
        System.out.println(fk.findKthLargestWithHeap(nums, 4));
    }
}
