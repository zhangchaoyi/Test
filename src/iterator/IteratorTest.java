package iterator;

import java.util.HashSet;
import java.util.Set;

/**
 * @description: 不可变类型测试
 * @author: zhangchaoyi
 * @date: 2019/7/18
 */
public class IteratorTest {

    public static void main(String[] args){
        Endpoint e1 = new Endpoint("e1");
        Endpoint e2 = new Endpoint("e2");
        Endpoint e3 = new Endpoint("e3");
        Set<Endpoint> endpoints = new HashSet<Endpoint>(){
            {add(e1);add(e2);add(e3);}
        };

        Candidate c = new Candidate(endpoints);

        for(Endpoint e : c){
            System.out.println(e);
        }
    }

}
