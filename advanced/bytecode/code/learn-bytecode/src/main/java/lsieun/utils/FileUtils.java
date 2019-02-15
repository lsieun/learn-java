package lsieun.utils;

public class FileUtils {
    public static String getFilePath(String filename) {
        String dir = IOUtils.class.getResource("/").getPath();
        String filepath = dir + filename;
        return filepath;
    }
}
