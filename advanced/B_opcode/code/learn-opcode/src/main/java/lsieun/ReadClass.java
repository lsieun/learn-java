package lsieun;

import java.util.List;

import lsieun.bytes.ByteDashboard;
import lsieun.standard.ByteBreaker;
import lsieun.standard.Container;
import lsieun.standard.Entry;
import lsieun.standard.EntryType;
import lsieun.standard.OneThing;
import lsieun.structure.StructFrame;
import lsieun.utils.FileUtils;
import lsieun.utils.JarUtils;

public class ReadClass {
    private static final boolean READ_JAR = false;

    public static void main(String[] args) {
        String url = null;
        byte[] bytes = null;
        if(READ_JAR) {
            String jarPath = "/usr/local/jdk8/jre/lib/rt.jar";
            String entryName = "java/lang/Object.class";
            url = "jar:file:" + jarPath + "!/" + entryName;
            bytes = JarUtils.readBytes(jarPath, entryName);
        }
        else {
            String dir = ReadClass.class.getResource(".").getPath();
            String filepath = dir + "example/HelloWorld.class";
            url = "file://" + filepath;
            bytes = FileUtils.readBytes(filepath);
        }

        //String hexCodeStr = ByteUtils.toHex(bytes).toUpperCase();
        //System.out.println(HexUtils.format(hexCodeStr));

        ByteDashboard byteDashboard = new ByteDashboard(url, bytes);
        System.out.println(byteDashboard);



        String dir = FileUtils.getFilePath("structure/classfile/");
        String ext = "c";
        String mainStructName = "ClassFile";
        StructFrame frame = new StructFrame(dir, ext, mainStructName);
        frame.init();
        //frame.print();

        ByteBreaker byteBreaker = new ByteBreaker(byteDashboard, frame);
        Container container = byteBreaker.analyze();
        print(container, "");
    }

    public static void print(Container container, String prefix) {
        String name = container.getName();

        String newPrefix = "";
        if("".equals(prefix)) {
            newPrefix = name;
        }
        else {
            newPrefix = prefix + "-" + name;
            System.out.println(prefix + "-" + name);
        }
        System.out.println(name);

        List<Entry> entryList = container.getEntryList();
        for(int i=0; i<entryList.size(); i++) {
            Entry entry = entryList.get(i);
            System.out.println("    " + entry);
        }

        System.out.println("\r\n====================================\r\n");

        for(int i=0; i<entryList.size(); i++) {
            Entry entry = entryList.get(i);

            if(entry.getType() == EntryType.ONE_CONTAINER || entry.getType() == EntryType.MULTI_CONTAINERS) {
                List<OneThing> members = entry.getMembers();
                for(int j=0; j<members.size(); j++) {
                    Container c = (Container) members.get(j);
                    print(c, newPrefix);
                }
            }
        }
    }
}
