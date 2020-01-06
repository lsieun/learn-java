package lsieun.generic.bean;

import java.util.ArrayList;
import java.util.List;

public class MyContainer implements Container<MyContained> {
    private final List<MyContained> _elements = new ArrayList<MyContained>();

    public void add(MyContained element) {
        _elements.add(element);
    }

    public List<MyContained> elements() {
        return _elements;
    }

    public Class<MyContained> getElementType() {
        return MyContained.class;
    }
}
