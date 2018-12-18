package lsieun.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationProxyExample implements Serializable {
    private static final long serialVersionUID = 1660365429822853696L;

    private String name;
    private int age;

    //region Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    //endregion

    public SerializationProxyExample(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void readObject(ObjectInputStream in) throws InvalidObjectException {
        System.out.println("readObject");
        throw new InvalidObjectException("Serialization Proxy is expected");
    }

    private Object writeReplace() {
        System.out.println("writeReplace");
        return new SerializationProxy(this);
    }

    @Override
    public String toString() {
        return "SerializationProxyExample{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = -8563914637143151720L;

        private String str;
        private int number;

        public SerializationProxy(final SerializationProxyExample instance) {
            System.out.println("SerializationProxy Constructor");
            this.str = instance.getName();
            this.number = instance.getAge();
        }

        private Object readResolve() {
            System.out.println("readResolve");
            return new SerializationProxyExample(str, number); // Uses public constructor
        }
    }

    public static void main(String[] args) {
        String workingDir = System.getProperty("user.dir");
        String filepath = workingDir + File.separator + "target/object.proxy";

        SerializationProxyExample instance = new SerializationProxyExample("Jerry", 1);
//        instance.setName("云天之巔");
//        instance.setAge(2);
        System.out.println(instance);

        try (
                FileOutputStream fout = new FileOutputStream(filepath);
                ObjectOutputStream out = new ObjectOutputStream(fout);
        ) {
            out.writeObject(instance);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("=================================");

        try (
                FileInputStream fin = new FileInputStream(filepath);
                ObjectInputStream in = new ObjectInputStream(fin);
        ) {
            SerializationProxyExample another = (SerializationProxyExample) in.readObject();
            System.out.println(another);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
