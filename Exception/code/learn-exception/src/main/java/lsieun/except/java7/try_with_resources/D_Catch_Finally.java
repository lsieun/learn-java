package lsieun.except.java7.try_with_resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class D_Catch_Finally {
    public void test() {
        try(InputStream in = new FileInputStream("/path/to/file")) {
            int value = in.read();
        }
        catch (IOException ex) {
            System.out.println("Hello Catch");
        }
        finally {
            System.out.println("Hello Finally");
        }
    }
}
