package util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chaoyi.zhang
 * @Date: 2020/10/28 11:25
 */
public class StringUtils2 {

    /**
     * 找出一个字符串中的除去word的剩余字符串
     * @param s
     * @param word
     * @return
     */
    public static List<String> matchWord(String s, String word){
        List<String> list = new ArrayList<>();
        int lastIndex = 0;
        while(s.indexOf(word, lastIndex) != -1){
            int index = s.indexOf(word, lastIndex);
            list.add(s.substring(0, index) + s.substring(index+word.length()));
            lastIndex = index + word.length();
        };
        return list;
    }
}
