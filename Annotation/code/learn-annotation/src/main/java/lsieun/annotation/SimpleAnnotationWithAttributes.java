package lsieun.annotation;

public @interface SimpleAnnotationWithAttributes {
    String name();
    int order() default 0;
}
