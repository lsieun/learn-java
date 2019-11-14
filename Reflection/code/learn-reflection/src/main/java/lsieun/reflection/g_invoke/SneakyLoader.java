package lsieun.reflection.g_invoke;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;

public class SneakyLoader extends ClassLoader {
    public SneakyLoader() {
        super(SneakyLoader.class.getClassLoader());
    }
    public Lookup getLookup() {
        return MethodHandles.lookup();
    }
}
