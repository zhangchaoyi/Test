package leecode.stack_queue;

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
}

class Pet {

    private String type;

    public Pet(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}

class Dog extends Pet {

    public Dog(){
        super("dog");
    }

}

class Cat extends Pet {

    public Cat(){
        super("cat");
    }

}