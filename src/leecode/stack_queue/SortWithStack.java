package leecode.stack_queue;

import java.util.Stack;

/**
 * @description: 使用另外一个栈进行排序当前栈，要求从顶到底从大到小排序，只能申请一个栈和变量，不能使用额外数据结构
 * 数据栈 stackData  辅助栈stackHelp
 * 分析：由于要求stackData从顶到底从大到小排序，因此stackHelp中从顶到底从小到大排序，只要将stackHelp中逐一弹出到stackData即可
 * 1.stackData中数据是无序的，将stackData的元素逐个压入StackHelp中，如果stackData弹出的元素 num 比stackHelp顶的元素小，直接压如stackHelp；
 * 如果num比stackHelp顶的元素大，则将stackHelp的元素逐个压如stackData，直到num比stackHelp顶的元素小，就压入num，之后再重复弹出stackData的元素
 *
 * @author: zhangchaoyi
 * @date: 2019/10/12
 */
public class SortWithStack {

    private Stack<Number> stackData;

    public SortWithStack(Stack<Number> stackData){this.stackData = stackData;}

    public void sort(){
        Stack<Number> stackHelp = new Stack<>();

        while(!stackData.isEmpty()){
            Number num = stackData.pop();

            if(stackHelp.isEmpty()){
                stackHelp.add(num);
            } else {
                Number helpHead = stackHelp.peek();
                if(num.intValue() <= helpHead.intValue()){
                    stackHelp.add(num);
                } else {
                    //进行弹出stackHelp的元素并适当时机进行插入num
                    while(!stackHelp.isEmpty() && stackHelp.peek().intValue() < num.intValue()){
                        helpHead = stackHelp.pop();
                        stackData.push(helpHead);
                    }
                    stackHelp.push(num);
                }
            }
        }

        while(!stackHelp.isEmpty()){
            stackData.push(stackHelp.pop());
        }
    }

    public Stack<Number> getStackData(){
        return this.stackData;
    }

    public static void main(String[] args){
        Stack<Number> stackData = new Stack<Number>(){
            {
                add(2);add(1);add(3);add(5);add(9);add(7);add(8);
            }
        };
        SortWithStack sws = new SortWithStack(stackData);

        sws.sort();

        System.out.println(sws.getStackData().toString());
    }
}
