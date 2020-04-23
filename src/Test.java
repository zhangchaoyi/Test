import org.apache.commons.lang3.StringUtils;

public class Test {

    public static void main(String[] args){
//        for(int i=0;i<10;i++){
//            for(int j=i;j<10;j++){
//                System.out.print("("+i+","+j+")"+" ");
//            }
//            System.out.println();
//        }
        Test t = new Test();
        System.out.println(t.lengthOfLastWord("        "));
    }

    public int lengthOfLastWord(String s) {
        if(s == null){
            return 0;
        }
        s = s.trim();
        if(s.equals("") || s.equals(" ")){
            return 0;
        }
        String[] strs = s.split(" ");
        return strs[strs.length-1].length();
    }
}