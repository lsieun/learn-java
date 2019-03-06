package lsieun.structure;

import java.util.ArrayList;
import java.util.List;

public class Struct {
    private String name;
    private List<Item> items;

    public Struct() {
        this.items = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Struct{" +
                "name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
