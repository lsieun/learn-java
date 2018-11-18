package lsieun.javaagent.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarUtil {
    public static List<String> getAllEntries(String filePath) {
        List<String> list = new ArrayList<String>();
        try {
            JarFile jarFile = new JarFile(filePath);
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                list.add(entry.getName());
                //System.out.println(entry.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void filter(List<String> list, String reg) {
        if (list == null || list.size() < 1) return;

        for (int i=list.size()-1; i>=0; i--) {
            String entryName = list.get(i);
            if (!entryName.matches(reg)) {
                list.remove(i);
            }
        }
    }

    /**
     * Get Fully-Qualified Class Name
     * @param entryName
     * @return
     */
    public static String getFQCN(String entryName) {
        String className = entryName.replace(".class", "").replace("/",".");
        return className;
    }

    public static byte[] readClass(String jarPath, String entryName) {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(jarPath);
            JarEntry entry = jarFile.getJarEntry(entryName);
            InputStream in = jarFile.getInputStream(entry);

            in = new BufferedInputStream(in);

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (jarFile != null) {
                try {
                    jarFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Map<String, ByteArrayOutputStream> getAllClasses(String jarPath, List<String> entryList) {
        if (jarPath == null || "".equals(jarPath)) return null;
        if (entryList == null || entryList.size() < 1) return null;

        Map<String, ByteArrayOutputStream> map = new HashMap<String, ByteArrayOutputStream>();
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(jarPath);
            for (String entryName : entryList) {
                JarEntry entry = jarFile.getJarEntry(entryName);
                InputStream in = jarFile.getInputStream(entry);

                in = new BufferedInputStream(in);

                ByteArrayOutputStream out = new ByteArrayOutputStream();

                byte[] buf = new byte[1024];
                int len = -1;
                while ((len = in.read(buf)) != -1) {
                    out.write(buf, 0, len);
                }

                String className = getFQCN(entryName);
                map.put(className, out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (jarFile != null) {
                try {
                    jarFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
}
