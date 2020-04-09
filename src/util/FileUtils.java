package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import enums.FileType;
import org.apache.commons.collections4.CollectionUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

public class FileUtils {

    private static final Logger log = Logger.getAnonymousLogger();

    /** 判断文件类型  */
    public static FileType getType(String filepath) {
        // 获取文件头
        String fileHead = getFileHeader(filepath);

        if (fileHead != null && fileHead.length() > 0) {
            fileHead = fileHead.toUpperCase();
            FileType[] fileTypes = FileType.values();

            for (FileType type : fileTypes) {
                if (fileHead.startsWith(type.getValue())) {
                    return type;
                }
            }
        }

        return null;
    }

    /** 读取文件头 */
    private static String getFileHeader(String filepath) {
        byte[] b = new byte[28];
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(filepath);
            inputStream.read(b, 0, 28);
        } catch(IOException e){
            log.info("IOException should not happen");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch(IOException e){
                    log.info("IOException should not happen");
                }

            }
        }

        return bytesToHex(b);
    }

    /** 将字节数组转换成16进制字符串 */
    public static String bytesToHex(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    /**
     * 递归调用获取一个obj的所有fileUniqueCode字段
     * @param obj
     * @param fileUniqueCodes
     */
    public void getFileUniqueCode(Object obj, List<String> fileUniqueCodes){
        String FILE_UNIQUE_CODE_STRING = "fileUniqueCode";
        if(obj instanceof Collection){
            JSONArray jsonArraySource = JSONArray.parseArray(JSON.toJSONString(obj));
            for(Object v : jsonArraySource){
                getFileUniqueCode(v, fileUniqueCodes);
            }
        } else if(isSimpleType(obj)){
            return;
        } else {
            JSONObject jsonObjectSource = JSONObject.parseObject(JSON.toJSONString(obj));
            Set<String> propertiesSet = jsonObjectSource.keySet();
            if (CollectionUtils.isEmpty(propertiesSet)) {
                return;
            }
            for(String key : propertiesSet){
                Object jsonObjectSourceV = jsonObjectSource.get(key);
                if(FILE_UNIQUE_CODE_STRING.equals(key)){
                    fileUniqueCodes.add(String.valueOf(jsonObjectSourceV));
                }else{
                    getFileUniqueCode(jsonObjectSourceV, fileUniqueCodes);
                }
            }
        }
    }

    /**
     * 判断是否简单类型，不能转换成JSONObject
     * clazz.isPrimitive()不能判断Boolean，原因未知
     * 此方法参考springframkwork  BeanUtils.isSimpleValueType()
     * @param obj
     * @return
     */
    private boolean isSimpleType(Object obj){
        Class clazz = obj.getClass();
        return (clazz.isPrimitive() || clazz.isEnum() ||
                CharSequence.class.isAssignableFrom(clazz) ||
                Number.class.isAssignableFrom(clazz) ||
                Date.class.isAssignableFrom(clazz) ||
                URI.class == clazz || URL.class == clazz ||
                Locale.class == clazz || Class.class == clazz || obj instanceof Boolean);
    }

}
