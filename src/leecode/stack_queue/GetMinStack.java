package leecode.stack_queue;

/**
 * 设计一个有getMin的栈，返回栈中最小的元素
 * 要求pop/push/getMin的时间复杂度都是O(1)
 *
 * 设计两个栈，正常栈stackData保存当前元素；另外一个栈stackMin用于保存每一步的最小值
 *
 * 思路1：在压入stackData时同时维护stackMin，且如果当前数据小于stackMin顶的数据，则压入当前数据
 *       出栈时如果stackData顶的数据如果等于stackMin顶的数据则同时删除stackMin顶数据
 *
 * 思路2：在压入stackData时如果当前数据大于stackMin顶的数据，再压入一次stackMin顶的数据
 *       出栈时同时出两个栈的数据
 *
 */
public class GetMinStack {



}
