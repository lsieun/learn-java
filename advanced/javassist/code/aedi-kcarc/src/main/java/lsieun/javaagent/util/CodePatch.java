package lsieun.javaagent.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * 任意jar包
 * 任何类正则表达式，正则表达式方法签名
 * 任意完整类名、方法名，任意方法签名
 *
 */
public class CodePatch {
    public static void patch(String jarPath, String entryRegex, String methodName, String methodSignatureRegex) {
        try {
            ClassPool pool = ClassPool.getDefault();
            pool.insertClassPath(jarPath);

            List<String> list = JarUtil.getAllEntries(jarPath);
            if (entryRegex == null || "".equals(entryRegex.trim())) {
                entryRegex = "^.*\\.class$";
            }
            JarUtil.filter(list, entryRegex);


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

                    if (m.isEmpty()) {
                        continue;
                    }

                    if (methodName != null) {
                        if (!methodName.equals(m.getName())) {
                            continue;
                        }
                    }
                    String sig = m.getSignature();

                    if (!sig.matches(methodSignatureRegex)) {
                        continue;
                    }

                    System.out.println(className + "#" + m.getName());
                    CodeSegment codeBefore = new CodeSegment();
                    codeBefore.printBlankLine(2);
                    codeBefore.printStartMark();
                    codeBefore.printMethodInfo();
                    codeBefore.printSignature();
                    codeBefore.printArguments();
                    codeBefore.printEndMark();

                    m.insertBefore(codeBefore.getString());
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String jarPath = "/home/liusen/workdir/git-repo/learn-java/advanced/javassist/code/aedi-kcarc/lib/idea.jar";
        String classNameRegex = "^.*\\.class$";
        String methodSignatureRegex = "^\\(.*\\)Lcom/jetbrains/ls/responses/License;$";
        patch(jarPath, classNameRegex, null, methodSignatureRegex);
    }
}
