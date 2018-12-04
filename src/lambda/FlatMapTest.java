package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: flatMap, 合并流作处理
 * 对于一张单词表，如何返回列表，列出各不相同的字母，比如给定["hello", "world"], 返回['h', 'e', 'l', 'o', 'w', 'r', ''d]
 * @author: zhangchaoyi
 * @date: 2018/12/4
 */
public class FlatMapTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world");
        List<String> l = list.stream().map(word -> word.split("")).flatMap(strs -> Arrays.stream(strs)).distinct().collect(Collectors.toList());
        System.out.println(l);
    }
}
