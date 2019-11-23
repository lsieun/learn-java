# Static Nested Classes

Here are a few points to remember about static nested classes:

- As with static members, these belong to their enclosing class, and not to an instance of the class
- They can have all types of access modifiers in their declaration
- They only have access to static members in the enclosing class
- They can define both static and non-static members

这段理解：

- 外部
  - 归属：属于enclosing class的成员，而不属于instance
  - 访问外部：静态成员
- 内部
  - modifiers：所有都可以
  - members：both static and non-static members

Let's see how we can declare a static nested class:

```java
public class Enclosing {

    private static int x = 1;

    public static class StaticNested {

        private void run() {
            // method implementation
        }
    }

    @Test
    public void test() {
        Enclosing.StaticNested nested = new Enclosing.StaticNested();
        nested.run();
    }
}
```
