package lsieun.javadoc.services.impl;

import java.util.List;

import lsieun.javadoc.entities.Employer;
import lsieun.javadoc.entities.Specification;
import lsieun.javadoc.services.EmployerService;

/**
 * Default implementation of {@link EmployerService}.
 *
 * @author lsieun
 * @since 1.0
 */
public class DefaultEmployerService implements EmployerService {
    /**
     * {@inheritDoc}
     */
    public Employer withId(Long identifier) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public List<Employer> thatAre(Specification specification) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Employer withIdException(Long identifier) throws Exception {
        return null;
    }
}
