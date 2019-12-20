package lsieun.generic.c_method;

import java.util.Collection;
import java.util.HashSet;

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

    public static void invoke_generic_method() {
        Collection<String> c = new HashSet<>();
        Integer value = A_GenericsMethod.<String, Integer>performStaticAction(c);
        System.out.println(value);
    }
}
