package gof.builder;

/**
 * Created by chris on 10/15/17.
 * builder模式，适合属性很多的类用于 多个构造方法的场景，任意个参数初始化对象
 * -------------------------------------------
 * 不使用builder  1.需要写死多个不同参数的构造方法，需要扩展代码，不灵活
 *              2.通过一个无参数初始化，接着多个set属性则可能导致过程中不一致的情况
 */
public class SelfPojo {
    private final Integer a;
    private final String b;
    private Integer c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;

    public SelfPojo(SelfPojoBuilder spb){
        this.a = spb.a;
        this.b = spb.b;
        this.c = spb.c;
        this.d = spb.d;
        this.e = spb.e;
        this.f = spb.f;
        this.g = spb.g;
        this.h = spb.h;
        this.i = spb.i;
        this.j = spb.j;
    }

    public static SelfPojoBuilder Builder(Integer a, String b){
        return new SelfPojoBuilder(a, b);
    }

    public static class SelfPojoBuilder {
        private final Integer a;
        private final String b;
        private Integer c;
        private String d;
        private String e;
        private String f;
        private String g;
        private String h;
        private String i;
        private String j;

        public SelfPojoBuilder(Integer a, String b){
            this.a = a;
            this.b = b;
        }

        public SelfPojoBuilder setC(Integer c){
            this.c = c;
            return this;
        }

        public SelfPojoBuilder setD(String d){
            this.d = d;
            return this;
        }

        public SelfPojoBuilder setE(String e) {
            this.e = e;
            return this;
        }

        public SelfPojoBuilder setF(String f) {
            this.f = f;
            return this;
        }

        public SelfPojoBuilder setG(String g) {
            this.g = g;
            return this;
        }

        public SelfPojoBuilder setH(String h) {
            this.h = h;
            return this;
        }

        public SelfPojoBuilder setI(String i) {
            this.i = i;
            return this;
        }

        public SelfPojoBuilder setJ(String j) {
            this.j = j;
            return this;
        }

        public SelfPojo build(){
            return new SelfPojo(this);
        }
    }

    @Override
    public String toString() {
        return "SelfPojo{" +
                "a=" + a +
                ", b='" + b + '\'' +
                ", c=" + c +
                ", d='" + d + '\'' +
                ", e='" + e + '\'' +
                ", f='" + f + '\'' +
                ", g='" + g + '\'' +
                ", h='" + h + '\'' +
                ", i='" + i + '\'' +
                ", j='" + j + '\'' +
                '}';
    }

    public static void main(String[] args){
//        SelfPojo spb = new SelfPojo.SelfPojoBuilder(1, "a").setC(2).build();
        SelfPojo spb = SelfPojo.Builder(1, "b").setD("d").build();
        System.out.println(spb);
    }
}
