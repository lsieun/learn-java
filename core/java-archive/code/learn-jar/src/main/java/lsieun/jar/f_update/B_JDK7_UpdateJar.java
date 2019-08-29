package lsieun.jar.f_update;

import java.net.URI;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class B_JDK7_UpdateJar {
    public static void main(String [] args) throws Throwable {
        Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        // locate file system by using the syntax
        // defined in java.net.JarURLConnection
        URI uri = URI.create("jar:file:/codeSamples/zipfs/zipfstest.zip");

        try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
            Path externalTxtFile = Paths.get("/codeSamples/zipfs/SomeTextFile.txt");
            Path pathInZipfile = zipfs.getPath("/SomeTextFile.txt");
            // copy a file into the zip file
            Files.copy( externalTxtFile,pathInZipfile,
                    StandardCopyOption.REPLACE_EXISTING );
        }
    }
}
