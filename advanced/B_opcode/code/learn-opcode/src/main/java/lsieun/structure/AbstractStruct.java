package lsieun.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractStruct extends Struct {
    private Item item;
    private List<Integer> indexList;
    private List<String> structList;
    private Map<Integer, String> structMap;


    public AbstractStruct() {
        this.indexList = new ArrayList();
        this.structList = new ArrayList();
        this.structMap = new HashMap();
    }

    // region getters and setters
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Integer> getIndexList() {
        return indexList;
    }

    public void setIndexList(List<Integer> indexList) {
        this.indexList = indexList;
    }

    public List<String> getStructList() {
        return structList;
    }

    public void setStructList(List<String> structList) {
        this.structList = structList;
    }

    public Map<Integer, String> getStructMap() {
        return structMap;
    }

    public void setStructMap(Map<Integer, String> structMap) {
        this.structMap = structMap;
    }
    // endregion

    @Override
    public void print() {
        System.out.println(super.getName());
        System.out.println("\t" + this.item);

        int size = this.structList.size();
        for(int i=0; i<size; i++) {
            Integer index = this.indexList.get(i);
            String structName = this.structList.get(i);
            System.out.println("\t" + index + ":" + structName);
        }
    }
}
