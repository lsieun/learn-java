package lsieun.chat;

import java.io.UnsupportedEncodingException;

import lsieun.utils.IOUtils;

public class Talker {
    public static final String PATTERN = "index(1)-length(2)-bytes(length)";

    private final int index;
    private final String name;
    private final int length;
    private final byte[] bytes;

    public Talker(int index, String name) {

        int length = 0;
        byte[] bytes = null;
        try {
            bytes = name.getBytes("UTF8");
            length = bytes.length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        this.index = index;
        this.name = name;
        this.length = length;
        this.bytes = bytes;
    }

    public int getIndex() {
        return index;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        return "Talker{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", bytes=" + IOUtils.toHex(bytes) +
                '}';
    }
}
