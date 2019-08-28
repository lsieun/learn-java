package lsieun.jar.f_update;

import java.io.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

// https://subversivebytes.wordpress.com/2012/10/11/java-programmatically-update-jar-file/
public class A_JDK6_UpdateJar {
    public void updateJarFile(File srcJarFile, String targetPackage, File... filesToAdd) throws IOException {
        File tmpJarFile = File.createTempFile("tempJar", ".tmp");
        JarFile jarFile = new JarFile(srcJarFile);
        boolean jarUpdated = false;

        try {
            JarOutputStream tempJarOutputStream = new JarOutputStream(new FileOutputStream(tmpJarFile));

            try {
                //Added the new files to the jar.
                for (int i = 0; i < filesToAdd.length; i++) {
                    File file = filesToAdd[i];
                    FileInputStream fis = new FileInputStream(file);
                    try {
                        byte[] buffer = new byte[1024];
                        int bytesRead = 0;
                        JarEntry entry = new JarEntry(targetPackage + File.separator + file.getName());
                        tempJarOutputStream.putNextEntry(entry);
                        while ((bytesRead = fis.read(buffer)) != -1) {
                            tempJarOutputStream.write(buffer, 0, bytesRead);
                        }

                        //System.out.println(entry.getName() + " added.");
                    } finally {
                        fis.close();
                    }
                }

                //Copy original jar file to the temporary one.
                Enumeration<JarEntry> jarEntries = jarFile.entries();
                while (jarEntries.hasMoreElements()) {
                    JarEntry entry = jarEntries.nextElement();
                    InputStream entryInputStream = jarFile.getInputStream(entry);
                    //tempJarOutputStream.putNextEntry(entry);
                    //create a new entry to avoid ZipException: invalid entry compressed size
                    tempJarOutputStream.putNextEntry(new JarEntry(entry.getName()));
                    byte[] buffer = new byte[1024];
                    int bytesRead = 0;
                    while ((bytesRead = entryInputStream.read(buffer)) != -1) {
                        tempJarOutputStream.write(buffer, 0, bytesRead);
                    }
                }

                jarUpdated = true;
            } catch (Exception ex) {
                ex.printStackTrace();
                tempJarOutputStream.putNextEntry(new JarEntry("stub"));
            } finally {
                tempJarOutputStream.close();
            }

        } finally {
            jarFile.close();
            //System.out.println(srcJarFile.getAbsolutePath() + " closed.");

            if (!jarUpdated) {
                tmpJarFile.delete();
            }
        }

        if (jarUpdated) {
            srcJarFile.delete();
            tmpJarFile.renameTo(srcJarFile);
            //System.out.println(srcJarFile.getAbsolutePath() + " updated.");
        }
    }
}
