package lsieun.chat;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import lsieun.utils.IOUtils;

public class SampleA {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("Integer Bytes = " + Integer.BYTES);
        System.out.println("===========================");

        List<Talker> talkerList = new ArrayList();
        talkerList.add(new Talker(1, "唐太宗"));
        talkerList.add(new Talker(2, "魏征"));
        System.out.println(Talker.PATTERN);
        printList(talkerList);
        System.out.println("===========================");

        List<Say> sayList = new ArrayList();
        sayList.add(new Say(1, "人主何为而明，何为而暗?"));
        sayList.add(new Say(2, "兼听则明，偏听则暗。"));
        sayList.add(new Say(1, "作为皇帝怎样才能做到明察秋毫，而不被臣下所蒙蔽？"));
        sayList.add(new Say(2, "多听取各方面的意见就会明察秋毫，偏听偏信就会被人蒙蔽。"));
        System.out.println(Say.PATTERN);
        printList(sayList);
        System.out.println("===========================");

        String dir = SampleA.class.getResource(".").getPath();
        String filepath = dir + "SampleA.byte.code";
        System.out.println("filepath = " + filepath);
        System.out.println("===========================");

        OutputStream out = null;
        try {
            out = new FileOutputStream(filepath);
            out = new BufferedOutputStream(out);

            for(Talker item : talkerList) {
                writeTalker(out, item);
            }

            for(Say item : sayList) {
                writeSay(out, item);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    public static void writeTalker(OutputStream out, Talker talker) {
        int index = talker.getIndex();
        int length = talker.getLength();
        byte[] bytes = talker.getBytes();
        write(out, index, length, bytes);
    }

    public static void writeSay(OutputStream out, Say say) {
        int talkerIndex = say.getTalkerIndex();
        int length = say.getLength();
        byte[] bytes = say.getBytes();
        write(out, talkerIndex, length, bytes);
    }

    public static void write(OutputStream out, int index, int length, byte[] bytes) {
        try {
            out.write(ChatUtils.toBytes(index, 1));
            out.write(ChatUtils.toBytes(length, 2));
            out.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printList(List list) {
        for(int i=0; i<list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
