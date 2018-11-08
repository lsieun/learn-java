package lsieun.method;

import java.util.List;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.ConstPool;
import javassist.bytecode.MethodInfo;

public class MethodGetInfo {
    public static void main(String[] args) {
        try {
            String fullClassName = "lsieun.geometry.Point";
            String methodName = "main";

            ClassPool pool = ClassPool.getDefault();
            CtClass cc = pool.get(fullClassName);
            CtMethod m = cc.getDeclaredMethod(methodName);

            MethodInfo info = m.getMethodInfo();

            System.out.println("Method: " + fullClassName + "#" + methodName);

            System.out.println("\n方法名、签名、访问修饰：");
            System.out.println("Name: " + info.getName());
            System.out.println("Descriptor: " + info.getDescriptor());
            System.out.println("AccessFlags: " + info.getAccessFlags());


            System.out.println("\n构造函数和方法：");
            System.out.println("isConstructor: " + info.isConstructor());
            System.out.println("isMethod: " + info.isMethod());


            System.out.println("\n常量池：");
            ConstPool constPool = info.getConstPool();
            String className = constPool.getClassName();
            System.out.println(className);
            System.out.println(constPool);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
