package lsieun.generic.c_method;

import java.util.Collection;

public abstract class A_GenericsMethod {

    public <T, R> R performAction(final T action) {
        final R result = null;
        // Implementation here
        return result;
    }

    // 泛型，可以用在抽象方法上
    protected abstract <T,R> R performProtectedAction(final T action);

    // 泛型，可以用在静态方法上
    static <T,R> R performStaticAction(final Collection<T> action) {
        final R result = null;
        // Implementation here
        return result;
    }
}
