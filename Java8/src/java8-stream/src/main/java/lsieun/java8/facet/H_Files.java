package lsieun.java8.facet;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class H_Files {
    public static void main(String[] args) throws Exception {
        test_06_watch();
    }

    public static void test_01_list() throws IOException {
        Files.list(Paths.get("."))
                .forEach(System.out::println);
    }

    public static void test_02_directory() throws IOException {
        Files.list(Paths.get("."))
                .filter(Files::isDirectory)
                .forEach(System.out::println);
    }

    public static void test_03_file() throws IOException {
        Files.newDirectoryStream(
                Paths.get(System.getProperty("user.dir") + "/src/main/java/lsieun/java8/facet"), path -> path.toString().endsWith(".java"))
                .forEach(System.out::println);
    }

    public static void test_04_hidden() {
        File[] files = new File(".").listFiles(File::isHidden);
        Arrays.stream(files)
                .forEach(System.out::println);
    }

    public static void test_05() {
        List<File> files =
                Stream.of(new File(".").listFiles())
                        .flatMap(file -> file.listFiles() == null ?
                                Stream.of(file) : Stream.of(file.listFiles()))
                        .collect(toList());
        System.out.println("Count: " + files.size());
        files.forEach(System.out::println);
    }

    public static void test_06_watch() throws Exception {
        final Path path = Paths.get(".");
        final WatchService watchService =
                path.getFileSystem()
                        .newWatchService();
        path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
        System.out.println("Report any file changed within next 1 minute..." );

        final WatchKey watchKey = watchService.poll(1, TimeUnit.MINUTES);
        if(watchKey != null) {
            watchKey.pollEvents()
                    .stream()
                    .forEach(event ->
                            System.out.println(event.context()));
        }
    }
}
