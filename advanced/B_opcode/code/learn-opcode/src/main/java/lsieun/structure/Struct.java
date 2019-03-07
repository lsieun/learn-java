package lsieun.structure;

import java.util.ArrayList;
import java.util.List;

public abstract class Struct {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void print();
}
