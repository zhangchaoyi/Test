package daily;

import java.lang.reflect.Array;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: 测试返回期望的类型
 * @author: zhangchaoyi
 * @date: 2019/10/9
 */
public class ReturnTypeTest {

    public static final Map<String, Object> map = new LinkedHashMap<String, Object>(){
        {
            put("1", "1");put("2", 2);put("3", new Integer(3));put("4", new Long(4));
        }
    };

    public static void main(String[] args){
        String[] s1 = getRequiredAttribute("1", String[].class);

        Integer[] s2 = getRequiredAttribute("2", Integer[].class);

        String s3 = getRequiredAttribute("1", String.class);

        Integer s4 = getRequiredAttribute("2", Integer.class);

    }

    /**
     * 返回对应的类型或者数组类型
     * @param attributeName
     * @param expectedType
     * @param <T>
     * @return
     */
    public static <T> T getRequiredAttribute(String attributeName, Class<T> expectedType){
        Object value = map.get(attributeName);
        assertAttributePresence(attributeName, value);
        assertNotException(attributeName, value);

        if (!expectedType.isInstance(value) && expectedType.isArray() &&
                expectedType.getComponentType().isInstance(value)) {
            Object array = Array.newInstance(expectedType.getComponentType(), 1);
            Array.set(array, 0, value);
            value = array;
        }
        assertAttributeType(attributeName, value, expectedType);

        return (T) value;
    }

    private static void assertAttributePresence(String attributeName, Object attributeValue) {
        if (attributeValue == null) {
            throw new IllegalArgumentException(String.format(
                    "Attribute '%s' not found in attributes for annotation [%s]", attributeName, ""));
        }
    }

    private static void assertNotException(String attributeName, Object attributeValue) {
        if (attributeValue instanceof Exception) {
            throw new IllegalArgumentException(String.format(
                    "Attribute '%s' for annotation [%s] was not resolvable due to exception [%s]",
                    attributeName, "", attributeValue), (Exception) attributeValue);
        }
    }

    private static void assertAttributeType(String attributeName, Object attributeValue, Class<?> expectedType) {
        if (!expectedType.isInstance(attributeValue)) {
            throw new IllegalArgumentException(String.format(
                    "Attribute '%s' is of type [%s], but [%s] was expected in attributes for annotation [%s]",
                    attributeName, attributeValue.getClass().getSimpleName(), expectedType.getSimpleName(),
                    ""));
        }
    }
}
