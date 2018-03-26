package queue.priority;

import comparion.Apple;
import comparion.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zcy on 18-3-26.
 * 放入PriorityQueue的元素 必须实现Comparable接口 或者 构造函数提供一个Comparator
 * 存储结构类似于一个堆
 * peek 返回堆顶元素 不删除
 * poll 返回堆顶元素 删除堆顶元素 并调整堆结构
 */
public class PriorityTest {

    public static void main(String[] args) {
        PriorityQueue<Apple> pq = new PriorityQueue(Comparator.comparingInt(Apple::getWeight));//必须传Comparator
        Apple a1 = new Apple("a1", 10, "red");
        Apple a2 = new Apple("a2", 2, "red2");
        Apple a3 = new Apple("a3", 30, "red3");
        Apple a4 = new Apple("a4", 12, "red3");
        Apple a5 = new Apple("a5", 11, "red3");
        Apple a6 = new Apple("a6", 32, "red3");
        Apple a7 = new Apple("a7", 50, "red3");
        Apple a8 = new Apple("a8", 13, "red3");
        Apple a9 = new Apple("a9", 11, "red3");

        pq.add(a1);
        System.out.println(pq);
        pq.add(a2);
        System.out.println(pq);
        pq.add(a3);
        System.out.println(pq);
        pq.add(a4);
        System.out.println(pq);
        pq.add(a5);
        System.out.println(pq);
        pq.add(a6);
        System.out.println(pq);
        pq.add(a7);
        System.out.println(pq);
        pq.add(a8);
        System.out.println(pq);
        pq.add(a9);
        System.out.println(pq);

        System.out.println("peek" + pq.peek());
        System.out.println("poll" + pq.poll());
        System.out.println("peek" + pq.peek());
        System.out.println("poll" + pq.poll());
        System.out.println("peek" + pq.peek());
        System.out.println("poll" + pq.poll());
        System.out.println("peek" + pq.peek());
        System.out.println("poll" + pq.poll());
        System.out.println("peek" + pq.peek());
        System.out.println("poll" + pq.poll());
        System.out.println("peek" + pq.peek());
        System.out.println("poll" + pq.poll());
        System.out.println("peek" + pq.peek());
        System.out.println("poll" + pq.poll());
        System.out.println("peek" + pq.peek());
        System.out.println("poll" + pq.poll());
        System.out.println("peek" + pq.peek());
        System.out.println("poll" + pq.poll());

//        System.out.println(pq.poll());
//        System.out.println(pq.poll());
//        System.out.println(pq.poll());
//        System.out.println(pq.poll());
//        System.out.println(pq.poll());
//        System.out.println(pq.poll());
//        System.out.println(pq.poll());
//        System.out.println(pq);
//        System.out.println(pq.contains(a1));
//        System.out.println(pq.peek());
//        System.out.println(pq.poll());
//
//
//        PriorityQueue<Employee> pq2 = new PriorityQueue<>();
//        Employee e1 = new Employee("e1", 1, "china");
//        Employee e2 = new Employee("e2", 3, "china");
//        Employee e3 = new Employee("e3", 5, "china");
//
//        pq2.add(e1);
//        pq2.add(e2);
//        pq2.add(e3);

    }
}
