package com.lsieun.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;
import java.util.logging.Logger;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.V1_5;

public class PublicizeMethodAdapter extends ClassVisitor {

    final Logger logger = Logger.getLogger("PublicizeMethodAdapter");
    TraceClassVisitor tracer;
    PrintWriter pw = new PrintWriter(System.out);

    public PublicizeMethodAdapter(ClassVisitor cv) {
        super(ASM4, cv);
        this.cv = cv;
        tracer = new TraceClassVisitor(cv, pw);
    }

    @Override
    public MethodVisitor visitMethod(int access,
                                     String name,
                                     String desc,
                                     String signature,
                                     String[] exceptions) {

        if (name.equals("toUnsignedString0")) {
            logger.info("Visiting unsigned method");
            return tracer.visitMethod(ACC_PUBLIC + ACC_STATIC, name, desc, signature, exceptions);
        }
        return tracer.visitMethod(access, name, desc, signature, exceptions);

    }

    public void visitEnd() {
        tracer.visitEnd();
        System.out.println(tracer.p.getText());
    }

}
