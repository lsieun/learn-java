package lsieun.javaagent.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javassist.ByteArrayClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import lsieun.javaagent.handler.ClassNameFilter;
import lsieun.javaagent.handler.Handler;


public class CodePatch {
    private String jarPath;
    private String jarDirectory;
    private String jarName;
    private ClassPool pool;
    private Map<String, ByteArrayOutputStream> map;

    public CodePatch() {
        //this.pool = ClassPool.getDefault();
        ClassPool parent = ClassPool.getDefault();
        this.pool = new ClassPool(parent);
        this.pool.appendSystemPath();         // the same class path as the default one.
        this.pool.childFirstLookup = true;    // changes the behavior of the child.
        this.map = new HashMap<String, ByteArrayOutputStream>();
    }

    public ClassPool getPool() {
        return pool;
    }

    public List<String> init(String jarPath) throws NotFoundException {
        pool.insertClassPath(jarPath);

        this.jarPath = jarPath;
        File file = new File(jarPath);
        this.jarDirectory = file.getParent();
        this.jarName = file.getName();

        List<String> resultList = new ArrayList<String>();
        resultList.add("Jar Path: " + jarPath);
        resultList.add("Jar Dir : " + jarDirectory);
        resultList.add("Jar Name: " + jarName);
        return resultList;
    }

    public void importPackage(String packageName) {
        pool.importPackage(packageName);
    }

    public List<String> processClasses(List<ClassNameFilter> filters) {
        List<String> entryList = this.getEntries(filters);
        this.map = JarUtil.getAllClasses(jarPath, entryList);

        List<String> resultList = new ArrayList<String>();

        Iterator<Map.Entry<String, ByteArrayOutputStream>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ByteArrayOutputStream> entry = it.next();
            String className = entry.getKey();
            byte[] byteCode = entry.getValue().toByteArray();

            List<Handler> handlers = getHandlers(className, filters);

            try {
                pool.insertClassPath(new ByteArrayClassPath(className, byteCode));
                CtClass cc = pool.makeClass(new ByteArrayInputStream(byteCode));
                CtMethod[] methods = cc.getDeclaredMethods();

                boolean save = false;
                for (int i=0; i<methods.length; i++) {
                    CtMethod m = methods[i];
                    if (m.isEmpty()) {
                        continue;
                    }
                    for (Handler h : handlers) {
                        if (h.match(m)) {
                            save = true;
                            h.process(m);
                        }
                    }
                }

                if (save) {
                    try {
                        cc.writeFile(this.jarDirectory);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    resultList.add(className);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultList;
    }

    public List<String> getTopDir(List<String> list) {
        List<String> resultList = new ArrayList<String>();
        for (String item : list) {
            String[] array = item.split("\\.");
            String topPackage = array[0];
            if (!resultList.contains(topPackage)) {
                resultList.add(topPackage);
            }
        }
        return resultList;
    }

    public List<String> updateJar(List<String> list) {
        List<String> resultList = new ArrayList<String>();
        for (String dir : list) {
            String[] commands = {"jar", "-uvf", jarName, dir};
            String result = ExecuteCommand.run(this.jarDirectory, commands);
            resultList.add(result);
        }
        return resultList;
    }

    public List<String> deleteDir(List<String> list) {
        List<String> resultList = new ArrayList<String>();
        for (String dir : list) {
            String unWantedDir = this.jarDirectory + File.separator + dir;
            IOUtil.deleteDirecotry(unWantedDir);
            resultList.add("Delete Directory: " + unWantedDir);
        }
        return resultList;
    }

    public List<Handler> getHandlers(String entryName, List<ClassNameFilter> filters) {
        List<Handler> list = new ArrayList<Handler>();
        entryName = entryName.replace(".", "/").concat(".class");


        for (ClassNameFilter filter : filters) {
            if (filter.matches(entryName)) {
                List<Handler> handlers = filter.getHandlers();
                if(handlers != null && handlers.size() > 0) {
                    list.addAll(handlers);
                }
            }
        }
        return list;
    }

    public List<String> getEntries(List<ClassNameFilter> filters) {
        List<String> entryNameList = JarUtil.getAllEntries(jarPath);

        List<String> list = new ArrayList<String>();
        for (String entryName : entryNameList) {
            for(ClassNameFilter filter : filters) {
                if (filter.matches(entryName)) {
                    list.add(entryName);
                    break;
                }
            }
        }
        return list;
    }


    public static void main(String[] args) {
        String jarPath = "/home/liusen/workdir/git-repo/learn-java/advanced/javassist/code/aedi-kcarc/lib/idea.jar";
        String classNameRegex = "^.*\\.class$";
        String methodSignatureRegex = "^\\(.*\\)Lcom/jetbrains/ls/responses/License;$";

    }
}
