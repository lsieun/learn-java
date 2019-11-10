package lsieun.generic.e_bounded_type;

import java.io.Serializable;
import java.util.Collection;

public class B_Wildcard {

    /**
     * If the type parameter is not of the interest of the generic class, interface or method,
     * it could be replaced by the ? wildcard.
     * This method does not really care what type parameters it is being called with,
     * the only thing it needs to ensure that every type implements Serializable interface.
     * Or, if this is not of any importance, the wildcard without bounds could be used instead
     */
    public void store(final Collection<? extends Serializable> objects) {
        // Some implementation here
    }

    public void persist(final Collection<?> objects) {
        // Some implementation here
    }


}
