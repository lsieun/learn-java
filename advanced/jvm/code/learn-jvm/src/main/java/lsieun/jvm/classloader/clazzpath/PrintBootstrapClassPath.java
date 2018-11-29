package lsieun.jvm.classloader.clazzpath;

import java.net.URL;

import sun.misc.Launcher;
import sun.misc.URLClassPath;

public class PrintBootstrapClassPath {
    public static void main(String[] args) throws Exception {
        URLClassPath bootstrapClassPath = Launcher.getBootstrapClassPath();
        URL[] urLs = bootstrapClassPath.getURLs();
        for (URL url : urLs) {
            System.out.println(url.toURI().getPath());
        }
    }
}
