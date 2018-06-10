package leecode.string;

/**
 * Created by chris on 5/13/18.
 */
public class reverseTest {

    public static void reverse(String s){
        if(s==null || s.isEmpty()){
            return;
        }
        int left = 0;
        int right = s.length() - 1;
        char[] arrays = s.toCharArray();
        while(left < right){
            char tmp = arrays[left];
            arrays[left] = arrays[right];
            arrays[right] = tmp;

            left ++ ;
            right --;
        }

        s = new String(arrays);

        System.out.println(s);
    }

    public static void main(String[] args){
        reverse("abcdefghijklmn");
    }
}
