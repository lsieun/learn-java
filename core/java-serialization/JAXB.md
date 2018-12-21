# JAXB

**Java Architecture for XML Binding**, or just **JAXB**, is probably the oldest alternative serialization mechanism available to Java developers. Underneath, it uses XML as the serialization format, provides a wide range of customization options and includes
a lot of annotations which makes **JAXB** very appealing and easy to use.

Let us take a look on a quite simplified example of the plain old Java class (POJO) annotated with **JAXB** annotations:

```java
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlRootElement( name = "example" )
public class JaxbExample {
    @XmlElement(required = true) private String str;
    @XmlElement(required = true) private BigDecimal number;
    // Setters and getters here
}
```

To serialize the instance of this class into XML format using JAXB infrastructure, the only thing needed is the instance of the marshaller (or serializer), for example:

```java
final JAXBContext context = JAXBContext.newInstance( JaxbExample.class );
final Marshaller marshaller = context.createMarshaller();

final JaxbExample example = new JaxbExample();
example.setStr( "Some string" );
example.setNumber( new BigDecimal( 12.33d, MathContext.DECIMAL64 ) );

try( final StringWriter writer = new StringWriter() ) {
    marshaller.marshal( example, writer );
}
```

Here is the XML representation of the `JaxbExample` class instance from the example above:

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<example>
<str>Some string</str>
<number>12.33000000000000</number>
</example>
```

Following the same principle, the instances of the class could be deserialized back from XML representation into the Java objects using the instance of the unmarshaller (or deserializer), for example:

```java
final JAXBContext context = JAXBContext.newInstance( JaxbExample.class );
final String xml = "" +
    "<?xml version=\\"1.0\\" encoding=\\"UTF-8\\" standalone=\\"yes\\"?>" +
    "<example>" +
    "<str>Some string</str>" +
    "<number>12.33000000000000</number>" +
    "</example>";

final Unmarshaller unmarshaller = context.createUnmarshaller();

try( final StringReader reader = new StringReader( xml ) ) {
    final JaxbExample example = ( JaxbExample )unmarshaller.unmarshal( reader );
    // Some implementaion here
}
```

As we can see, **JAXB** is quite easy to use and the XML format is still quite popular choice nowadays. However, **one of the fundamental pitfalls of XML** is **verbosity**: quite often the necessary XML structural elements significantly surpass the effective data payload.
