package jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by zcy on 18-3-9.
 * -Xmx20M -XX:MaxDirectMemorySize=10M
 *
 * ============================================
 * Exception in thread "main" java.lang.OutOfMemoryError
 at sun.misc.Unsafe.allocateMemory(Native Method)
 at jvm.DirectMemoryOOM.main(DirectMemoryOOM.java:19)
 Java HotSpot(TM) 64-Bit Server VM warning: Attempt to deallocate stack guard pages failed.
 Java HotSpot(TM) 64-Bit Server VM warning: INFO: os::commit_memory(0x00007f733cef1000, 12288, 0) failed; error='无法分配内存' (errno=12)
 #
 # There is insufficient memory for the Java Runtime Environment to continue.
 # Native memory allocation (mmap) failed to map 12288 bytes for committing reserved memory.
 # An error report file with more information is saved as:
 # /home/chris/IdeaProjects/Test/hs_err_pid22401.log
 *
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024*1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
