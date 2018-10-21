# X500Principal

Class: `javax.security.auth.x500.X500Principal`

This class implements the `java.security.Principal` interface for entities represented by `X.500` distinguished names (such as "CN=David,O=davidflanagan.com,C=US"). 

- The **constructor** methods can accept the distinguished name in string form or in binary encoded form.
- `getName(String format)` returns the name in string form, using the `format` defined by one of the three consant values. The no-argument version of `getName()` (the one defined by the `Principal` interface) returns the distinguished name formatted as specified by **RFC 2253**.
- Finally, `getEncoded()` returns a binary-encoded form of the name.

```java
public final class X500Principal implements java.security.Principal, Serializable {
    // Public Constructors
    public X500Principal(java.io.InputStream is);  
    public X500Principal(String name);  
    public X500Principal(byte[] name);
    
    // Public Constants
    public static final String CANONICAL = "CANONICAL";
    public static final String RFC1779 = "RFC1779";
    public static final String RFC2253 = "RFC2253";

    // Public Instance Methods
     public byte[ ] getEncoded( );  
     public String getName(String format);
     
    // Methods Implementing Principal
     public boolean equals(Object o);  
     public String getName( );  
     public int hashCode( );  
     public String toString( );  
}
```