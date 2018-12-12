package lsieun.utils;

import org.junit.Test;


public class FindUtilTest {

    @Test
    public void test1() {
        String jarPath = "/home/liusen/workdir/git-repo/learn-java/advanced/javassist/code/aedi-kcarc/lib/idea.jar";
        String classNameRegex = "^com/jetbrains/\\w+/\\w+/\\w+\\.class$";
        String methodSignatureRegex = "^\\(Ljava/lang/String;Ljava/lang/String;J\\[Lcom/jetbrains/\\w+/\\w+/\\w+;\\)V$";
        FindUtil.findClassByRegex(jarPath, classNameRegex, methodSignatureRegex);
    }

    @Test
    public void test2() {
        String jarPath = "/home/liusen/workdir/git-repo/learn-java/advanced/javassist/code/aedi-kcarc/lib/idea.jar";
        String classNameRegex = "^com/jetbrains/\\w+/\\w+/\\w+\\.class$";
        String methodSignatureRegex = "^\\(Ljava/lang/String;Ljava/lang/String;J\\)Lcom/jetbrains/ls/responses/PingResponse;$";
        FindUtil.findClassByRegex(jarPath, classNameRegex, methodSignatureRegex);
    }

    @Test
    public void test3() {
        String jarPath = "/home/liusen/workdir/git-repo/learn-java/advanced/javassist/code/aedi-kcarc/lib/idea.jar";
        String classNameRegex = "^com/jetbrains/\\w+/\\w+/\\w+\\.class$";
        String methodSignatureRegex = "^\\(Ljava/lang/String;Ljava/lang/String;J\\)Lcom/jetbrains/a/b/a;$";
        FindUtil.findClassByRegex(jarPath, classNameRegex, methodSignatureRegex);
    }

    @Test
    public void test4() {
        String jarPath = "/home/liusen/workdir/git-repo/learn-java/advanced/javassist/code/aedi-kcarc/lib/idea.jar";
        String classNameRegex = "^.*\\.class$";
        String methodSignatureRegex = "^\\(.*\\)Lcom/jetbrains/ls/responses/License;$";
        FindUtil.findClassByRegex(jarPath, classNameRegex, methodSignatureRegex);
    }
}
