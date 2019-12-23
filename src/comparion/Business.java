package comparion;

public enum Business {

    BANK(1, "bank"), IT(2, "it"), EDU(3, "edu"), HOSPITAL(4, "hosp"), SPORT(5, "sport");

    private Business(int code, String desc){
        this.code = code;
        this.desc = desc;
    }
    private int code;
    private String desc;
}
