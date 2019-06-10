package comparion;

import java.util.*;

/**
 * @description: 自定义排序
 * @author: zhangchaoyi
 * @date: 2019/6/10
 */
public class SelfDefinedTest {

    public static void main(String[] args){
        String[] order = {"语文","数学","英语","物理","化学","生物","政治","历史","地理","总分"};
        final List<String> definedOrder = Arrays.asList(order);
        List<String> list = new ArrayList<String>(){
            {
                add("总分");
                add("英语");
                add("政治");
                add("总分");
                add("数学");
            }
        };
        Collections.sort(list,new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                int io1 = definedOrder .indexOf(o1);
                int io2 = definedOrder .indexOf(o2);
                return io1-io2;
            }
        });

        for(String s:list){
            System.out.print(s+"   ");
        }
    }
}
