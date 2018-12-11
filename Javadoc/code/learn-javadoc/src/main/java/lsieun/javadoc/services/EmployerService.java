package lsieun.javadoc.services;

import java.util.List;

import lsieun.javadoc.entities.Employer;
import lsieun.javadoc.entities.Specification;

/**
 * Defines the API contract for the employer service.
 *
 * @author lsieun
 * @version 1.0
 * @since 1.0
 *
 */
public interface EmployerService {

    /**
     * find an employer with id
     *
     * <p>
     *     Refer {@link Employer#getName() getName}
     * </p>
     *
     * @param identifier the employer's identifier
     * @return the employer having specified {@code identifier}, {@code null} if not found
     *
     */
    Employer withId(Long identifier);

    /**
     * find employers with specification
     *
     * @param specification defines which employers should be returned
     * @return the list of employers matching {@code specification}
     *
     * @see lsieun.javadoc.entities.Specification
     */
    List<Employer> thatAre(Specification specification);

    /**
     * Refer {@link #withId(Long)}
     *
     * @param identifier the employer's identifier
     * @return the employer having specified {@code identifier}, throws {@code Exception} if not found
     * @throws Exception throws exception if not found.
     * @since 1.1
     *
     */
    Employer withIdException(Long identifier) throws Exception;
}
