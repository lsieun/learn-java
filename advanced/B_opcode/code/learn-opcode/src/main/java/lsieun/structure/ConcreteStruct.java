package lsieun.structure;

import java.util.ArrayList;
import java.util.List;

public class ConcreteStruct extends Struct {
    private List<Item> items;

    public ConcreteStruct() {
        this.items = new ArrayList();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public void print() {
        System.out.println(super.getName());
        int size = this.items.size();
        for(int i=0; i<size; i++) {
            Item item = this.items.get(i);
            System.out.println("\t" + item);
        }
    }
}
