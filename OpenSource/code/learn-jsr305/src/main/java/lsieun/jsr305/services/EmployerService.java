package lsieun.jsr305.services;

import java.util.List;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import lsieun.jsr305.entities.Employer;
import lsieun.jsr305.entities.Specification;

/**
 * Defines the API contract for the employer service.
 *
 * @author lsieun
 * @since 1.0
 */
public interface EmployerService {
    /**
     * @param identifier the emloyer's identifier
     * @return the employer having specified {@code identifier}, {@code null} if not found
     */
    @CheckForNull
    Employer withId(@Nonnull Long identifier);

    /**
     * @param specification defines which employers should be returned
     * @return the list of employers matching {@code specification}
     */
    @Nonnull
    List<Employer> thatAre(@Nonnull Specification specification);

}
