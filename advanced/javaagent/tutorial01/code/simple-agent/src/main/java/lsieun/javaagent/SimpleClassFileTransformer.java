package lsieun.javaagent;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;


public class SimpleClassFileTransformer implements ClassFileTransformer {
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] byteCode = classfileBuffer;
        if (className.equals("com/testMain/SimpleMain")) {
            System.out.println("Find SimpleMain");

            try {
                ClassPool classPool = ClassPool.getDefault();
                CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
                CtMethod[] methods = ctClass.getDeclaredMethods();

                for (CtMethod method : methods) {
                    System.out.println("Method: " + method.getName());
                    if (method.getName().equals("print")) {
                        System.out.println("Instrumenting method : " + method.getName());
                        method.insertAt(1, "System.out.println(\"This is the injected line\");");
                    }
                }
                byteCode = ctClass.toBytecode();
                ctClass.detach();
            }
            catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
        return byteCode;
    }
}
