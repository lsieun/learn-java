package lsieun.spi;

import java.util.Iterator;
import java.util.LinkedHashMap;


/**
 * @see java.util.ServiceLoader
 * @param <T>
 */
public class ServiceLoader<T> implements Iterable<T> {
    private static final String PREFIX = "META-INF/services/";

    // The class or interface representing the service being loaded
//    private final Class<T> service;

    // The class loader used to locate, load, and instantiate providers
//    private final ClassLoader loader;

    // Cached providers, in instantiation order
    private LinkedHashMap<String,T> providers = new LinkedHashMap<String,T>();

    // The current lazy-lookup iterator
    private LazyIterator lookupIterator;
    public Iterator<T> iterator() {
        return null;
    }
}
