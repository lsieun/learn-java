package lsieun.standard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container extends OneThing {
    private List<Entry> entryList;
    private Map<String, Entry> entryMap;

    public Container() {
        this.entryList = new ArrayList();
        this.entryMap = new HashMap();
    }

    // region getters and setters
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

    public void addEntry(Entry entry) {
        this.entryList.add(entry);
        String name = entry.getName();
        this.entryMap.put(name, entry);
    }

    public Entry getEntry(String entryName) {
        Entry entry = this.entryMap.get(entryName);
        return entry;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
//        sb.append("===>" + this.name + "\r\n");
//        for(int i=0; i<this.entryList.size(); i++) {
//            OneThing oneThing = this.entryList.get(i);
//            sb.append(oneThing.toString() + "\r\n");
//        }
//        sb.append("<===" + this.name + "\r\n");
        return sb.toString();
    }
}
