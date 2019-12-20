package lsieun.annotation;

@Inheritable
class Base {
    //
}

class Child extends Base {
    //
}

public class InheritableTest {
    public static void main(String[] args) {
        boolean isPresent = Child.class.isAnnotationPresent(Inheritable.class);
        System.out.println(isPresent);
    }
}
