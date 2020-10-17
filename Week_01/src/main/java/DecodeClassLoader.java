import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class DecodeClassLoader extends ClassLoader {

    public static void main(String[] args) {

        try {
            DecodeClassLoader decodeClassLoader = new DecodeClassLoader();
            Class<?> helloClass = decodeClassLoader.findClass("C:/极客时间课件/作业/JAVA-000/Week_01/src/main/java/Hello.xlass");
            Object obj = helloClass.newInstance();
            Method method = helloClass.getMethod("hello");
            method.invoke(obj);
        }  catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] helloBytes = DecodeClassLoader.readFileBytes(name);
        for (int i = 0; i < helloBytes.length; i++) {
            helloBytes[i] = (byte) (255 - helloBytes[i]);
        }
        return defineClass(null, helloBytes, 0, helloBytes.length);
    }


    private static byte[] readFileBytes(String fileName) {
        byte[] res = null;
        try (FileInputStream fis = new FileInputStream(fileName);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            int i = 0;
            while ((i = fis.read()) != -1) {
                bos.write(i);
            }
            res = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

}
