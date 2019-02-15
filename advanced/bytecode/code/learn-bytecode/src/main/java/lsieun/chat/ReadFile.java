package lsieun.chat;

import lsieun.utils.FileUtils;
import lsieun.utils.IOUtils;

public class ReadFile {
    public static void main(String[] args) {
        String filepath = FileUtils.getFilePath("Z003.bin");
        String hexCodeStr = IOUtils.readFormatHex(filepath);
        System.out.println(hexCodeStr);
    }
}
