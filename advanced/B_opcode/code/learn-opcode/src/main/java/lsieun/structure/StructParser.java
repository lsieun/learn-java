package lsieun.structure;

import java.util.List;

import lsieun.utils.StringUtils;

public class StructParser {
    public static Struct parse(List<String> lines) {
        if(lines == null || lines.size() < 1) return null;

        int size = lines.size();
        String theFirst = lines.get(0);
        //String theLast = lines.get(size -1);

        int start = 1;
        int stop = size - 1;

        Struct struct = new Struct();
        List<Item> itemList = struct.getItems();

        // struct name
        String trimStr = StringUtils.trim(theFirst);
        List<String> splitList = StringUtils.split(trimStr, " ");
        String structName = splitList.get(0);
        struct.setName(structName);
        System.out.println(structName);

        // struct items
        for(int i=start; i<stop; i++) {
            String theLine = lines.get(i);
            if(StringUtils.isBlank(theLine)) continue;
            Item item = line2Item(theLine);
            System.out.println("\t" + item);
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
}
