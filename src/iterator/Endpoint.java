package iterator;

/**
 * @description:
 * @author: zhangchaoyi
 * @date: 2019/7/18
 */
public final class Endpoint {

    private final String value;

    public Endpoint(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override public String toString() {
        return "Endpoint{" + "value='" + value + '\'' + '}';
    }
}
