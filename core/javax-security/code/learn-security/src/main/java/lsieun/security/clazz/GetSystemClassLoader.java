package lsieun.security.clazz;

import java.net.URISyntaxException;

public class GetSystemClassLoader {
    public static void main(String[] args) {
        try {
            String jarFilePath = ClassLoader.getSystemClassLoader().getResource(".")
                    .toURI()
                    .getPath()
                    .replaceFirst("/", "");
            System.out.println(jarFilePath);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
