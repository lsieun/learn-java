package lsieun.except.asm;

import lsieun.asm.adapter.enhance.MethodOpcodeAdapter;
import lsieun.except.java7.try_with_resources.C_Exception;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.*;
import java.net.URL;

public class ShowOpcode {
    public static void main(String[] args) throws IOException {
        Class<?> clazz = C_Exception.class;
        String[] includes = new String[] {
            "^play_try_with_resource:\\(\\)V$",
            "^:$",
        };
        String[] excludes = null;

        File file = toFile(clazz);
        byte[] bytes = getBytes(file);

        byte[] newBytes = transform(bytes, includes, excludes);
        writeBytes(file, newBytes);
        System.out.println("It seems OK");
        System.out.println("file://" + file);
    }

    public static byte[] transform(byte[] bytes, String[] includes, String[] excludes) {
        ClassReader cr = new ClassReader(bytes);

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        MethodOpcodeAdapter moa = new MethodOpcodeAdapter(cw, includes, excludes);
        cr.accept(moa, ClassReader.SKIP_DEBUG);

        byte[] newBytes = cw.toByteArray();
        return newBytes;
    }

    public static byte[] getBytes(File file) {
        try(InputStream in = new FileInputStream(file);
            BufferedInputStream bin = new BufferedInputStream(in);) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] buff = new byte[1024];

            int len;
            while ((len = bin.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
            return out.toByteArray();
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void writeBytes(File file, byte[] bytes) {
        try (OutputStream out = new FileOutputStream(file);
        BufferedOutputStream bout = new BufferedOutputStream(out)){
            bout.write(bytes);
            bout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File toFile(Class<?> clazz) {
        String canonicalName = clazz.getCanonicalName();
        String str = canonicalName.replaceAll("\\.", File.separator);
        String filename = String.format("%s.class", str);

        URL resource = clazz.getClassLoader().getResource(filename);
        String path = resource.getPath();
        File file = new File(path);
        return file;
    }
}
