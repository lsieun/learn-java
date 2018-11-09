package lsieun.javaagent.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javassist.CtClass;
import lsieun.javaagent.handler.CatchExceptionHandler;
import lsieun.javaagent.handler.Handler;
import lsieun.javaagent.handler.ObtainTicketHandler;
import lsieun.javaagent.handler.PingHandler;

public class JetUtil {
    private static String jarDir;
    private static String jarName;

    static {
        String userDir = System.getProperty("user.dir");
        jarDir = userDir + File.separator + "target" + File.separator + "tmp";
        jarName = "idea.jar";
    }

    public static void process(String jarPath) {
        // 0. Init
        init(jarPath);

        // 1. Get Classes
        String reg = "^com/jetbrains/\\w+/\\w+/\\w+\\.class$";
        Map<String, ByteArrayOutputStream> map = getClasses(jarPath, reg);

        // 2. Process and Save Classes
        processClass(map);

        // 3. Update Jar File
        String[] commands = {"jar", "-uvf", jarName, "com/jetbrains"};
        String result = ExecuteCommand.run(jarDir, commands);
        System.out.println("Result: " + result);
    }

    public static void init(String jarPath) {
        AssistUtil.insertClassPath(jarPath);

        File file = new File(jarPath);
        String dir = file.getParent();
        System.setProperty("user.dir", dir);
        jarDir = dir;
        jarName = file.getName();
        System.out.println("Working Dir: " + jarDir);
        System.out.println("Jar Name: " + jarName);
    }


    public static Map<String, ByteArrayOutputStream> getClasses(String jarPath, String reg) {
        List<String> list = JarUtil.getAllEntries(jarPath);
        JarUtil.filter(list, reg);
        System.out.println("List Size: " + list.size());
        Map<String, ByteArrayOutputStream> map = JarUtil.getAllClasses(jarPath, list);
        return map;
    }


    public static void processClass(Map<String, ByteArrayOutputStream> map) {
        Iterator<Map.Entry<String, ByteArrayOutputStream>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, ByteArrayOutputStream> entry = it.next();
            String className = entry.getKey();
            byte[] byteCode = entry.getValue().toByteArray();

            CtClass cc = null;
            try {
                cc = AssistUtil.getClass(className, byteCode);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            boolean save = check(cc);

            if (save) {
                try {
                    cc.writeFile(jarDir);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Save Class: " + className);
            }
        }
    }



    public static boolean check(CtClass cc) {
        List<Handler> handlers = new ArrayList<Handler>();

        String reg_Exception = "^\\(Ljava/lang/String;Ljava/lang/String;J\\[Lcom/jetbrains/\\w+/\\w+/\\w+;\\)V$";
        handlers.add(new CatchExceptionHandler(reg_Exception));

        String reg_ObtainTicket = "(Ljava/lang/String;Ljava/lang/String;IIZJ)Lcom/jetbrains/ls/responses/ObtainTicketResponse;";
        handlers.add(new ObtainTicketHandler(reg_ObtainTicket));

        String reg_Ping = "(Ljava/lang/String;Ljava/lang/String;J)Lcom/jetbrains/ls/responses/PingResponse;";
        handlers.add(new PingHandler(reg_Ping));

        boolean flag = AssistUtil.shouldSave(cc, handlers);
        return flag;
    }
}
