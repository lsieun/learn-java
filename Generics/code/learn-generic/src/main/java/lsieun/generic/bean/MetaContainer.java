package lsieun.generic.bean;

import java.util.List;

public class MetaContainer {
    private Container<? extends Contained> container;

    public void setContainer(Container<? extends Contained> container) {
        this.container = container;
    }

    public void add(Contained element) {
        _add(container, element);
    }

    private static <T extends Contained> void _add(Container<T> container, Contained element) {
        container.add(container.getElementType().cast(element));
    }

    public List<? extends Contained> elements() {
        return container.elements();
    }

}
