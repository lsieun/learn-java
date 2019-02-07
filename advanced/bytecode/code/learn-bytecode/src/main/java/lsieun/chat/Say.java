package lsieun.chat;

import java.io.UnsupportedEncodingException;

import lsieun.utils.IOUtils;

public class Say {
    public static final String PATTERN = "talkerIndex(1)-length(2)-bytes(length)";

    private final int talkerIndex;
    private final String words;
    private final int length;
    private final byte[] bytes;

    public Say(int talkerIndex, String words) {
        int length = 0;
        byte[] bytes = null;

        try {
            bytes = words.getBytes("UTF8");
            length = bytes.length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        this.talkerIndex = talkerIndex;
        this.words = words;
        this.length = length;
        this.bytes = bytes;
    }

    public int getTalkerIndex() {
        return talkerIndex;
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
                "talkerIndex=" + talkerIndex +
                ", words='" + words + '\'' +
                ", length=" + length +
                ", bytes=" + IOUtils.toHex(bytes) +
                '}';
    }
}
