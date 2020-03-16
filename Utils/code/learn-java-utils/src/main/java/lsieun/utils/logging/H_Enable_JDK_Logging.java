package lsieun.utils.logging;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class H_Enable_JDK_Logging {
    static {
        String path = H_Enable_JDK_Logging.class.getClassLoader()
                .getResource("http_logging.properties")
                .getFile();
        System.setProperty("java.util.logging.config.file", path);
    }

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://www.example.com/");
        URLConnection yc = url.openConnection();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()))) {
            // do something
            //in.lines().forEach(System.out::println);
        }
    }
}
