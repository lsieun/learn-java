package lsieun;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public class ASMHelloWorld {
    public static void main(String[] args) {
        ClassWriter cw = new ClassWriter(
                ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES
        );
        cw.visit(
                Opcodes.V1_6,        // version
                Opcodes.ACC_PUBLIC,  // access
                "lsieun/HelloWorld", // name
                null,                // signature
                "java/lang/Object",  // superName
                null                 // interfaces
        );

        MethodVisitor constructor = cw.visitMethod(
                Opcodes.ACC_PUBLIC,  // access
                "<init>",            // name
                "()V",               // desc
                null,                // signature
                null                 // exceptions
        );
        constructor.visitCode();
        // super()
        constructor.visitVarInsn(Opcodes.ALOAD, 0);
        constructor.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                "java/lang/Object",
                "<init>",
                "()V",
                false);
        constructor.visitInsn(Opcodes.RETURN);

        constructor.visitMaxs(0, 0);
        constructor.visitEnd();

        MethodVisitor mv = cw.visitMethod(
                Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                "main", "([Ljava/lang/String;)V", null, null
        );
        mv.visitCode();
        mv.visitFieldInsn(
                Opcodes.GETSTATIC,
                "java/lang/System",
                "out",
                "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Hello, World!");
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "java/io/PrintStream",
                "println",
                "(Ljava/lang/String;)V",
                false
        );
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] byteCode = cw.toByteArray();

        try {
            File file = new File("target/classes/lsieun/HelloWorld.class");
            System.out.println(file.getCanonicalPath());
            OutputStream out = new FileOutputStream(file);
            out = new BufferedOutputStream(out);
            out.write(byteCode);
            out.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
