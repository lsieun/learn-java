# JSON-P
Since 2013, Java developers are able to use **JSON** as the serialization format, by virtue of newly introduced **Java API for JSON Processing** (**JSON-P**).

As of now, JSON-P is not a part of Java standard library although there are many discussions to include native JSON support into the language in the upcoming **Java 9** release (http://openjdk.java.net/jeps/198). Nevertheless, it is there and available as part of Java JSON Processing Reference Implementation (https://jsonp.java.net/).

In contrast to JAXB, there is nothing required to be added to the class to make it suitable for JSON serialization, for example:

```java
public class JsonExample {
    private String str;
    private BigDecimal number;
    // Setters and getters here
}
```

The serialization is not as transparent as with JAXB, and requires a bit of code to be written for each class intended to be serialized into JSON, for example:

> 我使用的是Java 8，并没有Json类，可能是Java 9中的类。

```java
final JsonExample example = new JsonExample();
example.setStr( "Some string" );
example.setNumber( new BigDecimal( 12.33d, MathContext.DECIMAL64 ) );

try( final StringWriter writer = new StringWriter() ) {
    Json.createWriter(writer).write(
        Json.createObjectBuilder()
        .add("str", example.getStr() )
        .add("number", example.getNumber() )
        .build()
    );
}
```

And here is the JSON representation of the `JsonExample` class instance from the example above:

```xml
{
    "str":"Some string",
    "number":12.33000000000000
}
```

The deserialization process goes in the same vein:

```java
final String json = "{\\"str\\":\\"Some string\\",\\"number\\":12.33000000000000}";
try( final StringReader reader = new StringReader( json ) ) {
    final JsonObject obj = Json.createReader( reader ).readObject();
    final JsonExample example = new JsonExample();
    example.setStr( obj.getString( "str" ) );
    example.setNumber( obj.getJsonNumber( "number" ).bigDecimalValue() );
}
```

It is fair to say that at the moment JSON support in Java is pretty basic. Nonetheless it is a great thing to have and Java  community is working on enriching the JSON support by introducing **Java API for JSON Binding** (**JSON-B**, [JSR-367](https://jcp.org/en/jsr/detail?id=367)). With this API, the serialization and deserialization of the Java objects to/from JSON should be as transparent as JAXB has.