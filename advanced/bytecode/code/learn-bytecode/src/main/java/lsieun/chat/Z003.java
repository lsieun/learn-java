package lsieun.chat;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import lsieun.utils.FileUtils;
import lsieun.utils.IOUtils;

public class Z003 {
    public static void main(String[] args) {

        List<Say> sayList = ChatUtils.getSayList();
        List<Talker> talkerList = ChatUtils.getTalkerList();

        String filepath = FileUtils.getFilePath("chat_basic_info.bin");
        System.out.println("filepath = " + filepath);

        OutputStream out = null;
        try {
            out = new FileOutputStream(filepath);
            out = new BufferedOutputStream(out);

            DataUtils.writeNum(out, talkerList.size(), 2);
            for(Talker item : talkerList) {
                DataUtils.writeTalker(out, item);
            }

            DataUtils.writeNum(out, sayList.size(), 2);
            for(Say item : sayList) {
                DataUtils.writeSay(out, item);
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
