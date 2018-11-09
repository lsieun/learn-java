package lsieun.test;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lsieun.javaagent.util.JarUtil;

public class JarUtilTest {
    public static void main(String[] args) {
        String jarPath = "/home/liusen/workdir/dummy/idea-IU-182.4892.20/lib/idea.jar";
        List<String> list = JarUtil.getAllEntries(jarPath);

        String reg = "^com/jetbrains/\\w+/\\w+/\\w+\\.class$";
        JarUtil.filter(list, reg);
        System.out.println("List Size: " + list.size());
//        for (String name : list) {
//            System.out.println(name);
//        }
        System.out.println("File Name: " + list.get(0));
        System.out.println("Class Name: " + JarUtil.getFQCN(list.get(0)));

        String entryName = list.get(0);
        byte[] bytes = JarUtil.readClass(jarPath, entryName);
        System.out.println("Byte Length: " + bytes.length);

        Map<String, ByteArrayOutputStream> map = JarUtil.getAllClasses(jarPath, list);
        Iterator<Map.Entry<String, ByteArrayOutputStream>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ByteArrayOutputStream> entry = it.next();
            System.out.println(entry.getKey());
        }
    }
}
