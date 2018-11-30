package lsieun.jvm.classloader.loader_system;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class AppLoadClass {
    public static void main(String[] args) {
        StringBuilder classpath = new StringBuilder();
        classpath.append("/home/liusen/workdir/git-repo/learn-java/advanced/jvm/code/learn-jvm/target/classes");
        classpath.append(":");
        classpath.append("/home/liusen/workdir/dummy/tmp/Keys/tank.jar");

        String[] array = classpath.toString().split(":");
        String fqcn = "lsieun.util.ByteUtil";
        fqcn = "lsieun.jvm.classloader.clazzpath.PrintNativeLibPath";
        fqcn = "com.lsieun.tank.TankSimpleClient";

        byte[] bytes = null;
        for (String path : array) {
            bytes = findResource(path, fqcn);
            if (bytes != null) {
                System.out.println("Find it: " + path);
                break;
            }
        }
        if (bytes == null) {
            System.out.println("Not Found");
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

                copy(in, out);
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
        String subPath = fqcn.replace('.', '/').concat(".class");
        System.out.println("Zip File: " + "jar:file:" + path + "!" + subPath);

        try {
            JarFile jarFile = new JarFile(path);
            JarEntry entry = jarFile.getJarEntry(subPath);
            if (entry == null) {
                jarFile.close();
                return null;
            }
            InputStream in = jarFile.getInputStream(entry);
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            copy(in, out);

            in.close();
            jarFile.close();

            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void copy(InputStream in, OutputStream out) throws IOException{

        InputStream bufIn = new BufferedInputStream(in);
        OutputStream bufOut = new BufferedOutputStream(out);

        byte[] buff = new byte[1024];
        int len = -1;
        while ((len = bufIn.read(buff)) != -1) {
            bufOut.write(buff, 0, len);
        }

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
