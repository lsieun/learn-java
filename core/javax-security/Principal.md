# Principal

Interface: `java.security.Principal`

Synopsis(总览；概要)

A principal is anything that has a name, such as an identity. The name in this case is often an X.500 distinguished name, but that is not a requirement.

Interface Definition

```java
public abstract interface java.security.Principal {

    // Instance Methods
    public abstract boolean equals(Object);
    public abstract String getName();
    public abstract int hashCode();
    public abstract String toString();
}
```



