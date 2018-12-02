package lsieun.spi;

import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;

public class LazyIterator<T> implements Iterator<T> {

    Class<T> service;
    ClassLoader loader;
    Enumeration<URL> configs = null;
    Iterator<String> pending = null;
    String nextName = null;

    public LazyIterator(Class<T> service, ClassLoader loader) {
        this.service = service;
        this.loader = loader;
    }

    public boolean hasNext() {
        return false;
    }

    public T next() {
        return null;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
