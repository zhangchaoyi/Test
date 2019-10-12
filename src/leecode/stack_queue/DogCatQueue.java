package leecode.stack_queue;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *  猫狗队列，要求可以将 cat 或者 dog的实例放入
 *      可以调用pollALL()将所有实例按先进先出方式弹出
 *      可以调用pollDog()将所有dog实例按先进先出方式弹出
 *      可以调用pollCat()将所有cat实例按先进先出方式弹出
 *      可以调用isEmpty()判断是否还有cat或者dog的实例
 *      可以调用isCatEmpty()判断是否还有cat的实例
 *      可以调用isDogEmpty()判断是否还有dog的实例
 */
public class DogCatQueue {

    private Queue<Pet> dogQueue = new LinkedBlockingQueue<>();

    private Queue<Pet> catQueue = new LinkedBlockingQueue<>();

    private volatile int count = 0;

    public synchronized void add(Pet p){
        p.setCount(count++);
        if(p instanceof Dog){
            dogQueue.add(p);
        } else {
            catQueue.add(p);
        }
    }

    public Pet pollAll(){
        Pet dog = dogQueue.peek();
        Pet cat = catQueue.peek();

        if(dog.getCount() < cat.getCount()){
            return dogQueue.poll();
        } else {
            return catQueue.poll();
        }

    }

    public Pet pollDog(){
        return dogQueue.poll();
    }

    public Pet pollCat(){
        return catQueue.poll();
    }

    public boolean isEmpty(){
        return dogQueue.isEmpty() && catQueue.isEmpty();
    }

    public boolean isCatEmpty(){
        return catQueue.isEmpty();
    }

    public boolean isDogEmpty(){
        return dogQueue.isEmpty();
    }

    public static void main(String[] args){
        DogCatQueue dcQ = new DogCatQueue();

        Pet p1 = new Cat();
        Pet p2 = new Cat();
        Pet p3 = new Dog();
        Pet p4 = new Cat();
        Pet p5 = new Dog();
        Pet p6 = new Cat();
        Pet p7 = new Dog();
        Pet p8 = new Dog();
        Pet p9 = new Cat();
        Pet p10 = new Dog();

        dcQ.add(p1);
        dcQ.add(p2);
        dcQ.add(p3);

        dcQ.pollCat();

        dcQ.add(p4);

        dcQ.isEmpty();
        dcQ.add(p5);

        dcQ.pollAll();

        dcQ.add(p6);
        dcQ.add(p7);
        dcQ.add(p8);

        dcQ.pollCat();

        dcQ.add(p9);
        dcQ.add(p10);

        dcQ.pollAll();


    }

}

class Pet {

    private String type;

    private int count;

    public Pet(String type, int count){
        this.type = type; this.count = count;
    }

    public Pet(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public int getCount(){return this.count;}

    public void setType(String type){
        this.type = type;
    }

    public void setCount(int count){
        this.count = count;
    }
}

class Dog extends Pet {

    public Dog(int count){
        super("dog", count);
    }

    public Dog(){
        super("dog");
    }

    @Override
    public void setCount(int count){
        super.setCount(count);
    }
}

class Cat extends Pet {

    public Cat(int count){
        super("cat", count);
    }

    public Cat(){
        super("cat");
    }

    @Override
    public void setCount(int count){
        super.setCount(count);
    }

}
