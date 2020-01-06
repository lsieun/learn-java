package lsieun.generic.bean;

import java.util.List;

public interface Container<T extends Contained> {
    void add(T element);
    List<T> elements();
    Class<T> getElementType();
}
