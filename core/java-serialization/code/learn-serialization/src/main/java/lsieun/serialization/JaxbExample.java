package lsieun.serialization;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.MathContext;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="example")
public class JaxbExample {
    @XmlElement(required = true)
    private String str;
    @XmlElement(required = true)
    private BigDecimal number;

    //region Getters & Setters
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }
    //endregion


    @Override
    public String toString() {
        return "JaxbExample{" +
                "str='" + str + '\'' +
                ", number=" + number +
                '}';
    }

    public static void main(String[] args) throws Exception {
        final JaxbExample example = new JaxbExample();
        example.setStr("Some String");
        example.setNumber(new BigDecimal(12.33d, MathContext.DECIMAL64));
        System.out.println(example);

        String xml = toXML(example);
        System.out.println(xml);

        JaxbExample another = toObject(xml);
        System.out.println(another);
    }

    public static String toXML(JaxbExample example) throws Exception {
        final JAXBContext context = JAXBContext.newInstance(JaxbExample.class);
        final Marshaller marshaller = context.createMarshaller();


        try (final StringWriter writer = new StringWriter()) {
            marshaller.marshal(example, writer);
            return writer.toString();
        }

    }

    public static JaxbExample toObject(String xml) throws Exception {
        final JAXBContext context = JAXBContext.newInstance(JaxbExample.class);
        final Unmarshaller unmarshaller = context.createUnmarshaller();
        try (final StringReader reader = new StringReader(xml)) {
            final JaxbExample example = (JaxbExample) unmarshaller.unmarshal(reader);
            return example;
        }
    }
}
