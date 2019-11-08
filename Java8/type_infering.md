# inferring the type

```java
button.addActionListener(event -> System.out.println("button clicked"));
```

What is happening under the hood is that `javac` is inferring the type of the variable `event` from its context. What this means is that you don’t need to explicitly write out the type when it’s obvious.

For the sake of readability and familiarity, you have the option to include the type declarations, and sometimes the compiler just can’t work it out!


