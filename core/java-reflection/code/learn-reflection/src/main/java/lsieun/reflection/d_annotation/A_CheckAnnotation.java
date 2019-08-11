package lsieun.reflection.d_annotation;

public class A_CheckAnnotation {
    public static void main(String[] args) {
        final ExampleAnnotation annotation = ExampleClass.class.getAnnotation(ExampleAnnotation.class);
        if (annotation != null) {
            System.out.println("Exist");
        }
        else {
            System.out.println("Not Exist");
        }
    }
}
