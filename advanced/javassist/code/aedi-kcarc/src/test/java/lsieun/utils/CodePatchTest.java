package lsieun.utils;

import java.util.List;

import org.junit.Test;

import lsieun.handler.ClassNameFilter;

public class CodePatchTest {
    @Test
    public void test() {
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
