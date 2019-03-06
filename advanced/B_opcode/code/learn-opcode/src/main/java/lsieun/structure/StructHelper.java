package lsieun.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lsieun.utils.FileUtils;

public class StructHelper {
    private String dir;
    private String ext;
    private String mainStructName;
    private Map<String, Struct> structMap;

    public StructHelper(String dir, String ext, String mainStructName) {
        this.dir = dir;
        this.ext = ext;
        this.mainStructName = mainStructName;
        this.structMap = new HashMap();
    }

    public String getMainStructFilePath() {
        return getFilePath(this.mainStructName);
    }

    public String getFilePath(String structName) {
        return this.dir + structName + "." + this.ext;
    }

    public Struct getStruct(String structName) {
        if(this.structMap.containsKey(structName)) {
            Struct oldResult = this.structMap.get(structName);
            return oldResult;
        }

        String filename = getFilePath(structName);
        List<String> lines = FileUtils.readLines(filename);

        Struct newResult = StructParser.parse(lines);
        this.structMap.put(structName, newResult);

        return newResult;
    }

    public List<KeyValuePair> analyze(FileBytes fileBytes) {
        return this.analyze(fileBytes, this.mainStructName);
    }

    public List<KeyValuePair> analyze(FileBytes fileBytes, String structName) {
        Struct mainStruct = getStruct(structName);
        List<Item> items = mainStruct.getItems();
        int size = items.size();

        List<KeyValuePair> list = new ArrayList();

        for(int i=0; i<size; i++) {
            Item item = items.get(i);
            KeyValuePair pair = toPair(fileBytes, item);
            System.out.println(pair);
            list.add(pair);

            ItemType type = item.getType();
            int value = type.value();
            if(value < 1) break;
        }

        return list;
    }

    public KeyValuePair toPair(FileBytes fileBytes, Item item) {
        ItemType type = item.getType();
        String typeName = item.getTypeName();
        String itemName = item.getItemName();
        int count = item.getCount();

        KeyValuePair pair = new KeyValuePair();
        pair.setKey(itemName);

        int size = type.value();
        if(size > 0) {
            byte[] bytes = new byte[size];
            for(int i=0; i<size; i++) {
                byte b = fileBytes.readByte();
                bytes[i] = b;
            }
            pair.setBytes(bytes);

        }
        else {
            System.out.println(typeName);
        }

        return pair;
    }
}
