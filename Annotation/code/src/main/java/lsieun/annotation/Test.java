package lsieun.annotation;

import java.lang.annotation.Annotation;

public class Test {
    @MyTag(name="info method", age=1)
    @Deprecated
    public void info() {
        System.out.println("Info");
    }

    public static void testGetAnnotations() throws Exception{
        Annotation[] array = Class.forName("lsieun.annotation.Test").getMethod("info").getAnnotations();
        System.out.println("array = " + array.length);

        for(Annotation an : array) {
            System.out.println(an);
        }
    }

    public static void testGetAnnotationValues() throws Exception {
        Test tt = new Test();
        Annotation[] array = tt.getClass().getMethod("info").getAnnotations();
        for(Annotation an : array) {
            if(an instanceof MyTag) {
                System.out.println("Tag is: " + an);
                MyTag tag = (MyTag) an;
                System.out.println("tag.name(): " + tag.name());
                System.out.println("tag.age(): " + tag.age());
            }
            else {
                System.out.println(an);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        testGetAnnotations();
        testGetAnnotationValues();
    }
}
