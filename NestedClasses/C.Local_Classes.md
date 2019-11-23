# Local Classes

Local classes are a special type of **inner classes** – in which the class is defined inside a method or scope block.

Let's see a few points to remember about this type of class:

- They cannot have access modifiers in their declaration
- They have access to both static and non-static members in the enclosing context
- They can only define instance members

Here's a quick example:

```java
public class NewEnclosing {

    void run() {
        class Local {

            void run() {
                // method implementation
            }
        }
        Local local = new Local();
        local.run();
    }

    @Test
    public void test() {
        NewEnclosing newEnclosing = new NewEnclosing();
        newEnclosing.run();
    }
}
```
