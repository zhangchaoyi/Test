package util;

import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

public class EmojiUtils {
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static String fen2yuan(Long fen) {
        //string 转 BigDecimal 分转元
        BigDecimal feeNo = new BigDecimal(fen);
        return feeNo.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP).toString();
    }

    /**
     * 將邮箱处理成掩码
     * 如果邮箱只有一位，a@za.group, 显示 a@******.group
     * 其余情况邮箱显示前两位+6位*，如 test@za.group, 显示 te******@za.group
     * @return
     */
    public static String getMaskEmail(String email){

        final String mask = "******";
        String[] splits = StringUtils.split(email, "@");

        String prefix = splits[0];
        String suffix = splits[1];

        if(prefix.length() == 1){
            String[] suffixSplits = StringUtils.split(suffix, ".");
            suffix = mask + "." + suffixSplits[1];
        } else {
            prefix = StringUtils.substring(splits[0], 0, 2) + mask;
        }

        return prefix + "@" + suffix;
    }

    public static boolean containsEmoji(String str){
        if(StringUtils.isNotEmpty(str) && CollectionUtils.isNotEmpty(EmojiParser.extractEmojis(str))){
            return true;
        }
        return false;
    }

    public static boolean anyContainsEmoji(final String... str) {
        for (final String s : str){
            if (containsEmoji(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean noneContainsEmoji(final String... str){
        return !anyContainsEmoji(str);
    }

    public static void main(String[] args){
//        String s = "a@za.group";
//
//        System.out.println(getMaskEmail(s));
//
//        String str = "te@za.group";
//        System.out.println(getMaskEmail(str));
//
//        String str1 = "tessdfsd@za.com";
//        System.out.println(getMaskEmail(str1));
        String s = "Here is a boy \uD83D\uDE10 ";
        System.out.println(containsEmoji(s));
//        System.out.println(String.format("%.2f", 3.14235));;
    }


}
