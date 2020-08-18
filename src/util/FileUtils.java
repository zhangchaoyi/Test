package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import enums.FileType;
import org.apache.commons.collections4.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
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

    /**
     * 从包package中获取所有的Class（包含从JAR包中取）
     *
     * @param path 查找路径
     * @param recursive 是否循环迭代
     * @return
     */
    public static List<Class<?>> getClasses(String path, boolean recursive) {
        List<Class<?>> classes = new ArrayList<>();

        String packageName = path;
        String packageDirName = packageName.replace('.', '/');

        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();

                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
                } else if ("jar".equals(protocol)) {

                    // 如果是jar包文件定义一个JarFile
                    JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
                    // 从此jar包 得到一个枚举类
                    Enumeration<JarEntry> entries = jar.entries();
                    // 同样的进行循环迭代
                    while (entries.hasMoreElements()) {

                        // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                        JarEntry entry = entries.nextElement();
                        String name = entry.getName();
                        // 如果是以/开头的
                        if (name.charAt(0) == '/') {
                            // 获取后面的字符串
                            name = name.substring(1);
                        }
                        // 如果前半部分和定义的包名相同
                        if (name.startsWith(packageDirName)) {
                            int idx = name.lastIndexOf('/');
                            // 如果以"/"结尾 是一个包
                            if (idx != -1) {
                                // 获取包名 把"/"替换成"."
                                packageName = name.substring(0, idx).replace('/', '.');
                            }
                            // 如果可以迭代下去 并且是一个包
                            if ((idx != -1) || recursive) {
                                // 如果是一个.class文件 而且不是目录
                                if (name.endsWith(".class") && !entry.isDirectory()) {
                                    // 去掉后面的".class" 获取真正的类名
                                    String className = name.substring(packageName.length() + 1, name.length() - 6);
                                    classes.add(Class.forName(packageName + '.' + className));
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("get classes error, path={}");
            throw new RuntimeException("get classes error");
        }

        return classes;
    }

    /**
     * 以文件的形式来获取包下的所有Class
     *
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive,
                                                        List<Class<?>> classes) {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("用户定义包名 " + packageName + " 下没有任何文件");
            return;
        }

        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(pathname -> {
            //filter files ending with .class
            return recursive && pathname.getName().endsWith(".class");

        });
        // 循环所有文件
        for (File file : dirfiles) {
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive,
                        classes);
            } else {
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    classes.add(Class.forName(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    System.out.println("class not found.");
                }
            }
        }

    }
}
