package lsieun.standard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container {
    private String name;
    private List<Entry> entryList;
    private Map<String, Entry> entryMap;

    public Container(String name) {
        this.name = name;
        this.entryList = new ArrayList();
        this.entryMap = new HashMap();
    }

    // region getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }

    public Map<String, Entry> getEntryMap() {
        return entryMap;
    }
    // endregion
}
