package lsieun.test;

public class RegExpTest {
    public static void main(String[] args) {
        String str1 = "(Ljava/lang/String;Ljava/lang/String;J[Lcom/jetbrains/a/b/s;)V";
        String str2 = "(Ljava/lang/String;Ljava/lang/String;J[Lcom/jetbrains/b/b/l;)V";
        String reg1  = "^\\(Ljava/lang/String;Ljava/lang/String;J\\[Lcom/jetbrains/\\w+/\\w+/\\w+;\\)V$";

        System.out.println(str1.matches(reg1));
        System.out.println(str2.matches(reg1));

        String str3 = "com.jetbrains.a.b.d";
        String str4 = "com.jetbrains.b.b.y";
        String reg2 = "^com\\.jetbrains\\.\\w+\\.\\w+\\.\\w+$";
        System.out.println(str3.matches(reg2));
        System.out.println(str4.matches(reg2));

    }
}
