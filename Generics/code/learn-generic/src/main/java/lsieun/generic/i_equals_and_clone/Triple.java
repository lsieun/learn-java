package lsieun.generic.i_equals_and_clone;

import java.lang.reflect.Method;

/**
 * equals方法实现思路的总结：
 * <ul>
 *     <li>第一步，判断“reference variable”的“值”（内存空间的地址）是否相等</li>
 *     <li>第二步，判断“reference variable”所指向的内存空间的“类型”是否相等</li>
 *     <li>第三步，判断“reference variable”所指向的内存空间的“内容”是否相等</li>
 * </ul>
 */
public class Triple<T> implements Cloneable {
    private T fst, snd, trd;

    public Triple(T t1, T t2, T t3) {
        fst = t1;
        snd = t2;
        trd = t3;
    }

    /**
     * 从泛型的角度来看，
     * <ul>
     *     <li>推荐的写法： {@code Triple<?> otherTriple = (Triple<?>) other;}</li>
     *     <li>如果写成这样： {@code Triple<T> otherTriple = (Triple<T>) other;} 会有unchecked cast。</li>
     *     <li>如果写成这样： {@code Triple<T> otherTriple = (Triple<?>) other;} 会出现compile error。</li>
     * </ul>
     */
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (this.getClass() != other.getClass()) return false;
        Triple<?> otherTriple = (Triple<?>) other;
        return (this.fst.equals(otherTriple.fst)
                && this.snd.equals(otherTriple.snd)
                && this.trd.equals(otherTriple.trd));
    }

    public Triple<T> clone() {
        Triple<T> result = null;
        try {
            result = (Triple<T>) super.clone(); // unchecked warning
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }

        // first field
        try {
            Class<?> clzz = this.fst.getClass();
            Method meth = clzz.getMethod("clone", new Class[0]);
            Object dupl = meth.invoke(this.fst, new Object[0]);
            result.fst = (T) dupl; // unchecked warning
        } catch (Exception e) {
            // ...
        }

        // second field
        try {
            Class<?> clzz = this.snd.getClass();
            Method meth = clzz.getMethod("clone", new Class[0]);
            Object dupl = meth.invoke(this.snd, new Object[0]);
            result.snd = (T) dupl; // unchecked warning
        } catch (Exception e) {
            // ...
        }

        // third field
        try {
            Class<?> clzz = this.trd.getClass();
            Method meth = clzz.getMethod("clone", new Class[0]);
            Object dupl = meth.invoke(this.trd, new Object[0]);
            result.trd = (T) dupl; // unchecked warning
        } catch (Exception e) {
            // ...
        }
        return result;
    }

}
