package lsieun.example;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

public class Example_08_Fields_0E_Transient  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;

    public Example_08_Fields_0E_Transient(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User {" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {

        String dir = Example_08_Fields_0E_Transient.class.getResource(".").getPath();
        String filename = dir + "object.ser";
        final Path storage = new File(filename).toPath();
        System.out.println(filename);


        try( final ObjectOutputStream out = new ObjectOutputStream( Files.newOutputStream( storage ) ) ) {
            Example_08_Fields_0E_Transient instance = new Example_08_Fields_0E_Transient("Tom", 10);
            System.out.println(instance);
            out.writeObject(instance);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try( final ObjectInputStream in = new ObjectInputStream(Files.newInputStream(storage))) {
            final Example_08_Fields_0E_Transient another = ( Example_08_Fields_0E_Transient )in.readObject();
            System.out.println(another);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
