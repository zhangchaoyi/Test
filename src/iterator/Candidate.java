package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @description: 不可变类型使用Iterable接口
 * @author: zhangchaoyi
 * @date: 2019/7/18
 */
public final class Candidate implements Iterable<Endpoint> {

    private final Set<Endpoint> endpoints;

    public Set<Endpoint> getEndpoints() {
        return endpoints;
    }

    public Candidate(Set<Endpoint> endpoints){
        this.endpoints = endpoints;
    }

    @Override public Iterator<Endpoint> iterator() {

        //只读Iterator，没有remove方法
        class iter  implements Iterator<Endpoint>{     //方法内部类
            private int cur= 0;
            private List<Endpoint> list = new ArrayList<>(getEndpoints());

            @Override
            public boolean hasNext()
            {
                return cur != getEndpoints().size();
            }

            @Override
            public Endpoint next()
            {

                Endpoint c = list.get(cur);
                cur++;
                return c;
            }

        }

        return new iter();
    }



}
