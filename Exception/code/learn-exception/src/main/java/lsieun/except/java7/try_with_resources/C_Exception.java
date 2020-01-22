package lsieun.except.java7.try_with_resources;

import lsieun.except.bean.MyResource;
import lsieun.except.bean.XxxException;

import java.util.Arrays;

public class C_Exception {
    public static void play_try_finally() throws Exception {
        MyResource resource = null;
        try {
            resource = new MyResource();
            throw new XxxException();
        }
        finally {
            if (resource != null) {
                resource.close();
            }
        }
    }

    public static void play_try_with_resource() throws Exception {
        try (MyResource resource = new MyResource();) {
            throw new XxxException();
        }
    }

    public static void main(String[] args) {
        try {
            play_try_with_resource();
        }
        catch (Exception ex) {
            System.out.println("Current Exception: " + ex.getClass());
            System.out.println("Suppressed: " + Arrays.toString(ex.getSuppressed()));
        }
    }
}
