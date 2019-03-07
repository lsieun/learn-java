package lsieun.structure;

import java.util.List;
import java.util.Map;

import lsieun.utils.StringUtils;

public class StructParser {
    public static ConcreteStruct parseConcreteStruct(List<String> lines) {
        if(lines == null || lines.size() < 1) return null;

        int size = lines.size();
        String theFirst = lines.get(0);
        //String theLast = lines.get(size -1);

        int start = 1;
        int stop = size - 1;

        // struct name
        String trimStr = StringUtils.trim(theFirst);
        List<String> splitList = StringUtils.split(trimStr, " ");
        String structName = splitList.get(0);

        ConcreteStruct struct = new ConcreteStruct();
        List<Item> itemList = struct.getItems();
        struct.setName(structName);

        // struct items
        for(int i=start; i<stop; i++) {
            String theLine = lines.get(i);
            if(StringUtils.isBlank(theLine)) continue;
            Item item = line2Item(theLine);
            itemList.add(item);
        }

        return struct;
    }

    private static Item line2Item(String line) {
        if(StringUtils.isBlank(line)) return null;
        String theLine = StringUtils.trim(line);

        int spaceIndex = theLine.indexOf(" ");
        int leftSquareIndex = theLine.indexOf("[");
        int rightSquareIndex = theLine.indexOf("]");
        int endIndex = theLine.indexOf(";");

        boolean isArray = false;
        if(leftSquareIndex != -1) isArray = true;

        String theFirst = theLine.substring(0, spaceIndex);
        String theSecond = theLine.substring(spaceIndex+1, endIndex);
        String theThird = null;

        if(isArray) {
            theSecond = theLine.substring(spaceIndex+1, leftSquareIndex);
            theThird = theLine.substring(leftSquareIndex+1, rightSquareIndex);
        }


        String typeName = theFirst;
        String itemName = theSecond;
        String countExpression = theThird;

        Item item = new Item();
        item.setTypeName(typeName);
        item.setItemName(itemName);
        item.setCountExpression(countExpression);

        return item;
    }

    public static AbstractStruct parseAbstractStruct(List<String> lines) {
        // 处理第1行数据，获取structName
        String theFirst = lines.get(0);
        String theTrimFirst = StringUtils.trim(theFirst);
        List<String> theSplitFirstList = StringUtils.split(theTrimFirst, " ");
        String structName = theSplitFirstList.get(0);

        // 处理第2行数据，获取item相关数据
        String theSecond = lines.get(1);
        String theTrimSecond = StringUtils.trim(theSecond);
        int spaceIndex = theTrimSecond.indexOf(" ");
        int endIndex = theTrimSecond.indexOf(";");

        // 封装item
        String typeName = theTrimSecond.substring(0, spaceIndex);
        String itemName = theTrimSecond.substring(spaceIndex+1, endIndex);
        Item item = new Item();
        item.setTypeName(typeName);
        item.setItemName(itemName);
        item.setCountExpression(null);

        // 第3行数据不进行处理，跳过

        // 处理第4~theLast行数据
        AbstractStruct struct = new AbstractStruct();
        List<Integer> indexList = struct.getIndexList();
        List<String> structList = struct.getStructList();
        Map<Integer, String> structMap = struct.getStructMap();

        struct.setName(structName);
        struct.setItem(item);

        int size = lines.size();
        for(int i=3; i<size; i++) {
            String theLine = lines.get(i);
            String theTrimLine = StringUtils.trim(theLine);
            List<String> theSplitList = StringUtils.split(theTrimLine, ":");
            String indexStr = theSplitList.get(0);
            String subStructName = theSplitList.get(1);

            int index = Integer.parseInt(indexStr);

            indexList.add(index);
            structList.add(subStructName);
            structMap.put(index, subStructName);
        }

        return struct;
    }
}
