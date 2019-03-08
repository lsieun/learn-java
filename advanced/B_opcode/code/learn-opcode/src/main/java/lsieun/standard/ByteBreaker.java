package lsieun.standard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lsieun.bytes.ByteDashboard;
import lsieun.structure.AbstractStruct;
import lsieun.structure.ConcreteStruct;
import lsieun.structure.Item;
import lsieun.structure.ItemType;
import lsieun.structure.Struct;
import lsieun.structure.StructFrame;
import lsieun.utils.ByteUtils;
import lsieun.utils.HexUtils;

public class ByteBreaker {

    private ByteDashboard byteDashboard;
    private StructFrame structFrame;

    public ByteBreaker(ByteDashboard byteDashboard, StructFrame structFrame) {
        this.byteDashboard = byteDashboard;
        this.structFrame = structFrame;
    }

    public Container analyze() {
        String mainStructName = this.structFrame.getMainStructName();
        return analyze(mainStructName);
    }

    public Container analyze(String structName) {
        //
        String containerName = structName;
        Map<String, Struct> structMap = this.structFrame.getStructMap();
        Struct struct = structMap.get(structName);

        int count = 0;
        while(!(struct instanceof ConcreteStruct)) {
            // FIXME: 将struct处理成ConcreteStruct

            if(struct instanceof AbstractStruct) {
                AbstractStruct abstractStruct = (AbstractStruct) struct;
                Item item = abstractStruct.getItem();
                ItemType type = item.getType();
                int size = type.value();
                byte[] bytes = this.byteDashboard.peek(size);
                String hexCode = ByteUtils.toHex(bytes);
                int switchIndex = HexUtils.toInt(hexCode);

                String subStructName = abstractStruct.getStruct(switchIndex);
                containerName = subStructName;
                struct = structMap.get(structName + "/" + subStructName);
            }


            count++;
            if(count > 5) throw new RuntimeException("There is Something Wrong: " + structName);
        }

        // 将struct中的多个item取出来
        ConcreteStruct concreteStruct = (ConcreteStruct) struct;
        List<Item> items = concreteStruct.getItems();
        int size = items.size();

        // 将多个item转换为container中的多个entry
        Container container = new Container();
        container.setName(containerName);
        for(int i=0; i<size; i++) {
            Item item = items.get(i);
            addEntry2Container(container, item);
        }

        return container;
    }

    public void addEntry2Container(Container container, Item item) {
        ItemType type = item.getType();
        int count = item.getCount();

        // 处理四种情况：
        // （1）普通类型，一个，结果：普通entry就好
        // （2）普通类型，多个，结果：要返回entryList才好
        // （3）复合类型，一个，结果：container
        // （4）复合类型，多个，结果：多个container

        int processType = 0;
        int value = type.value();
        if(value > 0) {
            if(count == 1) {
                processType = 1;
            }
            else {
                processType = 2;
            }
        }
        else {
            if(count == 1) {
                processType = 3;
            }
            else {
                processType = 4;
            }
        }


        if(processType == 1) {
            addOneEntry(container, item);
        }
        else if(processType == 2) {
            addMultiEntries(container, item);
        }
        else if(processType == 3) {
            addOneContainer(container, item);
        }
        else if(processType == 4) {
            addMultiContainers(container, item);
        }
        else {
            throw new RuntimeException("processType WRONG: " + item);
        }

    }

    public void addOneEntry(Container container, Item item) {
        ItemType type = item.getType();
        String itemName = item.getItemName();

        int size = type.value();
        byte[] bytes = readBytes(size);


        // 设置entry
        Entry entry = new Entry();
        entry.setType(EntryType.ONE_ENTRY);
        entry.setName(itemName);
        entry.setBytes(bytes);
        entry.setParent(container);

        // 设置container
        container.addEntry(entry);
    }

    public byte[] readBytes(int size) {
        byte[] bytes = new byte[size];
        for(int i=0; i<size; i++) {
            byte b = this.byteDashboard.readByte();
            bytes[i] = b;
        }
        return bytes;
    }

    public void addMultiEntries(Container container, Item item) {
        ItemType type = item.getType();
        String itemName = item.getItemName();
        String countExpression = item.getCountExpression();

        ArithmeticHelper arithmeticHelper = new ArithmeticHelper(countExpression);
        arithmeticHelper.parse();
        String field = arithmeticHelper.getField();
        int delta = arithmeticHelper.getDelta();

        Entry targetEntry = container.getEntry(field);
        String hexCode = ByteUtils.toHex(targetEntry.getBytes());
        int count = HexUtils.toInt(hexCode);
        count += delta;

        // 设置entry
        Entry entry = new Entry();

        entry.setName(itemName);

        entry.setParent(container);

        int size = type.value();
        if(size == 1) { //对于只有一个字节的多个元素，将它们视为一个元素包含多个字节处理
            int totalSize = size * count;
            byte[] bytes = readBytes(totalSize);
            entry.setType(EntryType.ONE_ENTRY);
            entry.setBytes(bytes);
        }
        else {
            List<OneThing> subEntryList = new ArrayList();
            for(int i=0; i<count; i++) {
                byte[] bytes = readBytes(size);
                Entry subEntry = new Entry();
                subEntry.setType(EntryType.ONE_ENTRY);
                subEntry.setName("");
                subEntry.setBytes(bytes);
                subEntry.setParent(null);
                subEntryList.add(subEntry);
            }

            entry.setType(EntryType.MULTI_ENTRIES);
            entry.setMembers(subEntryList);
        }

        // 设置container
        container.addEntry(entry);
    }

    public void addOneContainer(Container container, Item item) {
        String itemName = item.getItemName();
        String structName = item.getTypeName();

        Container subContainer = this.analyze(structName);
        List<OneThing> subEntryList = new ArrayList();
        subEntryList.add(subContainer);

        // 设置entry
        Entry entry = new Entry();
        entry.setType(EntryType.ONE_CONTAINER);
        entry.setName(itemName);
        entry.setMembers(subEntryList);
        entry.setParent(container);

        // 设置container
        container.addEntry(entry);
    }

    public void addMultiContainers(Container container, Item item) {
        String structName = item.getTypeName();
        String itemName = item.getItemName();
        String countExpression = item.getCountExpression();

        ArithmeticHelper arithmeticHelper = new ArithmeticHelper(countExpression);
        arithmeticHelper.parse();
        String field = arithmeticHelper.getField();
        int delta = arithmeticHelper.getDelta();

        Entry targetEntry = container.getEntry(field);
        String hexCode = ByteUtils.toHex(targetEntry.getBytes());
        int count = HexUtils.toInt(hexCode);
        count += delta;

        List<OneThing> subEntryList = new ArrayList();
        for(int i=0; i<count; i++) {
            Container subContainer = this.analyze(structName);
            subEntryList.add(subContainer);
        }

        // 设置entry
        Entry entry = new Entry();
        entry.setType(EntryType.MULTI_CONTAINERS);
        entry.setName(itemName);
        entry.setMembers(subEntryList);
        entry.setParent(container);

        // 设置container
        container.addEntry(entry);

    }
}
