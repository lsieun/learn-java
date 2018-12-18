package lsieun.advanced.design;

public interface InterfaceWithDefaultMethods {
    void performAction();

    default void performDefaultAction() {
        // Implementation here
    }
}
