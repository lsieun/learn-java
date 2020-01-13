package lsieun.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableExample implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;

    //region Getters & Setters
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public static void main(String[] args) {
        String workingDir = System.getProperty("user.dir");
        String filepath = workingDir + File.separator + "target/object.ser";

        testWrite(filepath);
        testRead(filepath);
    }

    public static void testWrite(String filepath) {
        SerializableExample instance = new SerializableExample();
        instance.setName("Jerry");
        instance.setAge(1);
        write(filepath, instance);
    }

    public static void testRead(String filepath) {
        SerializableExample instance = read(filepath);
        System.out.println(instance);
    }

    public static void write(String filepath, SerializableExample instance) {
        try(
            FileOutputStream fout = new FileOutputStream(filepath);
            ObjectOutputStream out = new ObjectOutputStream(fout);
        ) {
            out.writeObject(instance);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SerializableExample read(String filepath) {
        try (
            FileInputStream fin = new FileInputStream(filepath);
            ObjectInputStream in = new ObjectInputStream(fin);
        ) {
            SerializableExample instance = (SerializableExample) in.readObject();
            return instance;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "SerializableExample{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
