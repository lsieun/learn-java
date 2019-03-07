package lsieun;

import lsieun.file.FileBytes;
import lsieun.structure.StructFrame;
import lsieun.utils.ByteUtils;
import lsieun.utils.FileUtils;
import lsieun.utils.HexUtils;
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

        String hexCodeStr = ByteUtils.toHex(bytes).toUpperCase();
        System.out.println(HexUtils.format(hexCodeStr));

        FileBytes fileBytes = new FileBytes(url, bytes);
        System.out.println(fileBytes);

        String dir = FileUtils.getFilePath("structure/");
        System.out.println(dir);
        String ext = "c";
        String mainStructName = "ClassFile";
        StructFrame frame = new StructFrame(dir, ext, mainStructName);
        frame.init();
        frame.print();

//        String dir = ReadClass.class.getResource("/").getPath();
//        String filename = dir + "structure/ClassFile.c";
//        System.out.println(filename);
//
//        List<String> lines = FileUtils.readLines(filename);
//        for(String line : lines) {
//            System.out.println(line);
//        }
//
//        StructParser.parseConcreteStruct(lines);
    }
}
