package lsieun.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class FindUtil {
    /**
     *
     * @param jarPath
     * @param classNameRegex eg. "^com/jetbrains/\w+/\w+/\w+\.class$"
     * @param methodSignatureRegex eg. "^\(Ljava/lang/String;Ljava/lang/String;J\[Lcom/jetbrains/\w+/\w+/\w+;\)V$"
     */
    public static void findClassByRegex(String jarPath, String classNameRegex, String methodSignatureRegex) {
        try {
            ClassPool pool = ClassPool.getDefault();
            pool.insertClassPath(jarPath);

            List<String> list = JarUtil.getAllEntries(jarPath);
            JarUtil.filter(list, classNameRegex);

            Map<String, ByteArrayOutputStream> map = JarUtil.getAllClasses(jarPath, list);

            Iterator<Map.Entry<String, ByteArrayOutputStream>> it = map.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry<String, ByteArrayOutputStream> entry = it.next();
                String className = entry.getKey();
                byte[] byteCode = entry.getValue().toByteArray();

                CtClass cc = pool.makeClass(new ByteArrayInputStream(byteCode));
                CtMethod[] methods = cc.getDeclaredMethods();
                for (int i=0; i<methods.length; i++) {
                    CtMethod m = methods[i];
                    String sig = m.getSignature();

                    if (sig.matches(methodSignatureRegex)) {
                        System.out.println(className + "#" + m.getName() + " : " + m.isEmpty());
                    }
                }
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
