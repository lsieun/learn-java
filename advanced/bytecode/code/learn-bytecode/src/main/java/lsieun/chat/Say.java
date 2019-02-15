package lsieun.chat;

import java.io.UnsupportedEncodingException;

import lsieun.utils.HexUtils;
import lsieun.utils.StringUtils;

public class Say {
    public static final String PATTERN = "index(1)-talkerIndex(1)-length(2)-bytes(length)";

    private final int id;
    private final int talkerId;
    private final String words;
    private final int length;
    private final byte[] bytes;

    public Say(int id, int talkerId, String words) {
        byte[] bytes = StringUtils.getBytes(words, "UTF8");
        int length = bytes.length;

        this.id = id;
        this.talkerId = talkerId;
        this.words = words;
        this.length = length;
        this.bytes = bytes;
    }

    public int getId() {
        return id;
    }

    public int getTalkerId() {
        return talkerId;
    }

    public String getWords() {
        return words;
    }

    public int getLength() {
        return length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        return "Say{" +
                "id=" + id +
                ", talkerId=" + talkerId +
                ", words='" + words + '\'' +
                ", length=" + length +
                ", bytes=" + HexUtils.toHex(bytes) +
                '}';
    }
}
