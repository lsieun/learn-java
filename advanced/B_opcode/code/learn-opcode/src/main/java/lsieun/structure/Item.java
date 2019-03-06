package lsieun.structure;

public class Item {
    private ItemType type;
    private String typeName;
    private String itemName;
    private String countExpression;
    private int count;

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
        if("u1".equals(typeName)) {
            this.type = ItemType.U1;
        }
        else if("u2".equals(typeName)) {
            this.type = ItemType.U2;
        }
        else if("u4".equals(typeName)) {
            this.type = ItemType.U4;
        }
        else if("u8".equals(typeName)) {
            this.type = ItemType.U8;
        }
        else {
            this.type = ItemType.COMPOSITE;
        }
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCountExpression() {
        return countExpression;
    }

    public void setCountExpression(String countExpression) {
        this.countExpression = countExpression;
        if(countExpression == null) {
            this.count = 1;
        }
        else {
            this.count = 0;
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return itemName + " {" +
                "type='" + typeName + '\'' +
                ", count=" + count +
                ", countExpression='" + countExpression + '\'' +
                '}';
    }
}
