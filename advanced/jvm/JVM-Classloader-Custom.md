# Custom ClassLoader

**The built-in class loader** would **suffice** in **most of the cases** where the files are already in the file system.

However, in scenarios where we need to load classes out of the local hard drive or a network, we may need to make use of custom class loaders.

## 1. Custom Class Loaders Use-Cases

Custom class loaders are helpful for more than just loading the class during runtime, a few use cases might include:

- Helping in **modifying the existing bytecode**, e.g. weaving agents
- Creating classes dynamically suited to the user’s needs. e.g in JDBC, switching between different driver implementations is done through dynamic class loading.
- Implementing a **class versioning mechanism** while **loading different bytecodes** for classes with same names and packages. This can be done either through **URL class loader** (load jars via URLs) or **custom class loaders**.

## 2. Creating our Custom Class Loader

For illustration purposes, let’s say we need to **load classes from a file** using a **custom class loader**.

We need to extend the `ClassLoader` class and override the `findClass()` method:

```java
package lsieun.jvm.classloader.custom;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = loadClassFromFile(name);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassFromFile(String fileName)  {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                fileName.replace('.', File.separatorChar) + ".class");
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ( (nextValue = inputStream.read()) != -1 ) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }
}
```

In the above example, we defined **a custom class loader** that extends **the default class loader** and loads a byte array from the specified file.







