package lsieun.utils;

import java.util.List;

import lsieun.handler.ClassNameFilter;
import lsieun.utils.CodePatch;
import lsieun.utils.JetUtil;

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
