# Output of Garbage Collection

URL: https://www.geeksforgeeks.org/output-of-java-programs-set-10-garbage-collection/


In Java, **object destruction** is taken care by the **Garbage Collector** module and the objects which do not have any references to them are eligible for garbage collection. 

## Program 1

```java
package lsieun.jvm.engine.gc;

public class OutputTest01_Default_Finalize {
    public static void main(String[] args) throws InterruptedException {
        String str = new String("GeeksForGeeks");

        // making str eligible for gc
        str = null;

        // calling garbage collector
        System.gc();

        // waiting for gc to complete
        Thread.sleep(1000);

        System.out.println("end of main");
    }

    @Override
    protected void finalize() {
        System.out.println("finalize method called");
    }
}
```

Output:

```txt
end of main
```

**Explanation** : 

We know that `finalize()` method is called by **Garbage Collector** on an object before destroying it. But here, the trick is that the `str` is `String` class object, not the `OutputTest01_Default_Finalize` class. Therefore, `finalize()` method of `String` class(if overridden in `String` class) is called on `str`. If a class doesn’t override `finalize` method, then by default `Object` class `finalize()` method is called.


## Program 2

```java
package lsieun.jvm.engine.gc;

public class OutputTest02_Finalize_Exception {
    public static void main(String[] args) throws InterruptedException {
        OutputTest02_Finalize_Exception t = new OutputTest02_Finalize_Exception();

        // making t eligible for garbage collection
        t = null;

        // calling garbage collector
        System.gc();

        // waiting for gc to complete
        Thread.sleep(1000);

        System.out.println("end main");
    }

    @Override
    protected void finalize() {
        System.out.println("finalize method called");
        System.out.println(10/0);
    }
}
```

Output:

```txt
finalize method called
end main
```

**Explanation** :

When Garbage Collector calls `finalize()` method on an object, it **ignores** all the exceptions raised in the method and program will terminate normally.

## Program 3

```java
package lsieun.jvm.engine.gc;

public class OutputTest03_Finalize_Count {

    static OutputTest03_Finalize_Count t ;
    static int count =0;

    public static void main(String[] args) throws InterruptedException {
        OutputTest03_Finalize_Count t1 = new OutputTest03_Finalize_Count();

        // making t1 eligible for garbage collection
        t1 = null; // line 12

        // calling garbage collector
        System.gc(); // line 15

        // waiting for gc to complete
        Thread.sleep(1000);
        System.out.println("First GC");
        // making t eligible for garbage collection,
        t = null; // line 21

        // calling garbage collector
        System.gc(); // line 24

        // waiting for gc to complete
        Thread.sleep(1000);
        System.out.println("Second GC");

        System.out.println("finalize method called "+count+" times");

    }

    @Override
    protected void finalize() {
        System.out.println("finalize");
        count++;
        t = this; // line 38
    }
}
```

Output:

```txt
finalize
First GC
Second GC
finalize method called 1 times
```

**Explanation** :

After execution of line `12`, `t1` becomes eligible for garbage collection. So when we call garbage collector at line `15`, Garbage Collector will call `finalize()` method on `t1` before destroying it. But in `finalize` method, in line `38`, we are again referencing the same object by `t`, so after execution of line `38`, this object is **no longer** eligible for garbage collection. Hence, Garbage Collector will not destroy the object.

Now again in line `21`, we are making same object eligible for garbage collection one more time. Here, we have to clear about one fact about Garbage Collector i.e. it will call `finalize()` method on a particular object exactly one time. Since on this object, `finalize()` method is already called, so now Garbage Collector will destroy it without calling `finalize()` method again.

## Program 4

```java
package lsieun.jvm.engine.gc;

public class OutputTest04_Finalize_Count_Method {
    public static void main(String[] args) throws InterruptedException {
        // How many objects are eligible for
        // garbage collection after this line?
        m1();  // Line 7
        System.gc();
        Thread.sleep(1000);
    }

    static void m1()
    {
        OutputTest04_Finalize_Count_Method t1 = new OutputTest04_Finalize_Count_Method();
        OutputTest04_Finalize_Count_Method t2 = new OutputTest04_Finalize_Count_Method();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
```

Output:

```txt
finalize
finalize
```

**Question** :

How many objects are eligible for garbage collection after execution of line `7` ?

**Answer** :

```txt
2
```

**Explanation** :

Since `t1` and `t2` are local objects of `m1()` method, so they become eligible for garbage collection after complete execution of method unless any of them is returned.


## Program 5

```java
package lsieun.jvm.engine.gc;

public class OutputTest05_Finalize_Count_ReAssign {
    public static void main(String [] args) throws InterruptedException {
        OutputTest05_Finalize_Count_ReAssign t1 = new OutputTest05_Finalize_Count_ReAssign();
        OutputTest05_Finalize_Count_ReAssign t2 = m1(t1); // line 6
        OutputTest05_Finalize_Count_ReAssign t3 = new OutputTest05_Finalize_Count_ReAssign();
        t2 = t3; // line 8
        System.gc();
        Thread.sleep(1000);
    }

    static OutputTest05_Finalize_Count_ReAssign m1(OutputTest05_Finalize_Count_ReAssign temp) {
        temp = new OutputTest05_Finalize_Count_ReAssign();
        return temp;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
```

**Question** :

How many objects are eligible for garbage collection after execution of line `8`?

**Answer** :

```txt
1
```

**Explanation** :

By the time line `8` has executed, the only object without a reference is the one generated i.e as a result of line `6`. Remember that “Java is strictly pass by value” so the reference variable `t1` is not affected by the `m1()` method. We can check it using `finalize()` method. The statement “`System.out.println(this.hashcode())`” in `finalize()` method print the object hashcode value on which `finalize()` method is called,and then just compare the value with other objects hashcode values created in main method.

