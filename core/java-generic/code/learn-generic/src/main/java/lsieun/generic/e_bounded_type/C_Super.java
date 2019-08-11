package lsieun.generic.e_bounded_type;

import java.util.Collection;

/**
 * In contrast to <b>extends</b>, the <b>super</b> keyword restricts the type parameter to be a superclass of some other class.
 */
public class C_Super {
    public void iterate(final Collection<? super Integer> objects) {
        // Some implementation here
    }
}
