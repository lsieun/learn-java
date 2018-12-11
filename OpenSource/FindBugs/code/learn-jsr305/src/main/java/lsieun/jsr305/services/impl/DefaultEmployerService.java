package lsieun.jsr305.services.impl;

import java.util.List;

import lsieun.jsr305.entities.Employer;
import lsieun.jsr305.entities.Specification;
import lsieun.jsr305.services.EmployerService;

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
}
