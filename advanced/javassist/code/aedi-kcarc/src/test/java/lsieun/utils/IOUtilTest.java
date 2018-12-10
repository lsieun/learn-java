package lsieun.utils;

import java.io.File;

import org.junit.Before;
import org.junit.Test;


public class IOUtilTest {

    private String baseDir;

    @Before
    public void setUp() {
        baseDir = System.getProperty("user.dir");
        System.out.println("baseDir = " + baseDir);
    }

    @Test
    public void testBackUp() {
        String filePath = baseDir + File.separator + "target" + File.separator + "Inability.jar";
        String result = IOUtil.backupFile(filePath);
        System.out.println("Result: " + result);
    }
}
