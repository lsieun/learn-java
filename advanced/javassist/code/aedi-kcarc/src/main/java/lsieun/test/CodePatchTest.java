package lsieun.test;

import java.util.ArrayList;
import java.util.List;

import lsieun.javaagent.handler.CatchExceptionHandler;
import lsieun.javaagent.handler.ClassNameFilter;
import lsieun.javaagent.handler.Handler;
import lsieun.javaagent.handler.ObtainTicketHandler;
import lsieun.javaagent.handler.PingHandler;
import lsieun.javaagent.util.CodePatch;
import lsieun.javaagent.util.JetUtil;

public class CodePatchTest {
    public static void main(String[] args) {
        try {
            CodePatch patch = new CodePatch();
            patch.importPackage("com.jetbrains.ls.responses");
            List<String> list = patch.init("/home/liusen/workdir/dummy/lab-idea/idea.jar");
            for(String item : list) {
                System.out.println(item);
            }

            List<ClassNameFilter> filters = JetUtil.getFilters();

            list = patch.processClasses(filters);
            for(String item : list) {
                System.out.println(item);
            }

            list = patch.getTopDir(list);
            patch.updateJar(list);
            patch.deleteDir(list);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
