package lsieun.jar.e_copy;

import java.io.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

//java -cp . test.A_CopyJar xxx/lib/sac-1.3.jar /tmp
public class A_CopyJar {

    public static void main(String[] args) throws Exception {
        File sourceFileOrDir = new File(args[0]);
        File destDir = new File(args[1]);
        if (sourceFileOrDir.isFile()) {
            copyJarFile(new JarFile(sourceFileOrDir), destDir);
        } else if (sourceFileOrDir.isDirectory()) {
            File[] files = sourceFileOrDir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.endsWith(".jar");
                }
            });
            for (File f : files) {
                copyJarFile(new JarFile(f), destDir);
            }
        }
    }

    public static void copyJarFile(JarFile jarFile, File destDir) throws IOException {
        String fileName = jarFile.getName();
        String fileNameLastPart = fileName.substring(fileName.lastIndexOf(File.separator));
        File destFile = new File(destDir, fileNameLastPart);

        JarOutputStream jos = new JarOutputStream(new FileOutputStream(destFile));
        Enumeration<JarEntry> entries = jarFile.entries();

        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            InputStream is = jarFile.getInputStream(entry);

            //jos.putNextEntry(entry);
            //create a new entry to avoid ZipException: invalid entry compressed size
            jos.putNextEntry(new JarEntry(entry.getName()));
            byte[] buffer = new byte[4096];
            int bytesRead = 0;
            while ((bytesRead = is.read(buffer)) != -1) {
                jos.write(buffer, 0, bytesRead);
            }
            is.close();
            jos.flush();
            jos.closeEntry();
        }
        jos.close();
        jarFile.close();
    }
}
