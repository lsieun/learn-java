package lsieun.jsr305.services;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lsieun.jsr305.services.impl.DefaultEmployerService;

/**
 * Employer service test.
 *
 * @author lsieun
 * @since 1.0
 */
public class EmployerServiceTest {

    private EmployerService employers;

    @Before
    public void setUp() {
        employers = new DefaultEmployerService();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test01() {
        Long identifier = null;
        employers.withId(identifier);
    }

    @Test
    public void test02() {
        employers.withId(Long.valueOf(1L)).getBusinessName();
    }

    @Test
    public void test03() {
        employers.thatAre(null);
    }
}