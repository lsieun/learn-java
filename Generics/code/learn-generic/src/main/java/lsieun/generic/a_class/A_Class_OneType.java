package lsieun.generic.a_class;

public class A_Class_OneType <T> {
    public void performAction(final T action) {
        // Implementation here
    }

    public void invoke_method() {
        A_Class_OneType<String> instance = new A_Class_OneType<>();
        instance.performAction("HelloWorld");
    }
}
