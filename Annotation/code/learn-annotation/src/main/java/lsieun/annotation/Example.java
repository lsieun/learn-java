package lsieun.annotation;

@SimpleAnnotationWithValue("new annotation")
@SimpleAnnotationWithAttributes(name = "new annotation")
public class Example {

    @RepeatableAnnotation("repeatition 1")
    @RepeatableAnnotation("repeatition 2")
    public void performAction() {
        // Some code here
    }

}
