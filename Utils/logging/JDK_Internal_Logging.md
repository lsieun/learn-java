# Enabling JDK Internal Logging

To enable JDK internal logging, we need to simply assign a suitable logging level to the desired logger name. Following example shows how to enable `HttpURLConnection` internal logging.

File: `http_logging.properties`

```txt
handlers= java.util.logging.ConsoleHandler
java.util.logging.ConsoleHandler.level = ALL
sun.net.www.protocol.http.HttpURLConnection.level=ALL
```

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MyClass {
    static {
        String path = MyClass.class.getClassLoader()
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
```
