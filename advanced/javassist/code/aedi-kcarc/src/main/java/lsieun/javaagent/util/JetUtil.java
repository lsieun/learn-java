package lsieun.javaagent.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javassist.CtClass;
import lsieun.javaagent.handler.*;

public class JetUtil {

    public static List<String> process(String jarPath) {
        List<String> resultList = new ArrayList<String>();
        try {
            CodePatch patch = new CodePatch();
            patch.importPackage("com.jetbrains.ls.responses");
            patch.init(jarPath);

            List<ClassNameFilter> filters = getFilters();
            List<String> classList = patch.processClasses(filters);
            resultList.addAll(classList);

            List<String> topDirList = patch.getTopDir(classList);
            resultList.addAll(patch.updateJar(topDirList));
            resultList.addAll(patch.deleteDir(topDirList));
        }
        catch (Exception ex) {
            resultList.add(ex.getMessage());
            ex.printStackTrace();
        }
        return resultList;
    }

    public static List<ClassNameFilter> getFilters() {
        List<ClassNameFilter> filters = new ArrayList<ClassNameFilter>();
        ClassNameFilter filter1 = new ClassNameFilter("^com/jetbrains/\\w+/\\w+/\\w+\\.class$", true);

        String reg_Exception = "^\\(Ljava/lang/String;Ljava/lang/String;J\\[Lcom/jetbrains/\\w+/\\w+/\\w+;\\)V$";
        filter1.addHandler(new JustReturnHandler(reg_Exception, true));

        String reg_ObtainTicket = "(Ljava/lang/String;Ljava/lang/String;IIZJ)Lcom/jetbrains/ls/responses/ObtainTicketResponse;";
        filter1.addHandler(new ObtainTicketHandler(reg_ObtainTicket,false));

        String reg_Ping = "(Ljava/lang/String;Ljava/lang/String;J)Lcom/jetbrains/ls/responses/PingResponse;";
        filter1.addHandler(new PingHandler(reg_Ping, false));

        filters.add(filter1);
        return filters;

    }

}
