package lsieun.utils;


import java.net.URI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OperatingSystemTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLocalURI() {
        URI uri = OperatingSystem.isWindows() ? URI.create("file:/") : URI.create("file:///");
        System.out.println(uri);
    }
}