package lsieun.chat;

import java.io.UnsupportedEncodingException;

import lsieun.utils.HexUtils;
import lsieun.utils.StringUtils;

public class Talker {
    public static final String PATTERN = "index(1)-length(2)-bytes(length)";

    private final int id;
    private final String name;
    private final int length;
    private final byte[] bytes;

    public Talker(int id, String name) {
        byte[] bytes = StringUtils.getBytes(name, "UTF8");
        int length = bytes.length;

        this.id = id;
        this.name = name;
        this.length = length;
        this.bytes = bytes;
    }

    public int getId() {
        return id;
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
                "id=" + id +
                ", name='" + name + "'" +
                ", length=" + length +
                ", bytes=" + HexUtils.toHex(bytes) +
                '}';
    }
}
