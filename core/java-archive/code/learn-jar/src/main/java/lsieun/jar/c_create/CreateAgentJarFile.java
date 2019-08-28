package lsieun.jar.c_create;

import java.io.*;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import lsieun.start.Main;

public class CreateAgentJarFile {
    public static void main(String[] args) throws IOException {
        File jarFile = File.createTempFile("agent", ".jar");
        System.out.println(jarFile);
        jarFile.deleteOnExit();

        // construct a b_manifest that allows class redefinition
        Manifest manifest = new Manifest();
        Attributes mainAttributes = manifest.getMainAttributes();
        mainAttributes.put(Attributes.Name.MANIFEST_VERSION, "1.0");
        mainAttributes.put(Attributes.Name.MAIN_CLASS, Main.class.getName());
        mainAttributes.put(new Attributes.Name("Agent-Class"), CreateAgentJarFile.class.getName());
        mainAttributes.put(new Attributes.Name("Can-Retransform-Classes"), "true");
        mainAttributes.put(new Attributes.Name("Can-Redefine-Classes"), "true");

        try (JarOutputStream jos = new JarOutputStream(new FileOutputStream(jarFile), manifest)) {
            // add the agent .class into the .jar
            JarEntry agent = new JarEntry(getJarEntryPath(Main.class));
            jos.putNextEntry(agent);

            // dump the class bytecode into the entry
            byte[] bytes = readBytes(Main.class);
            jos.write(bytes);
            jos.closeEntry();
        } catch (Exception e) {
            // Realistically this should never happen.
            throw new IOException(e);
        }

        while (true) {
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getJarEntryPath(Class<?> clazz) {
        return clazz.getName().replace('.', '/') + ".class";
    }

    public static byte[] readBytes(Class<?> clazz) {
        String dir = System.getProperty("user.dir");
        String filepath = dir + File.separator + "target" + File.separator + "classes" + File.separator + clazz.getName().replace('.', File.separatorChar) + ".class";
        System.out.println("file://" + filepath);

        try (InputStream in = new FileInputStream(filepath);
             BufferedInputStream buffIn = new BufferedInputStream(in);
        ){
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int length = 0;
            while((length = buffIn.read(buff)) > 0) {
                out.write(buff, 0, length);
            }
            return out.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[]{1, 2, 3};
    }
}
