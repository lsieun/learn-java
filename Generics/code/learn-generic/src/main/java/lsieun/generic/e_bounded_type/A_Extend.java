package lsieun.generic.e_bounded_type;

import java.io.Closeable;
import java.io.Externalizable;
import java.io.InputStream;
import java.io.Serializable;

public class A_Extend {

    // 类
    public <T extends InputStream> void read(final T stream) {
        // Some implementation here
    }

    // 接口
    public <T extends Serializable> void store(final T object) {
        // Some implementation here
    }

    // 泛型
    public <T, J extends T> void action(final T initial, final J next) {
        // Some implementation here
    }

    /**
     * The bounds are not limited to single constraints and could be combined using the &amp; operator.
     * @param stream
     * @param <T>
     */
    public <T extends InputStream & Serializable> void storeToRead(final T stream) {
        // Some implementation here
    }

    public <T extends Serializable & Externalizable & Closeable> void persist(final T object) {
        // Some implementation here
    }
}
