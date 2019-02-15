package lsieun.chat;

import java.io.IOException;
import java.io.OutputStream;

import lsieun.utils.IntegerUtils;
import lsieun.utils.HexUtils;
import lsieun.utils.StringUtils;

@SuppressWarnings("Duplicates")
public class DataUtils {
    public static void writeUser(OutputStream out, User user) throws IOException {
        // 内存已有的两个字段
        int id = user.getId();
        String name = user.getName();

        // 映射：内存->硬盘

        // 硬盘中需要的字段
        int index = id;
        byte[] bytes = StringUtils.getBytes(name, "UTF8");
        int length = bytes.length;

        out.write(IntegerUtils.toBytes(index, 1));
        out.write(IntegerUtils.toBytes(length, 2));
        out.write(bytes);
    }

    public static void writeTalker(OutputStream out, Talker talker) throws IOException {
        int index = talker.getId();
        int length = talker.getLength();
        byte[] bytes = talker.getBytes();

        out.write(IntegerUtils.toBytes(index, 1));
        out.write(IntegerUtils.toBytes(length, 2));
        out.write(bytes);
    }

    public static void writeSay(OutputStream out, Say say) throws IOException {
        int index = say.getId();
        int talkerIndex = say.getTalkerId();
        int length = say.getLength();
        byte[] bytes = say.getBytes();

        out.write(IntegerUtils.toBytes(index, 1));
        out.write(IntegerUtils.toBytes(talkerIndex, 1));
        out.write(IntegerUtils.toBytes(length, 2));
        out.write(bytes);
    }

    public static void writeHex(OutputStream out, String hexCode) throws IOException {
        byte[] bytes = HexUtils.toBytes(hexCode);
        out.write(bytes);
    }

    public static void writeNum(OutputStream out, int num, int byteCount) throws IOException {
        out.write(IntegerUtils.toBytes(num, byteCount));
    }

}
