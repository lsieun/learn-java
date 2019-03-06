package lsieun.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lsieun.utils.ByteUtils;
import lsieun.utils.HexUtils;
import lsieun.utils.StringUtils;

public class KeyValuePair {
    private String key;
    private byte[] bytes;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        List<String> list = new ArrayList();
        list.add("HexCode='" + ByteUtils.toHex(bytes) +"'");

        String content = StringUtils.list2str(list, ", ");

        StringBuilder sb = new StringBuilder();
        sb.append(this.key + ": {");
        sb.append(content);
        sb.append("}");

        return sb.toString();
    }
}
