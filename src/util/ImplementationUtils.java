package util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * Created by za-hejun on 2018/4/28.
 */
@Slf4j
public class ImplementationUtils {

    private volatile static util.ImplementationUtils instance;

    private static Lock lock = new ReentrantLock();

    //class container
    private ConcurrentHashMap<String, List<Class<?>>> classesMap = new ConcurrentHashMap<>();

    private ImplementationUtils(){}

    //get the instance
    public static ImplementationUtils getInstance(){
        if(null == instance){
            try{
                if(lock.tryLock()){
                    if(null == instance){
                        instance = new ImplementationUtils();
                    }
                }
            }finally {
                lock.unlock();
            }
        }
        return instance;
    }

    //obtain all classes in the specified path
    public List<Class<?>> getSubClass(String path, Class iClazz) throws Exception {
        List<Class<?>> classes = FileUtils.getClasses(path, true);
        return classes.stream().filter(clazz ->{
            return iClazz.isAssignableFrom(clazz) && clazz != iClazz;
        }).collect(Collectors.toList());
    }

    //obtain all files ending with ".class" in the specified path
    //一旦打成jar包在服务器上面跑，改地方将拿不到file，会报URI is not hierarchical 异常
    @Deprecated
    public File[] getResources(String path) throws URISyntaxException {
        String filePath = path.replaceAll("\\.", "/");
        File file = new File(getClass().getClassLoader().getResource(filePath).toURI());
        return file.listFiles(pathname -> {

            //filter files ending with .class
            return pathname.getName().endsWith(".class");

        });
    }

    //get interface instance
    public <R> R determine(String path, Class<R> clazz, Function<List<Class<?>>, R> func) throws Exception {
        if(!classesMap.containsKey(path + clazz.getName())){
            classesMap.putIfAbsent(path + clazz.getName(), getSubClass(path, clazz));
        }
        List<Class<?>> classes = classesMap.get(path + clazz.getName());
        return func.apply(classes);
    }
}
