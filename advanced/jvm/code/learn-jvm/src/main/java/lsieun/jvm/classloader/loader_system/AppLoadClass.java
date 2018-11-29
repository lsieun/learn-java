package lsieun.jvm.classloader.loader_system;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AppLoadClass {
    public static void main(String[] args) {
        StringBuilder classpath = new StringBuilder();
        classpath.append("/home/liusen/workdir/git-repo/learn-java/advanced/jvm/code/learn-jvm/target/classes");
        classpath.append(":");
        classpath.append("/home/liusen/workdir/dummy/tmp/Keys/tank.jar");

        String[] array = classpath.toString().split(":");
        for (String path : array) {
            findResource(path, "lsieun.util.ByteUtil");
        }
    }

    public static byte[] findResource(String path, String fqcn) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }

        if (file.isFile()) {
            return findResoureFromZip(path, fqcn);
        }
        else {
            return findResoureFromDir(path, fqcn);
        }

    }

    public static byte[] findResoureFromDir(String path, String fqcn) {
        String subPath = fqcn.replace(".",File.separator) + ".class";
        String fullPath = path + File.separator + subPath;
        System.out.println("Full Path: " + fullPath);

        File file = new File(fullPath);
        if (!file.exists()) {
            return null;
        }
        if (file.isFile()) {
            InputStream in = null;
            try {
                in = new FileInputStream(file);
                in = new BufferedInputStream(in);
                ByteArrayOutputStream out = new ByteArrayOutputStream();

                byte[] buff = new byte[1024];
                int len = -1;
                while ((len = in.read(buff)) != -1) {
                    out.write(buff, 0, len);
                }
                return out.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeQuietly(in);
            }
        }
        return null;
    }


    public static byte[] findResoureFromZip(String path, String fqcn) {
        return null;
    }


    public static void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                // Do Nothing
            }
        }
    }
}
