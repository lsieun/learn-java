package lsieun.chat;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import lsieun.utils.FileUtils;
import lsieun.utils.IOUtils;

public class Z004 {
    public static void main(String[] args) {

        List<Say> sayList = ChatUtils.getSayList();
        List<Talker> talkerList = ChatUtils.getTalkerList();

        String filepath = FileUtils.getFilePath("chat_full_info.bin");
        System.out.println("filepath = " + filepath);

        OutputStream out = null;
        try {
            out = new FileOutputStream(filepath);
            out = new BufferedOutputStream(out);

            DataUtils.writeHex(out, "ABCDDCBA");
            DataUtils.writeNum(out, 2019, 2);
            DataUtils.writeNum(out, 2, 1);
            DataUtils.writeNum(out, 10, 1);

            DataUtils.writeNum(out, talkerList.size(), 2);
            for(int i=0; i<talkerList.size(); i++) {
                Talker talker = talkerList.get(i);
                DataUtils.writeTalker(out, talker);
            }

            DataUtils.writeNum(out, sayList.size(), 2);
            for(int i=0; i<sayList.size(); i++) {
                Say say = sayList.get(i);
                DataUtils.writeSay(out, say);
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

        System.out.println("===========================");
        hexCodeStr = hexCodeStr.replaceAll(" ", "");
        System.out.println(hexCodeStr.toUpperCase());
    }

}
