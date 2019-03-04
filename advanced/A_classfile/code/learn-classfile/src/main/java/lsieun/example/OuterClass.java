package lsieun.example;

public class OuterClass {

    private class InnerClass {
        private int value;
        private int value2;
    }

    private void test() {
        InnerClass innerClass = new InnerClass();
        innerClass.value = 100;
        int i = innerClass.value;
        i = innerClass.value2;
    }
}
