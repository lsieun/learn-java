package lsieun.spi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MyServiceLoader {
    private static final String PREFIX = "META-INF/services/";

    public static void main(String[] args) throws Exception {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader.getClass().getName());

        Class service = MyServiceLoader.class;
        String fullName = PREFIX + service.getName();
        System.out.println("Service Provider: " + fullName);
        System.out.println("=============================");

        List<URL> resources = getResources(loader, fullName);
        System.out.println("=============================");

        System.out.println("Classes: ");
        for (URL url : resources) {
            List<String> classNames = readConfig(url);
            for (String name : classNames) {
                System.out.println("\t" + name);
            }
        }

    }

    public static List<URL> getResources(ClassLoader loader, String name) throws IOException {
        List<URL> list = new ArrayList<URL>();

        Enumeration<URL> configs = loader.getResources(name);
        System.out.println(configs);

        while (configs.hasMoreElements()) {
            URL url = configs.nextElement();
            list.add(url);
            System.out.println("\tURL: " + url);
        }

        return list;
    }

    public static List<String> readConfig(URL url) {
        InputStream in = null;
        BufferedReader r = null;

        List<String> names = new ArrayList<String>();
        try {
            in = url.openStream();
            r = new BufferedReader(new InputStreamReader(in, "utf-8"));

            String line = null;
            while ((line = r.readLine()) != null) {
                names.add(line);
            }
        } catch (IOException x) {
            System.out.println("READ CONFIG: Error reading configuration file. " + url);
        } finally {
            try {
                if (r != null) r.close();
                if (in != null) in.close();
            } catch (IOException y) {
                System.out.println("READ CONFIG: Error closing configuration file. " + url);
            }
        }
        return names;
    }
}
