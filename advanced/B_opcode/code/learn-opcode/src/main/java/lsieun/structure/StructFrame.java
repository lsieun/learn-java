package lsieun.structure;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lsieun.bytes.ByteDashboard;
import lsieun.standard.Entry;
import lsieun.utils.FileUtils;

public class StructFrame {
    private String dir;
    private String ext;
    private String mainStructName;
    private List<String> structList;
    private Map<String, Struct> structMap;

    public StructFrame(String dir, String ext, String mainStructName) {
        this.dir = dir;
        this.ext = ext;
        this.mainStructName = mainStructName;
        this.structList = new ArrayList();
        this.structMap = new HashMap();

        this.addStruct(mainStructName);
    }

    // region getters and setters

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getMainStructName() {
        return mainStructName;
    }

    public void setMainStructName(String mainStructName) {
        this.mainStructName = mainStructName;
    }

    public List<String> getStructList() {
        return structList;
    }

    public void setStructList(List<String> structList) {
        this.structList = structList;
    }

    public Map<String, Struct> getStructMap() {
        return structMap;
    }

    public void setStructMap(Map<String, Struct> structMap) {
        this.structMap = structMap;
    }

    // endregion

    private void addStruct(String structName) {
        if(this.structList.contains(structName)) return;
        this.structList.add(structName);
    }

    public String getFilePath(String structName, boolean hasExtension) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.dir);
        sb.append(structName);
        if(hasExtension) {
            sb.append("." + this.ext);
        }
        return sb.toString();
    }

    public void init() {
        // 找主文件分析
        for(int i=0; i<this.structList.size(); i++) {
            String structName = this.structList.get(i);
            initStruct(structName);
        }
    }

    /**
     * 思路：
     * （1）第一种情况，如果存在文件，则说明是普通struct
     * （2）第二种情况，如果存在目录，则说明是抽象struct
     * （3）第三种情况，以上都不成立，说明有问题，抛出异常
     * @param structName 结构体的名字
     */
    private void initStruct(String structName) {
        //（1）第一种情况，如果存在文件，则说明是普通struct
        String filename = getFilePath(structName, true);
        File file = new File(filename);
        if(file.exists()) {
            initConcreteStruct(structName);
            return;
        }

        //（2）第二种情况，如果存在目录，则说明是抽象struct
        String dirname = getFilePath(structName, false);
        File dirFile = new File(dirname);
        if(dirFile.exists() && dirFile.isDirectory()) {
            initAbstractStruct(structName);
            return;
        }


        //（3）第三种情况，以上都不成立，说明有问题，抛出异常
        //throw new FileNotFoundException(structName);
        throw new RuntimeException("FileNotFound: " + structName);
    }

    public void print() {
        for(int i=0; i<this.structList.size(); i++) {
            String structName = this.structList.get(i);
            Struct struct = this.structMap.get(structName);
            struct.print();
        }
    }

    private void initConcreteStruct(String structName) {
        String filename = getFilePath(structName, true);
        List<String> lines = FileUtils.readLines(filename);
        ConcreteStruct struct = StructParser.parseConcreteStruct(lines);
        this.structMap.put(structName, struct);

        // 获取子结构体
        List<Item> items = struct.getItems();
        for(int i=0; i<items.size(); i++) {
            Item item = items.get(i);
            ItemType type = item.getType();
            String typeName = item.getTypeName();

            if(type == ItemType.COMPOSITE) {
                this.addStruct(typeName);
            }
        }

    }

    private void initAbstractStruct(String structName) {
        String filename = getFilePath(structName, false) + File.separator + "index.c";

        File file = new File(filename);
        if(!file.exists() || !file.isFile()) {
            throw new RuntimeException("Not Abstract Struct: " + structName + ", " + filename);
        }
        List<String> lines = FileUtils.readLines(filename);
        AbstractStruct struct = StructParser.parseAbstractStruct(lines);
        this.structMap.put(structName, struct);

        // 获取子结构体
        List<String> structList = struct.getStructList();
        for(int i=0; i<structList.size(); i++) {
            String subStructName = structList.get(i);
            String fullStructName = structName + "/" + subStructName;
            this.addStruct(fullStructName);
        }
    }

}
