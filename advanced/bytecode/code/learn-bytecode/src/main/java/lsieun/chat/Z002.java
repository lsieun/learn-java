package lsieun.chat;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import lsieun.utils.FileUtils;
import lsieun.utils.IntegerUtils;
import lsieun.utils.IOUtils;

public class Z002 {
    public static void main(String[] args) {
        List<User> list = new ArrayList();
        list.add(new User(3, "张三"));
        list.add(new User(4, "李四"));
        list.add(new User(5, "王五"));
        list.add(new User(6, "赵六"));
        int userCount = list.size();

        String filepath = FileUtils.getFilePath("user_list_info.bin");
        System.out.println("filepath = " + filepath);

        OutputStream out = null;
        try {
            out = new FileOutputStream(filepath);
            out = new BufferedOutputStream(out);

            out.write(IntegerUtils.toBytes(userCount, 2));
            for(User user : list) {
                DataUtils.writeUser(out, user);
            }

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
