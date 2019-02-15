package lsieun.chat;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import lsieun.utils.FileUtils;
import lsieun.utils.IOUtils;

public class Z001 {
    public static void main(String[] args) {
        System.out.println("Integer Bytes = " + Integer.BYTES);
        System.out.println(User.PATTERN);
        System.out.println("===========================");

        User user = new User(3, "张三");

        String filepath = FileUtils.getFilePath("user_info.bin");
        System.out.println("filepath = " + filepath);

        OutputStream out = null;
        try {
            out = new FileOutputStream(filepath);
            out = new BufferedOutputStream(out);

            DataUtils.writeUser(out, user);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }

        String hexCodeStr = IOUtils.readFormatHex(filepath);
        System.out.println("===========================");
        System.out.println(hexCodeStr);
    }

}
