package leetcode.array.sort;

import java.util.Arrays;

/**
 * 堆排序，堆是一个完全二叉树，
 * 数组的索引关系 当前节点为n，左子节点=2n+1， 右子节点=2n+2， 其父节点为(n-1)/2
 *
 * 完成堆的结构初始化：从最后一个节点的父节点开始往前遍历，保证遍历的当前节点为根的子树是一个最大堆，直到树的根节点
 *
 * 排序过程：每次将堆根节点和最后一个元素互换，重新调整堆
 *
 * 要使最终的元素升序：使用最大堆，这样每次换到最后的元素都是最大的
 * 要使最终的元素降序：使用最小堆，这样每次换到最后的元素都是最小的
 *
 * 时间复杂度 O(n logn) , 一次调整树时间 logn
 * 非稳定排序，因为在交换堆顶和最后一位时，最后一位元素到首位的过程可能出现交换相同元素的相对位置的情况
 *
 *
 * @Author: chaoyi.zhang
 * @Date: 2020/12/14 10:06
 */
public class HeapSort {

    public void sort(int[] nums){
        //build tree
        build(nums);
        //进行取出堆顶元素排序
        for(int i=nums.length-1;i>=0;i--){
            swap(nums, 0, i);

            adjustTree(nums, 0, i);
        }
    }

    public void build(int[] nums){
        int lastParent = (nums.length-1)/2;
        for(int i=lastParent;i>=0;i--){
            adjustTree(nums, i, nums.length);
        }
    }

    /**
     * 对堆进行插入一个元素，实际是对最后一个元素不断做上浮操作调整树
     * O(logn)
     */
    private int[] insertNode(int[] heap, int newElement){
        int[] newHeap = Arrays.copyOf(heap, heap.length+1);
        int curIndex = newHeap.length-1;
        newHeap[curIndex] = newElement;

        while(curIndex>0){
            int parent = (curIndex-1)/2;
            if(newHeap[parent] < newElement){
                swap(newHeap, parent, curIndex);
                curIndex = parent;
            } else {
                return newHeap;
            }
        }

        return newHeap;
    }

    /**
     * 一次调整curParent为根的子树，时间复杂度 O(logk)
     *
     *  下沉调整堆, 用于堆初始化和删除节点
     *
     * 在初始化堆时 size为nums.length
     *
     * 在取堆首元素排序时， 因为堆顶元素和最后一位换位，在重新调整树时只需要调整剩余的元素
     *
     * @param nums
     * @param curParent
     * @param size
     */
    public void adjustTree(int[] nums, int curParent, int size){
        int leftChild = curParent*2+1;

        if(leftChild<size) {
            //找出左右子树中最大的节点，与parent节点比较
            int maxSubPosition = leftChild;
            int subMax = nums[leftChild];

            int rightChild = leftChild+1;
            if(rightChild<size){
                if(nums[rightChild]>nums[leftChild]){
                    maxSubPosition = rightChild;
                    subMax = nums[rightChild];
                }
            }

            if(nums[curParent] < subMax) {
                swap(nums, curParent, maxSubPosition);
                //需要进一步调整子树
                adjustTree(nums, maxSubPosition, size);
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

    public static void main(String[] args){
        int[] nums = new int[]{1,2,3,4,5,6};

        HeapSort hs = new HeapSort();
        hs.build(nums);
        int[] newHeap = hs.insertNode(nums, 7);

        System.out.println(Arrays.toString(newHeap));
    }
}
