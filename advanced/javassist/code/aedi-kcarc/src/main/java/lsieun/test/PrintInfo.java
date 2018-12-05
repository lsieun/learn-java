package lsieun.test;

import java.util.ArrayList;
import java.util.List;

import lsieun.javaagent.handler.CatchExceptionHandler;
import lsieun.javaagent.handler.ClassNameFilter;
import lsieun.javaagent.handler.InfoHandler;
import lsieun.javaagent.util.CodePatch;

public class PrintInfo {
    public static void main(String[] args) {
        List<String> resultList = new ArrayList<String>();
        try {
            String jarPath = "/home/liusen/workdir/dummy/lab-idea/idea.jar";
            CodePatch patch = new CodePatch();
            patch.init(jarPath);

            List<ClassNameFilter> filters = getFilters(patch);
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
    }

    public static List<ClassNameFilter> getFilters(CodePatch patch) {
        List<ClassNameFilter> filters = new ArrayList<ClassNameFilter>();
        ClassNameFilter filter1 = new ClassNameFilter("^com/jetbrains/\\w+/\\w+/\\w+\\.class$", true);
        //ClassNameFilter filter1 = new ClassNameFilter("^com/jetbrains/ls/\\w+/ValidateKey\\w+\\.class$", true);

        //String regexp = "^.*$";
        String regexp = "^\\(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J\\)Lcom/jetbrains/ls/responses/ValidateKeyResponse;$";
        filter1.addHandler(new CatchExceptionHandler(regexp, true, patch.getPool()));
        //filter1.addHandler(new InfoHandler(regexp, true));

        filters.add(filter1);
        return filters;
    }
}
