package com.lsieun.asm;

import org.objectweb.asm.ClassVisitor;
import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.V1_5;

public class AddInterfaceAdapter extends ClassVisitor {

    final static String CLONEABLE = "java/lang/Cloneable";

    public AddInterfaceAdapter(ClassVisitor cv) {
        super(ASM4, cv);
    }

    @Override
    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {
        String[] holding = new String[interfaces.length + 1];
        holding[holding.length - 1] = CLONEABLE;
        System.arraycopy(interfaces, 0, holding, 0, interfaces.length);

        cv.visit(V1_5, access, name, signature, superName, holding);
    }

}
